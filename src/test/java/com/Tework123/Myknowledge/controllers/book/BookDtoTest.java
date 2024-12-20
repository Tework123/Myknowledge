package com.Tework123.Myknowledge.controllers.book;

import com.Tework123.Myknowledge.dtos.book.BookCreateDto;
import com.Tework123.Myknowledge.dtos.user.SignUpDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BookDtoTest {

    public static String bookCreateDto200() throws JsonProcessingException {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setTitle("book1");
        bookCreateDto.setAuthor("author1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(bookCreateDto);
        return requestJson;
    }

    public static String bookCreateDto400Author() throws JsonProcessingException {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setTitle("book1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(bookCreateDto);
        return requestJson;
    }

    public static String bookCreateDto400Title() throws JsonProcessingException {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setAuthor("author1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(bookCreateDto);
        return requestJson;
    }

    public static String bookEditDto() throws JsonProcessingException {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setTitle("book1Edit");
        bookCreateDto.setAuthor("author1Edit");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(bookCreateDto);
        return requestJson;
    }
}
