# JWT

[![Lines-of-Code](https://tokei.rs/b1/github/javacoded78/jwt-humble)](https://github.com/javacoded78/jwt-humble)
[![Hits-of-Code](https://hitsofcode.com/github/javacoded78/jwt-humble?branch=main)](https://hitsofcode.com/github/javacoded78/jwt-humble/view?branch=main)
[![mvn](https://github.com/javacoded78/jwt-humble/actions/workflows/maven-build.yml/badge.svg)](https://github.com/javacoded78/jwt-humble/actions/workflows/maven-build.yml)

[![codecov](https://codecov.io/gh/JavaCoDED78/JWT-humble/graph/badge.svg?token=GZRBG9ALU8)](https://codecov.io/gh/JavaCoDED78/JWT-humble)

This repository is an open-source Java library for fast and convenient using of
JWT tokens in your Java applications.

## Content:

* [How to use](#how-to-use)
    * [Instantiate a service](#instantiate-a-service)
    * [Create token](#create-jwt-token)
    * [If token is expired](#check-if-jwt-token-is-expired)
    * [If token has claim](#check-if-jwt-token-has-claim)
    * [Get subject from token](#get-subject-from-jwt-token)
    * [Get claims from token](#get-claims-from-jwt-token)
* [How to contribute](#how-to-contribute)

## How to use

At first, you need to install this library.

Until we publish it to Maven Central, you need to download source code, compile
it with `mvn package` and add `jwt-***.jar` as a dependency to your project.

This library provides simple and convenient usage.

### Instantiate a service

You need to create `TokenService` object and pass `secret` (base64 encoded
secret string for
JWT tokens) to the constructor.

```java
public class Main {
    public static void main(String[] args) {
        String secret = "aGZiYmtiYWllYmNpZWFpZWJsZWNldWNlY2xhZWNhaWJlbGNhZWN3Q0VCV0VXSUM=";
        TokenService tokenService = new TokenServiceImpl(secret);
    }
}
```

After, you can call available methods and use library.

### Create JWT token

To create token call method `create(TokenParameters params)` on `TokenService`
object.

```java
public class Main {
    public static void main(String[] args) {
        String token = tokenService.create(
                TokenParameters.builder("user@test.com", Duration.of(1, ChronoUnit.HOURS))
                        .build()
        );
        System.out.println(token);
    }
}
```

You can specify in `TokenParameters`:

* claims to be put in JWT token
* JWT token issuing date
* JWT token expiration date
* "sub" of JWT token

It all is configured via `TokenParameters` builder.

### Check if JWT token is expired

To check if JWT token is expired call method `isExpired(String token)` on `TokenService` object.

```java
class Main {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyb3Nvcjk5QGdtYWlsLmNvbSIsImlkIjozLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZXhwIjoxNzE3MTQ1MDIxfQ.w8ZFLFsKf7Qs9_dNb0WzdoyAIpWtfeEyqLfNI_G16_6NHbGwCRbeVVm_a_DzckytsyGYHTWRlZdi_gWK-HjrXg";
        boolean expired = tokenService.isExpired(token);
        System.out.println(expired);
    }
}
```

### Check if JWT token has claim

To check if JWT token has claim in payload call method `has(String token, String key, Object value)` on `TokenService` object.

```java
class Main {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyb3Nvcjk5QGdtYWlsLmNvbSIsImlkIjozLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZXhwIjoxNzE3MTQ1MDIxfQ.w8ZFLFsKf7Qs9_dNb0WzdoyAIpWtfeEyqLfNI_G16_6NHbGwCRbeVVm_a_DzckytsyGYHTWRlZdi_gWK-HjrXg";
        String key = "subject";
        String value = "1234567890";
        boolean hasSubject = tokenService.has(token, key, value);
        System.out.println(hasSubject);
    }
}
```

### Get subject from JWT token

To get subject from JWT token payload call method `subject(String token)`
on `TokenService` object.

**Note:** Optional, you can call
method `claims(token).get("sub").toString()` on `TokenService` object.

```java
public class Main {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyb3Nvcjk5QGdtYWlsLmNvbSIsImlkIjozLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZXhwIjoxNzE3MTQ1MDIxfQ.w8ZFLFsKf7Qs9_dNb0WzdoyAIpWtfeEyqLfNI_G16_6NHbGwCRbeVVm_a_DzckytsyGYHTWRlZdi_gWK-HjrXg";
        String subject = tokenService.getSubject(token);
        System.out.println(subject);
    }
}
```

### Get claims from JWT token

To get all claims from JWT token payload call method `claims(String token)`
on `TokenService`
object.

```java
public class Main {
    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyb3Nvcjk5QGdtYWlsLmNvbSIsImlkIjozLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZXhwIjoxNzE3MTQ1MDIxfQ.w8ZFLFsKf7Qs9_dNb0WzdoyAIpWtfeEyqLfNI_G16_6NHbGwCRbeVVm_a_DzckytsyGYHTWRlZdi_gWK-HjrXg";
        Map<String, Object> claims=tokenService.claims(token);
        claims.forEach((key,value)->System.out.println(key+" "+value));
    }
}

```

## How to contribute

See active issues at [issues page](https://github.com/JavaCoDED78/JWT-humble/issues)
