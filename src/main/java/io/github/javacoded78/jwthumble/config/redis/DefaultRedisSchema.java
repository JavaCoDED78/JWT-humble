package io.github.javacoded78.jwthumble.config.redis;

/**
 * Default schema for RedisTokenStorageImpl.
 */
public class DefaultRedisSchema implements RedisSchema {

    @Override
    public String subjectTokenKey(final String subject,
                                  final String type) {
        return String.format("token:%s:%s", subject, type);
    }

}
