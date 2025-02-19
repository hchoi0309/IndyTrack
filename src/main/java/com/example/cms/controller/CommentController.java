package com.example.cms.controller;

import com.example.cms.model.entity.Comment;
import com.example.cms.model.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/comments")
    List<Comment> retrieveAllComment() {
        return repository.findAll();
    }
}
