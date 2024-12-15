package com.Tework123.Myknowledge.services;

import com.Tework123.Myknowledge.dtos.book.BookCreateDto;
import com.Tework123.Myknowledge.dtos.book.BookEditDto;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.exceptions.customException.CustomException;
import com.Tework123.Myknowledge.repositories.BookRepository;
import com.Tework123.Myknowledge.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public List<Book> listBooks(String username) {
        User user = userRepository.findByUsername(username);
        return bookRepository.findByUserId(user.getId());
    }

    public Book createBook(User currentUser, BookCreateDto bookCreateDto) {
        Book book = new Book(bookCreateDto.getTitle(), bookCreateDto.getAuthor(), currentUser);
        return bookRepository.save(book);
    }

    public void editBook(Long id, BookEditDto bookEditDto, User currentUser) {
        Book oldBook = bookRepository.findById(id).orElseThrow(()
                -> new CustomException(HttpStatus.NOT_FOUND, "Book not found"));
        if (oldBook.getUser() != currentUser) {
            throw new CustomException(HttpStatus.FORBIDDEN, "You don't owner this book");
        }
        if (bookEditDto.getTitle() != null) {
            oldBook.setTitle(bookEditDto.getTitle());
        }
        if (bookEditDto.getAuthor() != null) {
            oldBook.setAuthor(bookEditDto.getAuthor());
        }
        if (bookEditDto.getDatePublish() != null) {
            oldBook.setDatePublish(bookEditDto.getDatePublish());
        }
        if (bookEditDto.getDateStartRead() != null) {
            oldBook.setDateStartRead(bookEditDto.getDateStartRead());
        }
        if (bookEditDto.getDateEndRead() != null) {
            oldBook.setDateEndRead(bookEditDto.getDateEndRead());
        }
        if (bookEditDto.getDateLastEnter() != null) {
            oldBook.setDateLastEnter(bookEditDto.getDateLastEnter());
        }
        if (bookEditDto.getNumberOfPages() != 0) {
            oldBook.setNumberOfPages(bookEditDto.getNumberOfPages());
        }
        if (bookEditDto.getGrade() != 0) {
            oldBook.setGrade(bookEditDto.getGrade());
        }
        if (bookEditDto.getLanguage() != null) {
            oldBook.setLanguage(bookEditDto.getLanguage());
        }
        if (bookEditDto.getPhoto() != null) {
            oldBook.setPhoto(bookEditDto.getPhoto());
        }
        if (bookEditDto.getPurposeRead() != null) {
            oldBook.setPurposeRead(bookEditDto.getPurposeRead());
        }
        bookRepository.save(oldBook);
    }

    public void deleteBook(Long id, User currentUser) {
        Book oldBook = bookRepository.findById(id).orElseThrow(()
                -> new CustomException(HttpStatus.NOT_FOUND, "Book not found"));
        if (oldBook.getUser() != currentUser) {
            throw new CustomException(HttpStatus.FORBIDDEN, "You don't owner this book");
        }
        bookRepository.deleteById(id);

    }

}
