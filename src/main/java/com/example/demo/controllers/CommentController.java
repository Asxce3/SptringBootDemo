package com.example.demo.controllers;

import com.example.demo.model.Comment;
import com.example.demo.model.Restaurant;
import com.example.demo.service.CommentService;
import com.example.demo.service.RestaurantService;
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
    public ResponseEntity<?> getUsers(){
        return commentService.getComments();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody() Comment comment){
        return commentService.createComment(comment);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam() UUID uuid, @RequestBody Comment comment) {
        return commentService.updateComment(uuid, comment);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam() UUID uuid ){
        return commentService.deleteComment(uuid);
    }
}
