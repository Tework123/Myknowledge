package com.Tework123.Myknowledge.controllers;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import com.Tework123.Myknowledge.dtos.admin.SetRoleDto;
import com.Tework123.Myknowledge.dtos.user.UserGetDto;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Role;
import com.Tework123.Myknowledge.entities.enums.Status;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private UserRepository userRepository;
    private AdminService adminService;


    @GetMapping("/users")
    public List<UserGetDto> getUsers() {
        List<User> users = userRepository.findAll();
        return UserGetDto.toListUser(users);
    }


    @PatchMapping("/users/{id}/ban")
    public ResponseEntity<ResponseDto> banUser(@PathVariable("id") Long id) {
        User user = adminService.banUser(id);
        return ResponseEntity.ok(ResponseDto.toDto(user.getUsername() + " active is "
                + user.isEnabled()));

    }

    @PatchMapping("/users/{id}/change_role")
    public ResponseEntity<ResponseDto> changeRoleUser(@PathVariable("id") Long id, @Validated
    @RequestBody SetRoleDto setRoleDto) {
        User user = adminService.changeRoleUser(id, setRoleDto.getRole());
        return ResponseEntity.ok(ResponseDto.toDto("Role for " + user.getUsername()
                + " changed to " + user.getRoles()));
    }

}
