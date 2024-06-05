package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.commentUtils.CommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentUtils commentUtils;

    public ResponseEntity<?> getComments() {
        return commentRepository.getComments();
    }

    public ResponseEntity<?> createComment(Comment comment) {
        if(commentUtils.checkScore(comment.getScore())) {
            return commentRepository.createComment(comment);
        }
        return ResponseEntity.badRequest().body("Данные введены не корректно");

    }

    public ResponseEntity<?> updateComment(UUID uuid, Comment comment) {
        if(commentUtils.checkScore(comment.getScore())) {
            return commentRepository.updateComment(uuid, comment);
        }
        return ResponseEntity.badRequest().body("Данные введены не корректно");
    }

    public ResponseEntity<?> deleteComment(UUID uuid) {
        return commentRepository.deleteComment(uuid);
    }

    public ResponseEntity<?> deleteCommentByUserID(UUID uuid) {
        return commentRepository.deleteCommentByUserId(uuid);
    }


}
