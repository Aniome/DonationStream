package org.app.donationstream.entity;

import java.io.Serial;
import java.io.Serializable;

public class Jwt implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String token;
    private String refreshToken;

    public Jwt() {
    }

    public Jwt(String jwtToken, String jwtRefreshToken) {
        this.token = jwtToken;
        this.refreshToken = jwtRefreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
