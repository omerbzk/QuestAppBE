package com.project.questapp.services;

import com.project.questapp.dtos.requests.PostCreateRequest;
import com.project.questapp.dtos.requests.PostUpdateRequest;
import com.project.questapp.dtos.responses.LikeResponse;
import com.project.questapp.dtos.responses.PostResponse;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    private LikeService likeService;

    // @Lazy annotation is added because of circular references
    public PostService(PostRepository postRepository, UserService userService,@Lazy LikeService likeService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }
        list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParams(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);
        }).collect(Collectors.toList());
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getUserById(newPostCreateRequest.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setText(newPostCreateRequest.getText());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setUser(user);
        toSave.setCreateDate(new Date());
        return postRepository.save(toSave);
    }

    public Post updatePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setTitle(postUpdateRequest.getTitle());
            toUpdate.setText(postUpdateRequest.getText());
            return postRepository.save(toUpdate);
        }
        return null;
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
