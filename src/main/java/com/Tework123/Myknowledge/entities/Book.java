package com.Tework123.Myknowledge.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 30, message = "3 to 30")
    private String title;

    @Size(min = 3, max = 40, message = "3 to 40")
    private String author;

    private LocalDate datePublish;

    private LocalDate dateStartRead;

    private LocalDate dateEndRead;

    private LocalDateTime dateLastEnter;

    private int numberOfPages;

    //  0 is null here
    private byte grade;

    private String language;

    private String photo;

    private String purposeRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Book(String title, String author, User user) {
        this.title = title;
        this.author = author;
        this.user = user;
    }

    public Book() {

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return id == book.id && Objects.equals(user, book.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, user);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", user=" + user +
                '}';
    }
}
