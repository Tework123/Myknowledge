package com.Tework123.Myknowledge.controllers.relationship;

import com.Tework123.Myknowledge.controllers.book.BookDtoTest;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.Relationship;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.MyTokens;
import com.Tework123.Myknowledge.services.RelationshipService;
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

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

    @Test
    @Order(1)
    public void createRelationship401Test() throws Exception {
        mockMvc.perform(post("/relationship")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto200()))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    @Order(2)
    public void createRelationship400Test() throws Exception {
        mockMvc.perform(post("/relationship")
                        .header("Authorization", MyTokens.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto400()))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Book create book1")));

    }

    @Test
    @Order(3)
    public void createRelationship200Test() throws Exception {
        mockMvc.perform(post("/relationship")
                        .header("Authorization", MyTokens.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(RelationshipDtoTest.relationshipCreateDto200()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Book create book1")));

    }


    @Test
    @Order(4)
    public void getRelationships200Test() throws Exception {
        List<Relationship> relationshipList = relationshipService.getRelationship(getUser());

        mockMvc.perform(get("/relationship")
                .header("Authorization", MyTokens.getToken()))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.title", is("book1Edit")))
                .andExpect(jsonPath("$.author", is("author1Edit")));
    }

    public User getUser() {
        return userRepository.findByUsername("user1");
    }

}

//check one relationship, then add else one,
// check 2, check from other user, delete both, check