package com.project.questapp.controllers;

import com.project.questapp.dtos.requests.CommentCreateRequest;
import com.project.questapp.dtos.requests.CommentUpdateRequest;
import com.project.questapp.entities.Comment;
import com.project.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParams(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateCommentById(commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
    }

}
