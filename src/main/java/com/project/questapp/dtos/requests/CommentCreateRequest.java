package com.project.questapp.dtos.requests;

public class CommentCreateRequest {
    Long id;
    Long userId;
    Long postId;
    String text;

    public CommentCreateRequest() {
    }

    public CommentCreateRequest(Long userId, Long postId, String text) {
        this.userId = userId;
        this.postId = postId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
