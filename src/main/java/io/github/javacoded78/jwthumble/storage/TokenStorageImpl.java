package io.github.javacoded78.jwthumble.storage;

import io.github.javacoded78.jwthumble.config.TokenParameters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Basic implementation of TokenStorage.
 */
public class TokenStorageImpl implements TokenStorage {

    /**
     * Inner map of key-value pairs.
     */
    private final ConcurrentHashMap<String, String> tokens;

    /**
     * Creates an object.
     */
    public TokenStorageImpl() {
        this.tokens = new ConcurrentHashMap<>();
    }

    private String subjectTokenKey(final String subject,
                                   final String type) {
        return "tokens:" + subject + ":" + type;
    }

    @Override
    public void save(final String token,
                     final TokenParameters params) {
        String tokenKey = subjectTokenKey(
                params.getSubject(),
                params.getType()
        );
        tokens.put(tokenKey, token);
    }

    @Override
    public boolean exists(final String token,
                          final TokenParameters params) {
        String tokenKey = subjectTokenKey(
                params.getSubject(),
                params.getType()
        );
        return token.equals(tokens.get(tokenKey));
    }

    @Override
    public String get(final TokenParameters params) {
        String tokenKey = subjectTokenKey(
                params.getSubject(),
                params.getType()
        );
        return tokens.get(tokenKey);
    }

    @Override
    public boolean remove(final String token) {
        AtomicBoolean deleted = new AtomicBoolean(false);
        for (Map.Entry<String, String> entry : tokens.entrySet()) {
            if (entry.getValue().equals(token)) {
                tokens.remove(entry.getKey());
                deleted.set(true);
                return true;
            }
        }
        return deleted.get();
    }

    @Override
    public boolean remove(final TokenParameters params) {
        String tokenKey = subjectTokenKey(
                params.getSubject(),
                params.getType()
        );
        return tokens.remove(tokenKey) != null;
    }

}
