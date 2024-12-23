package com.Tework123.Myknowledge.controllers.user;

import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Role;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.AdminService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    FileWriter writer = new FileWriter
            ("src/test/resources/applicationTest.properties", true);

    public UserLoginControllerTest() throws IOException {
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminService adminService;

    @Sql(value = {"/clearDb.sql"})
    @Test
    @Order(1)
    void registerUser200Test() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UserTestDto.signUpDto("user1", "123456")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",
                        is("User registered successfully")));
    }

    @Test
    @Order(2)
    void registerUser200Test2() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UserTestDto.signUpDto("user2", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",
                        is("User registered successfully")));
    }

    @Test
    @Order(3)
    public void loginUser200Test() throws Exception {
        MvcResult result = mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(UserTestDto.signUpDto("user1", "123456")))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");

        try {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("jwtTokenUser1=Bearer " + token+"\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @Test
    @Order(4)
    public void loginUser200Test2() throws Exception {
        MvcResult result = mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(UserTestDto.signUpDto("user2", "12345")))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");

        try {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("jwtTokenUser2=Bearer " + token+"\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    @Order(5)
    void registerAdmin200Test() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UserTestDto.signUpDto("admin1", "000000")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",
                        is("User registered successfully")));
    }

    @Test
    @Order(6)
    public void loginAdmin200() throws Exception {
        adminService.changeRoleUser(getAdmin().getId(), Role.ROLE_ADMIN);

        MvcResult result = mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(UserTestDto.signUpDto("admin1", "000000")))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");

        try {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("jwtTokenAdmin1=Bearer " + token+"\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public User getAdmin() {
        return userRepository.findByUsername("admin1");
    }

}
