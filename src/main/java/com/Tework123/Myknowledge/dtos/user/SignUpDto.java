package com.Tework123.Myknowledge.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}