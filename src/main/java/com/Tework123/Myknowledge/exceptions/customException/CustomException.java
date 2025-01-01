package com.Tework123.Myknowledge.exceptions.customException;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {

    public CustomException(HttpStatus NOT_FOUND, String book_not_found) {
    }
    private final HttpStatus httpStatus = null;
    private final String message = null;
}