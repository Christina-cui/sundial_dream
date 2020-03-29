package com.cuijing.sundial_dream.common;

import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Map;

public class Claims extends DefaultClaims {
    public static final String TOKEN = "tok";
    public static final String TYPE = "typ";
    public static final String ROLE = "rol";
    public static final String DATA = "dat";
    public static final String CLIENT_ID = "cid";
    public static final String USER_ID = "uid";

    public Claims() {
    }

    public Claims(Map<String, Object> map) {
        super(map);
    }

    public String getType() {
        return this.getString("typ");
    }

    public Claims setType(String type) {
        this.put("typ", type);
        return this;
    }

    public String getUserId() {
        return this.getString("uid");
    }

    public Claims setUserId(String userId) {
        this.put("uid", userId);
        return this;
    }

    public String getToken() {
        return this.getString("tok");
    }

    public Claims setToken(String token) {
        this.put("tok", token);
        return this;
    }

    public String getClientId() {
        return this.getString("cid");
    }

    public Claims setClientId(String clientId) {
        this.put("cid", clientId);
        return this;
    }
}

