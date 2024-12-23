package com.Tework123.Myknowledge.controllers.relationship;

import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.RelationshipService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RelationshipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RelationshipService relationshipService;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwtTokenUser1}")
    private String jwtTokenUser1;

    @Value("${jwtTokenUser2}")
    private String jwtTokenUser2;

    @Value("${jwtTokenAdmin1}")
    private String jwtTokenAdmin1;

    @Test
    @Order(1)
    public void createRelationship401Test() throws Exception {
        mockMvc.perform(post("/relationship/" + getUser2().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());

    }

    @Test
    @Order(2)
    public void createRelationship400Test() throws Exception {
        mockMvc.perform(post("/relationship/" + getUser2().getId())
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto400()))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("must not be null")));

    }

    @Test
    @Order(3)
    public void createRelationship200Test() throws Exception {
        mockMvc.perform(post("/relationship/" + getUser2().getId())
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto200()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Relationship set with user2")));

    }

    @Test
    @Order(4)
    public void createRelationshipAlreadyExist400Test() throws Exception {
        mockMvc.perform(post("/relationship/" + getUser2().getId())
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto200()))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Relationship already exist")));

    }


    @Test
    @Order(5)
    public void getRelationships401Test() throws Exception {
        mockMvc.perform(get("/relationship"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    @Order(6)
    public void getRelationships200Test() throws Exception {
        mockMvc.perform(get("/relationship")
                        .header("Authorization", jwtTokenUser1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("FRIEND")))
                .andExpect(jsonPath("$[0].userGetDto.username", is("user2")));

    }

    @Test
    @Order(7)
    public void createRelationship2_200Test() throws Exception {
        mockMvc.perform(post("/relationship/" + getAdmin().getId())
                        .header("Authorization", jwtTokenUser2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto200()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Relationship set with admin1")));

    }


    @Test
    @Order(8)
    public void getRelationships2_200Test() throws Exception {
        mockMvc.perform(get("/relationship")
                        .header("Authorization", jwtTokenUser2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("FRIEND")))
                .andExpect(jsonPath("$[0].userGetDto.username", is("user1")))
                .andExpect(jsonPath("$[1].userGetDto.username", is("admin1")));

    }

    @Test
    @Order(9)
    public void deleteRelationships401Test() throws Exception {
        mockMvc.perform(delete("/relationship/" + getUser2().getId()))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }


    @Test
    @Order(10)
    public void deleteRelationships200Test() throws Exception {
        mockMvc.perform(delete("/relationship/" + getUser2().getId())
                        .header("Authorization", jwtTokenUser1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Relationship deleted")));

    }

    @Test
    @Order(11)
    public void deleteRelationships400Test() throws Exception {
        mockMvc.perform(delete("/relationship/" + getUser2().getId())
                        .header("Authorization", jwtTokenUser1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Relationship don't exist")));

    }

    @Test
    @Order(12)
    public void deleteRelationships2_200Test() throws Exception {
        mockMvc.perform(delete("/relationship/" + getAdmin().getId())
                        .header("Authorization", jwtTokenUser2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Relationship deleted")));

    }

    @Test
    @Order(13)
    public void getRelationships3_200Test() throws Exception {
        mockMvc.perform(get("/relationship")
                        .header("Authorization", jwtTokenUser2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));


    }

    @Test
    @Order(14)
    public void getRelationships4_200Test() throws Exception {
        mockMvc.perform(get("/relationship")
                        .header("Authorization", jwtTokenUser1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));


    }

    public User getUser1() {
        return userRepository.findByUsername("user1");
    }


    public User getUser2() {
        return userRepository.findByUsername("user2");
    }

    public User getAdmin() {
        return userRepository.findByUsername("admin1");
    }


}
