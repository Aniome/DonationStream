package org.app.donationstream.entity;

import java.io.Serializable;

public class jwtStorage implements Serializable {
    public jwtStorage(String jwtToken, String jwtRefreshToken) {
        this.jwtToken = jwtToken;
        this.jwtRefreshToken = jwtRefreshToken;
    }

    private String jwtToken;

    private String jwtRefreshToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtRefreshToken() {
        return jwtRefreshToken;
    }

    public void setJwtRefreshToken(String jwtRefreshToken) {
        this.jwtRefreshToken = jwtRefreshToken;
    }
}
