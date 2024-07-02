package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.commentUtils.CommentUtils;
import com.example.demo.service.commentUtils.RecalculateRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public ResponseEntity<?> getComment(UUID id) {
        return commentRepository.getComment(id);
    }

    public ResponseEntity<?> createComment(Comment comment) {
        if(commentUtils.checkScore(comment.getScore())) {
            UUID restaurantId = comment.getRestaurantId();
            return commentRepository.createComment(comment);
        }
        return ResponseEntity.badRequest().body("Данные введены не корректно");

    }

    public ResponseEntity<?> updateComment(UUID id, Comment comment) {
        if(commentUtils.checkScore(comment.getScore())) {
            return commentRepository.updateComment(id, comment);
        }
        return ResponseEntity.badRequest().body("Данные введены не корректно");
    }

    public ResponseEntity<?> deleteComment(UUID id){
        Comment comment = (Comment) getComment(id).getBody();
        UUID restaurantId = comment.getRestaurantId();

        List<Comment> comments = (List<Comment>) commentRepository.getCommentsByRestaurantId(restaurantId).getBody();

        int sumScore = comments.stream().mapToInt(Comment::getScore).sum() + 5;

        RecalculateRate recalculateRate = new RecalculateRate(restaurantId, comment.getScore(), sumScore);
        System.out.println(recalculateRate);

        HttpStatusCode code = commentRepository.deleteComment(id).getStatusCode();

        if(code.is2xxSuccessful()){
            return ResponseEntity.ok().body(recalculateRate);
        }
        return ResponseEntity.status(code).build();
    }

    public ResponseEntity<?> deleteCommentByUserID(UUID userId) {
        return commentRepository.deleteCommentByUserId(userId);
    }

    public ResponseEntity<?> deleteCommentByRestaurantId(UUID restaurantId) {
        return commentRepository.deleteCommentByRestaurantId(restaurantId);
    }



}
