package com.Tework123.Myknowledge.controllers.user;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
public class UserLoginControllerTest {

//    @Mock
//    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    //    @Sql(value = {"/clearDb.sql"})
    @Test
    void registerUser200Test() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UserTestDto.signUpDto("user1", "123456")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",
                        is("User registered successfully")));
    }

    @Test
    void registerUser200Test2() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UserTestDto.signUpDto("user2", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",
                        is("User registered successfully")));
    }

    @Test
    public void loginUser200Test() throws Exception {
        mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(UserTestDto.signUpDto("user1", "123456")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginUser200Test2() throws Exception {
        mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(UserTestDto.signUpDto("user2", "12345")))
                .andDo(print()).andExpect(status().isOk());
    }


//    @Sql(value = {"/createAdmin.sql"})
    @Test
    public void loginAdmin200() throws Exception {
        mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(UserTestDto.signUpDto("admin1", "000000")))
                .andDo(print()).andExpect(status().isOk());
    }
}
