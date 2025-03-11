package com.project.questapp.services;

import com.project.questapp.dtos.requests.LikeCreateRequest;
import com.project.questapp.dtos.responses.LikeResponse;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParams(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        } else {
            list = likeRepository.findAll();
        }
        return list.stream().map(LikeResponse::new).collect(Collectors.toList());
    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUserById(likeCreateRequest.getUserId());
        Post post = postService.getPostById(likeCreateRequest.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        } else {
            return null;
        }
    }

    public void deleteLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
