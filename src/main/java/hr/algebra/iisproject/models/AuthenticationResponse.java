package hr.algebra.iisproject.models;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private final String jwt;
    private final String refreshToken;

    public AuthenticationResponse(String jwt, String refreshToken) {
        this.jwt = jwt;
        this.refreshToken = refreshToken;
    }
}