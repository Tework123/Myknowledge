package com.Tework123.Myknowledge.dtos.admin;


import com.Tework123.Myknowledge.entities.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetRoleDto {

    @NotNull
    private Role role;
}
