package com.jpa.board.dtos;

import com.jpa.board.domain.entities.Post;
import com.jpa.board.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostDto {
    public record PostRequest(
            @NotNull(message = "User ID cannot be null")
            long userId,
            @NotBlank(message = "Title cannot be blank")
            String title,
            @NotBlank(message = "Content cannot be blank")
            String content
    ) {
        public Post toEntity(User user) {
            return new Post(title, content, user);
        }
    }

    public record PostResponse(
            Long postId,
            String title,
            String content
    ) {
        public static PostResponse from(Post post) {
            return new PostResponse(post.getId(), post.getTitle(), post.getContent());
        }
    }

    public record ModifyRequest(
            @NotBlank(message = "Title cannot be blank")
            String title,
            @NotBlank(message = "Content cannot be blank")
            String content
    ) { }
}