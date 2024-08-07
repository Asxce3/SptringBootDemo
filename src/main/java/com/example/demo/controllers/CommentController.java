package com.example.demo.controllers;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/get-many")
    public ResponseEntity<?> getMany(){
        return commentService.getComments();
    }

    @GetMapping("/get-one")
    public ResponseEntity<?> getOne(@RequestParam UUID id){
        return commentService.getComment(id);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody() Comment comment){
        return commentService.createComment(comment);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam() UUID id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam() UUID id ){
        return commentService.deleteComment(id);
    }
}
