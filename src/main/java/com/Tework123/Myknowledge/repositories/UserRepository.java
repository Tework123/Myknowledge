package com.Tework123.Myknowledge.repositories;

import com.Tework123.Myknowledge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
