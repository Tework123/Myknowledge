package com.Tework123.Myknowledge.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
public class ResponseDto {
    private String message;

    public static ResponseDto toDto(String message) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);
        return responseDto;
    }
}
