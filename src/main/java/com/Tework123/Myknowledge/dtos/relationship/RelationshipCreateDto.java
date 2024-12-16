package com.Tework123.Myknowledge.dtos.relationship;

import com.Tework123.Myknowledge.entities.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationshipCreateDto {

    @NotNull
    private Status status;
}
