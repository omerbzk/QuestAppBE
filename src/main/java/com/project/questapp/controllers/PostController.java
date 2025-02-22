package com.project.questapp.controllers;

import com.project.questapp.dtos.requests.PostCreateRequest;
import com.project.questapp.dtos.requests.PostUpdateRequest;
import com.project.questapp.entities.Post;
import com.project.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostCreateRequest) {
        return postService.createPost(newPostCreateRequest);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updatePost(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
    }



}
