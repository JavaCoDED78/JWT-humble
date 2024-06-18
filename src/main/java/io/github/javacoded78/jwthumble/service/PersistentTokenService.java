package io.github.javacoded78.jwthumble.service;

import io.github.javacoded78.jwthumble.config.TokenParameters;

/**
 * Interface if PersistentTokenService.
 */
public interface PersistentTokenService extends TokenService {

    /**
     * Removes JWT token from storage. Method removes all entries
     * of the same token.
     *
     * @param token JWT token to be removed
     * @return true - if JWT token was removed, false - otherwise
     */
    boolean invalidate(String token);

    /**
     * Removes JWT token from storage.
     *
     * @param params params of JWT token
     * @return true - if JWT token was removed, false - otherwise
     */
    boolean invalidate(TokenParameters params);

}
