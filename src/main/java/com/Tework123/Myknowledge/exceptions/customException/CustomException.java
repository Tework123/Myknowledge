package com.Tework123.Myknowledge.exceptions.customException;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}