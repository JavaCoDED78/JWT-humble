package io.github.javacoded78.jwthumble.storage;


import io.github.javacoded78.jwthumble.config.TokenParameters;
import io.github.javacoded78.jwthumble.config.redis.DefaultRedisSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class RedisTokenStorageImplTests {

    private static String subject;
    private static Duration duration;
    private static String type;

    private RedisTokenStorageImpl tokenStorage;

    @Container
    public GenericContainer redis = new GenericContainer(
            DockerImageName.parse("redis:5.0.3-alpine")
    )
            .withExposedPorts(6379);

    @BeforeAll
    static void setUp() {
        subject = "testSubject";
        duration = Duration.ofMinutes(30);
        type = "any";
    }

    @BeforeEach
    void setup() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setJmxEnabled(false);
        JedisPool jedisPool = new JedisPool(
                config,
                redis.getHost(),
                redis.getMappedPort(6379)
        );
        tokenStorage = new RedisTokenStorageImpl(
                jedisPool,
                new DefaultRedisSchema()
        );
    }

    @Test
    void save_ShouldStoreToken() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();
        String token = "testToken";

        tokenStorage.save(token, params);

        assertTrue(tokenStorage.exists(token, params));
    }

    @Test
    void exists_NonExistingToken_ReturnsFalse() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();
        String nonExistingToken = "nonExistingToken";

        assertFalse(tokenStorage.exists(nonExistingToken, params));
    }

    @Test
    void exists_ExistingToken_ReturnsTrue() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();
        String token = "testToken";

        tokenStorage.save(token, params);

        assertTrue(tokenStorage.exists(token, params));
    }

    @Test
    void get_ExistingToken_ReturnsToken() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();
        String token = "testToken";

        tokenStorage.save(token, params);

        assertEquals(token, tokenStorage.get(params));
    }

    @Test
    void get_NonExistingToken_ReturnsNull() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();

        assertNull(tokenStorage.get(params));
    }

}
