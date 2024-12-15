package com.Tework123.Myknowledge.dtos.book;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookEditDto {

    private String title;

    private String author;

    private LocalDate datePublish;

    private LocalDate dateStartRead;

    private LocalDate dateEndRead;

    private LocalDateTime dateLastEnter;

    private int numberOfPages;

    private byte grade;

    private String language;

    private String photo;

    private String purposeRead;
}
