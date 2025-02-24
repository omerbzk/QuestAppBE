package com.project.questapp.controllers;

import com.project.questapp.dtos.requests.LikeCreateRequest;
import com.project.questapp.dtos.responses.LikeResponse;
import com.project.questapp.entities.Like;
import com.project.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
                                          @RequestParam Optional<Long> postId) {
        return likeService.getAllLikesWithParams(userId, postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createLike(likeCreateRequest);
    }

    @GetMapping("/likeId")
    public Like getLikeById(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping("/likeId")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLikeById(likeId);
    }

}
