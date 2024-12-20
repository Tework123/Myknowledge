package com.Tework123.Myknowledge.services.user;


import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.repositories.UserRepository;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTest {

    private UserRepository userRepository;

    private UserService userService;


    void createUserTest() {
        User user1 = new User();
        User user2 = new User();
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2));

//        int sumid = userService.createUser();
    }
}
