package com.programmers.springboard.converter;

import com.programmers.springboard.dto.PostDto;
import com.programmers.springboard.dto.PostResponseDto;
import com.programmers.springboard.dto.UserDto;
import com.programmers.springboard.model.Post;
import com.programmers.springboard.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostConverter {
    public Post convertPost(PostDto postDto) {
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .user(this.convertUser(postDto.getUserDto()))
                .build();
    }

    public User convertUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .age(userDto.getAge())
                .hobby(userDto.getHobby())
                .build();
    }

    public PostResponseDto convertPostDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdBy(post.getCreatedBy())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
