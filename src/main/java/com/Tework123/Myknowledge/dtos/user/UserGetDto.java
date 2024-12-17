package com.Tework123.Myknowledge.dtos.user;

import com.Tework123.Myknowledge.dtos.book.BookGetDto;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserGetDto {
    private Long id;
    private String username;


    public static UserGetDto toDto(User user) {
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setUsername(user.getUsername());
        userGetDto.setId(user.getId());
        return userGetDto;
    }

    public static List<UserGetDto> toListUser(List<User> userFromDb) {
        return userFromDb.stream().map(UserGetDto::toDto)
                .collect(Collectors.toList());
    }
}
