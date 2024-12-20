package com.Tework123.Myknowledge.controllers.admin;


import com.Tework123.Myknowledge.controllers.book.BookDtoTest;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.repositories.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminControllerTest {

    @Autowired
    UserRepository userRepository;

    //    create admin without controllers --> loginUser,  not here

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void getUsers401Test() throws Exception {
        mockMvc.perform(post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void getUsers403RoleAdminTest() throws Exception {
        mockMvc.perform(post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void getUsers200Test() throws Exception {
        mockMvc.perform(post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void banUser401Test() throws Exception {
        List<User> users = userRepository.findAll();

        mockMvc.perform(post("/admin/{id}/ban" + users.get(0).getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void banUser403RoleAdminTest() throws Exception {
        mockMvc.perform(post("/admin/{id}/ban")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void banUser200Test() throws Exception {
        mockMvc.perform(post("/admin/{id}/ban")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }


    @Test
    @Order(1)
    public void changeRoleUser401Test() throws Exception {
        mockMvc.perform(post("/admin/{id}/change_role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void changeRoleUser403RoleAdminTest() throws Exception {
        mockMvc.perform(post("/admin/{id}/change_role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void changeRoleUser200Test() throws Exception {
        mockMvc.perform(post("/admin/{id}/change_role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

}
