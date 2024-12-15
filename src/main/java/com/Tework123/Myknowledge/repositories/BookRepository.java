package com.Tework123.Myknowledge.repositories;

import com.Tework123.Myknowledge.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
//    ByOrderByDateLastEnterDesc

//    List<Book> findByUser();
}
