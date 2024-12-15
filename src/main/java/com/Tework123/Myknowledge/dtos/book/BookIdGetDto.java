package com.Tework123.Myknowledge.dtos.book;

import com.Tework123.Myknowledge.dtos.user.UserGetDto;
import com.Tework123.Myknowledge.entities.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookIdGetDto extends BookGetDto {

    private LocalDate dateStartRead;

    private LocalDate dateEndRead;

    private int numberOfPages;

    private String language;

    private String purposeRead;

    public static BookGetDto toDto(Book book) {
        BookIdGetDto bookIdGetDto = new BookIdGetDto();
        bookIdGetDto.setTitle(book.getTitle());
        bookIdGetDto.setAuthor(book.getAuthor());
        bookIdGetDto.setDatePublish(book.getDatePublish());
        bookIdGetDto.setDateLastEnter(book.getDateLastEnter());
        bookIdGetDto.setGrade(book.getGrade());
        bookIdGetDto.setPhoto(book.getPhoto());
        bookIdGetDto.setDateStartRead(book.getDateStartRead());
        bookIdGetDto.setDateEndRead(book.getDateEndRead());
        bookIdGetDto.setNumberOfPages(book.getNumberOfPages());
        bookIdGetDto.setLanguage(book.getLanguage());
        bookIdGetDto.setPurposeRead(book.getPurposeRead());


        if (book.getUser() != null) {
            UserGetDto userGetDto = new UserGetDto();
            userGetDto.setId(book.getUser().getId());
            userGetDto.setUsername(book.getUser().getUsername());
            bookIdGetDto.setUser(userGetDto);
        }
        return bookIdGetDto;
    }
}
