package com.Tework123.Myknowledge.controllers.user;

import com.Tework123.Myknowledge.dtos.user.SignUpDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UserTestDto {
    public static String signUpDto(String username, String password) throws JsonProcessingException {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setUsername(username);
        signUpDto.setPassword(password);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(signUpDto);
        return requestJson;
    }
}
