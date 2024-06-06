package com.example.demo.repository.DAO;

import com.example.demo.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentDAO {
    List<Comment> getComments();

    void createComment (Comment comment) throws Exception;

    void updateComment (UUID id, Comment comment) throws Exception;

    void deleteComment (UUID id) throws Exception;

    void deleteCommentByUserId (UUID id) throws Exception;

}
