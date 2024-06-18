package io.github.javacoded78.jwthumble.storage;

import io.github.javacoded78.jwthumble.config.TokenParameters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenStorageImplTests {

    private static String subject;
    private static Duration duration;
    private static String type;

    private TokenStorageImpl tokenStorage;

    @BeforeAll
    static void setUp() {
        subject = "testSubject";
        duration = Duration.ofMinutes(30);
        type = "any";
    }

    @BeforeEach
    void setup() {
        tokenStorage = new TokenStorageImpl();
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

    @Test
    void invalidate_Token() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();
        String token = "testToken";
        tokenStorage.save(
                token,
                params
        );

        tokenStorage.remove(token);

        String existingToken = tokenStorage.get(params);
        assertNull(existingToken);
    }

    @Test
    void invalidate_SubjectAndType() {
        TokenParameters params = TokenParameters.builder(
                        subject,
                        type,
                        duration
                )
                .build();
        String token = "testToken";
        tokenStorage.save(
                token,
                params
        );

        tokenStorage.remove(params);

        String existingToken = tokenStorage.get(params);
        assertNull(existingToken);
    }

}

