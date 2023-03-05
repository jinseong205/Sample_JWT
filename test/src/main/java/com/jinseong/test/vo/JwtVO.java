package com.jinseong.test.vo;

public class JwtVO {
    private String token;

    public JwtVO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
