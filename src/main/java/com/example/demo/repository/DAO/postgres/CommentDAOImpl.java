package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.Comment;
import com.example.demo.repository.DAO.ObjectDAO;
import com.example.demo.repository.DAO.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommentDAOImpl implements ObjectDAO<Comment>, {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> getMany() {
        List<Comment> comments = jdbcTemplate.query
                ("SELECT * FROM comment", new BeanPropertyRowMapper<>(Comment.class));
        return comments;
    }

    @Override
    public Comment getOne(UUID id) throws Exception{
        Comment comment = jdbcTemplate.query
                ("SELECT * FROM comment WHERE id = ?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Comment.class)).stream().findAny().orElse(null);
        return comment;
    }

    @Override
    public void create(Comment comment) throws Exception{
        try {
            jdbcTemplate.update("INSERT INTO comment VALUES (?, ?, ?, ?, ?)",
                    UUIDGenerate.generateID(),
                    comment.getPersonId(),
                    comment.getRestaurantId(),
                    comment.getComment(),
                    comment.getScore());

        } catch (DuplicateKeyException e) {
            e.printStackTrace();

            throw new Exception("User already post comment");

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
//            ПРИ НЕ ПРАВИЛЬНО ВВЕДЕННЫХ ДАННЫХ ВЫБРАСЫВАЕТ ЭТУ ОШИБКУ
            throw new Exception("Some fields are empty");
        }
    }

    @Override
    public void update(UUID id, Comment comment) throws Exception {
        int i = jdbcTemplate.update("UPDATE comment SET comment = ?, score = ? WHERE id = ?",
                comment.getComment(),
                comment.getScore(), id);

        if (i != 1) {
            throw new Exception("Comment not found");
        }
    }

    @Override
    public void delete(UUID id) throws Exception {

        int i = jdbcTemplate.update("DELETE FROM comment WHERE id = ?", id);

        if (i != 1) {
            throw new Exception("Comment not found");
        }
    }

    public void deleteCommentByUserId(UUID personId) throws Exception {
        int i = jdbcTemplate.update("DELETE FROM comment WHERE person_id = ?", personId);

        if (i != 1) {
            throw new Exception("User dont have comment to delete");
        }
    }


}
