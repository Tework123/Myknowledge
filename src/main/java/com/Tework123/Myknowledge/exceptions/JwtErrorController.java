package com.Tework123.Myknowledge.exceptions;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JwtErrorController {

    @GetMapping("/error/forbidden")
    public ResponseEntity<?> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseDto.toDto("FORBIDDEN by jwt"));
    }

}