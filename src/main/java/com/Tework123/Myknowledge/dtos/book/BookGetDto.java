package com.Tework123.Myknowledge.dtos.book;


import com.Tework123.Myknowledge.dtos.user.UserGetDto;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BookGetDto {
    private String title;
    private String author;
    private LocalDate datePublish;
    private LocalDateTime dateLastEnter;
    private byte grade;
    private String photo;
    private UserGetDto user;

    public static BookGetDto toDto(Book book) {
        BookGetDto bookGetDto = new BookGetDto();
        bookGetDto.setTitle(book.getTitle());
        bookGetDto.setAuthor(book.getAuthor());
        bookGetDto.setDatePublish(book.getDatePublish());
        bookGetDto.setDateLastEnter(book.getDateLastEnter());
        bookGetDto.setGrade(book.getGrade());
        bookGetDto.setPhoto(book.getPhoto());

        if (book.getUser() != null) {
            UserGetDto userGetDto = new UserGetDto();
            userGetDto.setId(book.getUser().getId());
            userGetDto.setUsername(book.getUser().getUsername());
            bookGetDto.setUser(userGetDto);
        }
        return bookGetDto;
    }

    public static List<BookGetDto> toListBook(List<Book> bookFromDb) {
        return bookFromDb.stream().map(BookGetDto::toDto)
                .collect(Collectors.toList());
    }
}
