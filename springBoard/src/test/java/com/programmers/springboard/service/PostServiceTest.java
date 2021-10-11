package com.programmers.springboard.service;

import com.programmers.springboard.dto.PostDto;
import com.programmers.springboard.dto.UserDto;
import com.programmers.springboard.model.Post;
import com.programmers.springboard.repository.PostRepository;
import javassist.NotFoundException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    PostDto postDto;

    @BeforeEach
    void setUp() {
        //Given
        postDto = PostDto.builder()
                .title("Test title")
                .content("Test content")
                .userDto(
                        UserDto.builder()
                                .name("Test name")
                                .age(0)
                                .hobby("Test hobby")
                                .build()
                )
                .build();
        //When
        postService.save(postDto);

        //Then
        assertThat(postRepository.findAll().isEmpty(), is(false));
    }

    @Test
    void findOneTest() throws NotFoundException {
        //When
        PostDto post = postService.findOne(1L);

        //Then
        assertThat(post.getId()).isEqualTo(postDto.getId());

    }

    @Test
    void findAllTest() {
        //Given
        PageRequest page = PageRequest.of(0, 10);

        //When
        Page<PostDto> all = postService.findAll(page);

        //Then
        assertThat(all.getTotalElements()).isEqualTo(3);

    }

    @Test
    void updateTest() throws NotFoundException {
        //Given
        postDto.setTitle("update title");
        postDto.setContent("update content");

        //When
        postService.update(postDto, 1L);
        var post = postService.findOne(1L);

        //Then
        assertThat(post.getTitle()).isEqualTo(postDto.getTitle());
    }


}