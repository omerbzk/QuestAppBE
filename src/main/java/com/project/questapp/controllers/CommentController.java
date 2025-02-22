package com.project.questapp.controllers;

import com.project.questapp.services.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    private CommentService commentService;
}
