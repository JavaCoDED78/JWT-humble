package io.github.javacoded78.jwthumble.storage;

import io.github.javacoded78.jwthumble.config.TokenParameters;

public interface TokenStorage {

    /**
     * Saves JWT token to storage.
     *
     * @param token  JWT token
     * @param params params of JWT token
     */
    void save(String token,
              TokenParameters params);

    /**
     * Checks if JWT token is stored.
     *
     * @param token  JWT token
     * @param params params of JWT token
     * @return true - if JWT token is stored, false - otherwise
     */
    boolean exists(String token,
                   TokenParameters params);

    /**
     * Returns JWT token from storage by its params.
     *
     * @param params params of JWT token
     * @return stored JWT token
     */
    String get(TokenParameters params);

}
