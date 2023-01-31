package com.jpa.board.dtos;

import com.jpa.board.domain.entities.User;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    public record UserRequest(
            @NotBlank(message = "Name cannot be blank")
            String name,
            String hobby,
            int age
    ) {
        public User toEntity() {
            return new User(name, hobby, age);
        }
    }

    public record UserResponse(
            Long userId,
            String name,
            String hobby,
            int age) {
        public static UserResponse from(User user) {
            return new UserResponse(user.getId(), user.getName(), user.getHobby(), user.getAge());
        }
    }
}
