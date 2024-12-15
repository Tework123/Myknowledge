package com.Tework123.Myknowledge.dtos.book;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class BookCreateDto {
    @NonNull
    private String title;

    @NotNull
    private String author;
}
