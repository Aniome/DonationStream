package org.app.donationstream.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class jwtStorage {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "jwtToken")
    private String jwtToken;

    @Column(name = "jwtRefreshToken")
    private String jwtRefreshToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public jwtStorage() {
    }

    public jwtStorage(Integer id, String jwtToken, String jwtRefreshToken) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.jwtRefreshToken = jwtRefreshToken;
    }
}
