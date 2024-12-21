package com.Tework123.Myknowledge.controllers.admin;


import com.Tework123.Myknowledge.controllers.book.BookDtoTest;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.exceptions.customException.CustomException;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.MyTokens;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void getUsers401Test() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(2)
    public void getUsers403RoleAdminTest() throws Exception {

        mockMvc.perform(get("/admin/users")
                        .header("Authorization", MyTokens.getToken()))
                .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @Order(3)
    public void getUsers200Test() throws Exception {
        mockMvc.perform(get("/admin/users")
                        .header("Authorization", MyTokens.getAdminToken()))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void banUser401Test() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(5)
    public void banUser403RoleAdminTest() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban")
                        .header("Authorization", MyTokens.getToken()))
                .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @Order(6)
    public void banUser200Test() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban")
                        .header("Authorization", MyTokens.getAdminToken()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("user1 active is false")));
        ;
    }

    @Test
    @Order(7)
    public void banUserCheckTest() {
        Assertions.assertFalse(getUser().isEnabled());
    }

    @Test
    @Order(8)
    public void getBookCheckBan403Test() throws Exception {
        try {
            mockMvc.perform(get("/book")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", MyTokens.getToken()))
                    .andExpect(status().isForbidden())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof Exception));

        } catch (CustomException _) {
        }
    }

    @Test
    @Order(9)
    public void banUserCheckUnban200Test() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban")
                .header("Authorization", MyTokens.getAdminToken()));

        assertTrue(getUser().isEnabled());
    }

    @Test
    @Order(10)
    public void getBookCheckUnBan200Test() throws Exception {
        mockMvc.perform(get("/book")
                        .header("Authorization", MyTokens.getToken()))
                .andDo(print()).andExpect(status().isOk());

    }


//    @Test
//    @Order(1)
//    public void changeRoleUser401Test() throws Exception {
//        mockMvc.perform(post("/admin/users/{id}/change_role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(BookDtoTest.bookCreateDto200()))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @Order(1)
//    public void changeRoleUser403RoleAdminTest() throws Exception {
//        mockMvc.perform(post("/admin/{id}/change_role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(BookDtoTest.bookCreateDto200()))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @Order(1)
//    public void changeRoleUser200Test() throws Exception {
//        mockMvc.perform(post("/admin/{id}/change_role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(BookDtoTest.bookCreateDto200()))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }


    public User getUser() {
        return userRepository.findByUsername("user1");
    }
}
