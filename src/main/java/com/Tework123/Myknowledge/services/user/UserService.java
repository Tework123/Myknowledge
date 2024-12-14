package com.Tework123.Myknowledge.services.user;


import com.Tework123.Myknowledge.dtos.user.SignUpDto;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Role;
import com.Tework123.Myknowledge.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public void createUser(SignUpDto signUpDto) {

//        if (userRepository.existsByUsername(signUpDto.getUsername())) {
//            throw new CustomException(HttpStatus.BAD_REQUEST, "User already exist");
//        }

        User user = new User(signUpDto.getUsername(),
                passwordEncoder.encode(signUpDto.getPassword()),
                Role.ROLE_USER);
        user.setActive(true);

        userRepository.save(user);


    }
}
