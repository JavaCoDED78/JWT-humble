package io.github.javacoded78.jwthumble.config.redis;

/**
 * Schema interface for
 * {@link io.github.javacoded78.jwthumble.storage.RedisTokenStorageImpl}.
 */
public interface RedisSchema {

    /**
     * Redis key for JWT token to be stored with.
     *
     * @param subject "sub" of JWT token
     * @param type    token type
     * @return Redis key
     */
    String subjectTokenKey(String subject,
                           String type);

}
