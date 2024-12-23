package com.Tework123.Myknowledge.controllers.relationship;

import com.Tework123.Myknowledge.dtos.book.BookCreateDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipCreateDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipGetDto;
import com.Tework123.Myknowledge.dtos.user.UserGetDto;
import com.Tework123.Myknowledge.entities.Relationship;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.stream.Collectors;

public class RelationshipDtoTest {

    public static String relationshipCreateDto200() throws JsonProcessingException {
        RelationshipCreateDto relationshipCreateDto = new RelationshipCreateDto();
        relationshipCreateDto.setStatus(Status.FRIEND);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(relationshipCreateDto);
        return requestJson;
    }

    public static String relationshipCreateDto400() throws JsonProcessingException {
        RelationshipCreateDto relationshipCreateDto = new RelationshipCreateDto();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(relationshipCreateDto);
        return requestJson;
    }
}