package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.repository.DAO.CommentDAO;
import com.example.demo.repository.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;

@Component
public class CommentRepository {

    @Autowired
    CommentDAO commentDAO;

    public ResponseEntity<?> getComments() {

        try {
            List<Comment> comments = commentDAO.getComments();
            return ResponseEntity.status(200).body(comments);

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> createComment(Comment comment) {

        try {
            commentDAO.createComment(comment);
            return ResponseEntity.status(201).build();

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateComment(UUID uuid, Comment comment) {

        try {
            commentDAO.updateComment(uuid, comment);
            return ResponseEntity.status(200).build();

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteComment(UUID uuid) {

        try {
            commentDAO.deleteComment(uuid);
            return ResponseEntity.status(200).build();

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteCommentByUserId(UUID uuid) {

        try {
            commentDAO.deleteCommentByUserId(uuid);
            return ResponseEntity.status(200).build();

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
