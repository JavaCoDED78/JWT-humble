package io.github.javacoded78.jwthumble.fake;

import io.github.javacoded78.jwthumble.config.TokenParameters;
import io.github.javacoded78.jwthumble.storage.TokenStorage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class FakeTokenStorageImpl implements TokenStorage {

    private final Map<String, String> tokens = new HashMap<>();

    private String subjectTokenKey(final String subject,
                                   final String type) {
        return "tokens:" + subject + ":" + type;
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
