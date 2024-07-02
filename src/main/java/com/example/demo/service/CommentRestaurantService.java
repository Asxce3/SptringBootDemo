package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.service.commentUtils.RecalculateRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentRestaurantService {
    @Autowired
    private CommentService commentService;

    @Autowired
    private RestaurantService restaurantService;

    public ResponseEntity<?> createComment(Comment comment) {
        try {
            commentService.createComment(comment);
            UUID restaurantId = comment.getRestaurantId();
            restaurantService.updateRatingRestaurant(restaurantId, comment.getScore());
            return ResponseEntity.ok().build();

        }   catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> deleteComment(UUID id) {
        try {
            RecalculateRate rate = (RecalculateRate) commentService.deleteComment(id).getBody();
            restaurantService.recalculateRating(rate.getRestaurantId(), rate.getScore(), rate.getSumScore());
            return ResponseEntity.ok().build();

        }   catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }


    public ResponseEntity<?> deleteRestaurant(UUID id) {
        try {
            commentService.deleteCommentByRestaurantId(id);
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.ok().build();

        }   catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }


    }
}