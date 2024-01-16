package com.codersbay.backendfinalproject.service;

import com.codersbay.backendfinalproject.exception.UserNotFoundException;
import com.codersbay.backendfinalproject.model.Post;
import com.codersbay.backendfinalproject.model.User;
import com.codersbay.backendfinalproject.repository.PostRepository;
import com.codersbay.backendfinalproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createNewPost(Long userId, Post post) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        post.setUser(user);
        postRepository.save(post);
        user.getPosts().add(post);
        userRepository.save(user);
        return post;
    }
}
