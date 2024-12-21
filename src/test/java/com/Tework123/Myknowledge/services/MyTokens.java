package com.Tework123.Myknowledge.services;

public class MyTokens {
    static String AdminToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE3MzQ3NTI5MzIsImV4cCI6MTczNTE2Mjk5N30.0yqXfJEOcAgvSslauwmqI0vdevtzLwChOVjN_eEpNFA";
    static String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTczNDY2NDM4MSwiZXhwIjoxNzM1MDc0NDQ2fQ.s0eTX8wv_TJ_ykb4t1nrrEbFG_ghjhJaBgWMuV6DwSM";
    static String token2 = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsImlhdCI6MTczNDY3Mzk0NSwiZXhwIjoxNzM1MDg0MDExfQ.H5uuJjxww6n2OvNZ2kFT9Cn2BjWYBplNypbGvoGbvdM";

    public static String getAdminToken() {
        return AdminToken;
    }

    public static String getToken() {
        return token;
    }

    public static String getToken2() {
        return token2;
    }
}
