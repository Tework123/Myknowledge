package com.Tework123.Myknowledge.controllers;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getUsers(Authentication auth) {
        return ResponseEntity.ok(ResponseDto.toDto("users give to you"));
    }
}
