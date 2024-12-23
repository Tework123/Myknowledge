//package com.Tework123.Myknowledge.utils;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource("/applicationTest.properties")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class MyTokens {
//
//    private static String AdminToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE3MzQ3NTI5MzIsImV4cCI6MTczNTE2Mjk5N30.0yqXfJEOcAgvSslauwmqI0vdevtzLwChOVjN_eEpNFA";
//
//    @Value("${token}")
//    private String token;
//
//    //    private static String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTczNDY2NDM4MSwiZXhwIjoxNzM1MDc0NDQ2fQ.s0eTX8wv_TJ_ykb4t1nrrEbFG_ghjhJaBgWMuV6DwSM";
//    private static String token2 = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsImlhdCI6MTczNDY3Mzk0NSwiZXhwIjoxNzM1MDg0MDExfQ.H5uuJjxww6n2OvNZ2kFT9Cn2BjWYBplNypbGvoGbvdM";
//
//    public static String getAdminToken() {
//        return AdminToken;
//    }
//
//    public static String getToken() {
//
//        System.out.println(token);
//        System.out.println(token);
//        System.out.println(token);
//        System.out.println(token);
//
//        return token;
//    }
//
//    public static String getToken2() {
//        return token2;
//    }
//}
