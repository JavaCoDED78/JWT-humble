package io.github.javacoded78.jwthumble;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder(builderMethodName = "hiddenBuilder", access = AccessLevel.PRIVATE)
public class TokenParameters {

    /**
     * A map of claims to be put in JWT token.
     */
    private final Map<String, Object> claims;

    /**
     * The "sub" of JWT token.
     */
    private final String subject;

    /**
     * Date when JWT token was issued.
     */
    private final Date issuedAt;

    /**
     * Date when JWT token will be expired.
     */
    private final Date expiredAt;

    /**
     * Creates a builder for TokenParameters.
     *
     * @param subject  sub of JWT token
     * @param duration duration between token issuing and expiration date
     * @return TokenParametersBuilder
     */
    public static TokenParametersBuilder builder(final String subject,
                                                 final Duration duration) {
        Date issuedAt = new Date();
        return hiddenBuilder()
                .claims(new HashMap<>())
                .issuedAt(issuedAt)
                .subject(subject)
                .expiredAt(new Date(issuedAt.getTime() + duration.toMillis()));
    }

    public static class TokenParametersBuilder {

        /**
         * A map of claims to be put in JWT token.
         */
        private final Map<String, Object> claims = new HashMap<>();

        /**
         * The "sub" of JWT token.
         */
        private String subject;

        /**
         * Date when JWT token was issued.
         */
        private Date issuedAt;

        /**
         * Date when JWT token will be expired.
         */
        private Date expiredAt;

        /**
         * Add a claim to parameters.
         *
         * @param key   the key of the claim
         * @param value the value of the claim
         * @return the current TokenParametersBuilder instance
         */
        public TokenParametersBuilder claim(final String key,
                                            final Object value) {
            this.claims.put(key, value);
            return this;
        }

        /**
         * Adds multiple claims to parameters.
         *
         * @param claims a map of claims
         * @return the current TokenParametersBuilder instance
         */
        public TokenParametersBuilder claims(final Map<String, Object> claims) {
            this.claims.putAll(claims);
            return this;
        }

        /**
         * Sets the issued date for the JWT token.
         *
         * @param issuedAt the date of issuing
         * @return the current TokenParametersBuilder instance
         */
        public TokenParametersBuilder issuedAt(final Date issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        /**
         * Sets the expiration date for the JWT token.
         *
         * @param expiredAt the date of expiration
         * @return the current TokenParametersBuilder instance
         */
        public TokenParametersBuilder expiredAt(final Date expiredAt) {
            this.expiredAt = expiredAt;
            return this;
        }

        /**
         * Sets the subject for the JWT token.
         *
         * @param subject the subject of the JWT token
         * @return the current TokenParametersBuilder instance
         */
        public TokenParametersBuilder subject(final String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Builds and returns the final TokenParameters object.
         *
         * @return the built TokenParameters object
         */
        public TokenParameters build() {
            return new TokenParameters(claims, subject, issuedAt, expiredAt);
        }
    }

}
