package com.Tework123.Myknowledge.controllers.book;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import com.Tework123.Myknowledge.dtos.book.BookCreateDto;
import com.Tework123.Myknowledge.dtos.book.BookEditDto;
import com.Tework123.Myknowledge.dtos.book.BookGetDto;
import com.Tework123.Myknowledge.dtos.book.BookIdGetDto;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.exceptions.customException.CustomException;
import com.Tework123.Myknowledge.repositories.BookRepository;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @GetMapping("")
    public List<BookGetDto> getBooks(Authentication auth) {
        List<Book> books = bookService.listBooks(auth.getName());
        return BookGetDto.toListBook(books);

    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createBook(@Validated @RequestBody
                                                  BookCreateDto bookCreateDto,
                                                  Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        Book savedBook = bookService.createBook(currentUser, bookCreateDto);
        return ResponseEntity.ok(ResponseDto.toDto("Post create " + savedBook.getTitle()));

    }

    @GetMapping("/{id}")
    public BookGetDto getBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()
                -> new CustomException(HttpStatus.NOT_FOUND, "Book not found"));
        return BookIdGetDto.toDto(book);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> editBook(@PathVariable("id") Long id,
                                                @Validated
                                                @RequestBody BookEditDto bookEditDto,
                                                Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        bookService.editBook(id, bookEditDto, currentUser);
        return ResponseEntity.ok(ResponseDto.toDto("Book edit"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable("id") Long id,
                                                  Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        bookService.deleteBook(id, currentUser);
        return ResponseEntity.ok(ResponseDto.toDto("Book deleted"));
    }
}
