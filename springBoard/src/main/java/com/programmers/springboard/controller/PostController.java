package com.programmers.springboard.controller;

import com.programmers.springboard.config.ApiResponse;
import com.programmers.springboard.dto.PostDto;
import com.programmers.springboard.dto.PostResponseDto;
import com.programmers.springboard.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ApiResponse<Long> save(@RequestBody PostDto postDto) {
        Long id = postService.save(postDto);
        return ApiResponse.ok(id);
    }

    @GetMapping("/posts/{id}")
    public ApiResponse<PostResponseDto> getOne(@PathVariable Long id) {
        PostResponseDto one = postService.findOne(id);
        return ApiResponse.ok(one);
    }

    @GetMapping("/posts")
    public ApiResponse<Page<PostResponseDto>> getAll(Pageable pageable) {
        Page<PostResponseDto> all = postService.findAll(pageable);
        return ApiResponse.ok(all);
    }


    @PatchMapping("/posts/{id}")
    public ApiResponse<Long> update(@PathVariable Long id, @RequestBody PostDto postDto) {
        Long postId = postService.update(postDto, id);
        return ApiResponse.ok(postId);
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
