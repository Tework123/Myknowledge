package com.Tework123.Myknowledge.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok("Work norm");
    }
}
