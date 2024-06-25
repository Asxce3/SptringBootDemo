package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.repository.DAO.ObjectDAO;
import com.example.demo.repository.DAO.postgres.CommentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CommentRepository {

    @Autowired
    CommentDAOImpl commentDAO;

    public ResponseEntity<?> getComments() {

        try {
            List<Comment> comments = commentDAO.getMany();
            return ResponseEntity.status(200).body(comments);

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getComment(UUID id) {

        try {
            Optional<Comment> comments = commentDAO.getOne(id);
            return ResponseEntity.status(200).body(comments.get());

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
            commentDAO.create(comment);
            return ResponseEntity.status(201).build();

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateComment(UUID id, Comment comment) {

        try {
            commentDAO.update(id, comment);
            return ResponseEntity.status(200).build();

        }   catch (CannotGetJdbcConnectionException e) {

            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteComment(UUID id) {

        try {
            commentDAO.delete(id);
            return ResponseEntity.status(200).build();

        }   catch (CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }   catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteCommentByUserId(UUID id) {

        try {
            commentDAO.deleteCommentByUserId(id);
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
