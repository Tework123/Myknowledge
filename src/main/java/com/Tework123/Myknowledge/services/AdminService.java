package com.Tework123.Myknowledge.services;


import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Role;
import com.Tework123.Myknowledge.exceptions.customException.CustomException;
import com.Tework123.Myknowledge.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository userRepository;


    public User banUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));

        user.setActive(!user.isActive());
        userRepository.save(user);
        return user;

    }

    public User changeRoleUser(Long id, Role role) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));
        user.getRoles().clear();
        user.getRoles().add(role);
        userRepository.save(user);
        return user;
    }
}
