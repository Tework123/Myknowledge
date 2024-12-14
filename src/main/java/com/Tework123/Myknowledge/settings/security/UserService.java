package com.Tework123.Myknowledge.settings.security;


import com.Tework123.Myknowledge.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    void saveUser(User user);
}