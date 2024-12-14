package com.Tework123.Myknowledge.controllers.user;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import com.Tework123.Myknowledge.dtos.user.SignInDto;
import com.Tework123.Myknowledge.dtos.user.SignUpDto;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserLoginController {
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        userService.createUser(signUpDto);
        return ResponseEntity.ok(ResponseDto.toDto("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody SignInDto signInDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDto.getUsername(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(ResponseDto.toDto("login successfully"));

    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getUsers(Authentication auth) {
        return ResponseEntity.ok(ResponseDto.toDto("users give to you"));
    }
}
