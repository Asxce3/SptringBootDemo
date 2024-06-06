package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.Comment;
import com.example.demo.repository.DAO.CommentDAO;
import com.example.demo.repository.DAO.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = jdbcTemplate.query
                ("SELECT * FROM comment", new BeanPropertyRowMapper<>(Comment.class));
        return comments;
    }

    @Override
    public void createComment(Comment comment) throws Exception{
        try {
            jdbcTemplate.update("INSERT INTO comment VALUES (?, ?, ?, ?, ?)",
                    UUIDGenerate.generateID(),
                    comment.getPersonId(),
                    comment.getRestaurantId(),
                    comment.getComment(),
                    comment.getScore());

        } catch (DataIntegrityViolationException e) {
            throw new Exception("Fields don`t be null");

        } catch (Exception e) {
            e.printStackTrace();
//            throw new Exception("User already post comment");
        }
    }

    @Override
    public void updateComment(UUID id, Comment comment) throws Exception {
        int i = jdbcTemplate.update("UPDATE comment SET comment = ?, score = ? WHERE id = ?",
                comment.getComment(),
                comment.getScore(), id);

        if (i != 1) {
            throw new Exception("Comment not found");
        }
    }

    @Override
    public void deleteComment(UUID id) throws Exception {

        int i = jdbcTemplate.update("DELETE FROM comment WHERE id = ?", id);

        if (i != 1) {
            throw new Exception("Comment not found");
        }
    }

    public void deleteCommentByUserId(UUID personId) throws Exception {
        int i = jdbcTemplate.update("DELETE FROM comment WHERE personid = ?", personId);

        if (i != 1) {
            throw new Exception("User dont have comment to delete");
        }
    }


}
