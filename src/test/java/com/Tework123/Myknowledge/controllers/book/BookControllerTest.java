package com.Tework123.Myknowledge.controllers.book;


import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.repositories.BookRepository;

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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Value("${jwtTokenUser1}")
    private String jwtTokenUser1;

    @Value("${jwtTokenUser2}")
    private String jwtTokenUser2;

    @Value("${jwtTokenAdmin1}")
    private String jwtTokenAdmin1;

    @Test
    @Order(1)
    public void createBook401Test() throws Exception {
        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(2)
    public void createBook400AuthorTest() throws Exception {
        mockMvc.perform(post("/book")
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto400Author()))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.author", is("must not be null")));

    }

    @Test
    @Order(3)
    public void createBook400TitleTest() throws Exception {
        mockMvc.perform(post("/book")
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto400Title()))
                .andDo(print()).andExpect(status().isBadRequest());
//                .andExpect(jsonPath("$.title", is("must not be null")));

    }

    @Test
    @Order(4)
    public void createBook200Test() throws Exception {
        mockMvc.perform(post("/book")
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookCreateDto200()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Book create book1")));

    }


    @Test
    @Order(5)
    public void getBooks401Test() throws Exception {
        mockMvc.perform(get("/book")
        ).andDo(print()).andExpect(status().isUnauthorized());

    }

    @Test
    @Order(6)
    public void getBooks200Test() throws Exception {
        mockMvc.perform(get("/book")
                        .header("Authorization", jwtTokenUser1)
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));

    }


    @Test
    @Order(7)
    public void getBook401Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(get("/book/" + books.get(0).getId())
        ).andDo(print()).andExpect(status().isUnauthorized());
    }


    @Test
    @Order(8)
    public void getBook200Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(get("/book/" + books.get(0).getId())
                .header("Authorization", jwtTokenUser1)
        ).andDo(print()).andExpect(status().isOk());
    }


    @Test
    @Order(9)
    public void editBook401Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(patch("/book/" + books.get(0).getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookEditDto()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(10)
    public void editBook403Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(patch("/book/" + books.get(0).getId())
                        .header("Authorization", jwtTokenUser2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookEditDto()))
                .andDo(print()).andExpect(status().isForbidden());
    }


    @Test
    @Order(11)
    public void editBook200Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(patch("/book/" + books.get(0).getId())
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookEditDto()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Book edit")));
    }

    @Test
    @Order(12)
    public void editBook200EditTest() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(get("/book/" + books.get(0).getId())
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookEditDto()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("book1Edit")))
                .andExpect(jsonPath("$.author", is("author1Edit")));
    }

    @Test
    @Order(13)
    public void deleteBook401Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(delete("/book/" + books.get(0).getId()))
                .andDo(print()).andExpect(status().isUnauthorized());
    }


    @Test
    @Order(14)
    public void deleteBook403Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(delete("/book/" + books.get(0).getId())
                        .header("Authorization", jwtTokenUser2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookEditDto()))
                .andDo(print()).andExpect(status().isForbidden());
    }


    @Test
    @Order(15)
    public void deleteBook200Test() throws Exception {
        List<Book> books = bookRepository.findAll();
        mockMvc.perform(delete("/book/" + books.get(0).getId())
                        .header("Authorization", jwtTokenUser1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(BookDtoTest.bookEditDto()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Book deleted")));

    }

}
