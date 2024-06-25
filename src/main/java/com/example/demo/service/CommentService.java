package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.commentUtils.CommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentService {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentUtils commentUtils;

    public ResponseEntity<?> getComments() {
        return commentRepository.getComments();
    }

    public ResponseEntity<?> getComment(UUID id) {
        return commentRepository.getComment(id);
    }

    public ResponseEntity<?> createComment(Comment comment) {
        if(commentUtils.checkScore(comment.getScore())) {

            UUID restaurantId = comment.getRestaurantId();
            ResponseEntity<?> response = commentRepository.createComment(comment);

            HttpStatusCode code = response.getStatusCode();

            if(code.is2xxSuccessful()) {
                restaurantService.updateRatingRestaurant(restaurantId, comment.getScore());
            }
            return response;
        }
        return ResponseEntity.badRequest().body("Данные введены не корректно");

    }

    public ResponseEntity<?> updateComment(UUID id, Comment comment) {
        if(commentUtils.checkScore(comment.getScore())) {
            return commentRepository.updateComment(id, comment);
        }
        return ResponseEntity.badRequest().body("Данные введены не корректно");
    }

    public ResponseEntity<?> deleteComment(UUID id) {
        return commentRepository.deleteComment(id);
    }

    public ResponseEntity<?> deleteCommentByUserID(UUID userId) {
        return commentRepository.deleteCommentByUserId(userId);
    }



}
