package com.codersbay.backendfinalproject.controller;

import com.codersbay.backendfinalproject.model.Post;
import com.codersbay.backendfinalproject.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create-post/{userId}")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post, @PathVariable Long userId) {
        Post newPost = postService.createNewPost(userId, post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
