package com.Tework123.Myknowledge.controllers.user;

import com.Tework123.Myknowledge.dtos.user.SignUpDto;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
                .content(UserTestDto.signUpDto("user1", "123456"))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginUser200Test2() throws Exception {
        mockMvc.perform(post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(UserTestDto.signUpDto("user2", "12345"))).andDo(print()).andExpect(status().isOk());
    }

//    create admin     //    create admin without controllers
}
