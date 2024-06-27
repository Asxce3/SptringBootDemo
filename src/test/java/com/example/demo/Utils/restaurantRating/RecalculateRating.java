package com.example.demo.Utils.restaurantRating;

import com.example.demo.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLDataException;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecalculateRating {

    @Autowired
    CommentService commentService;

    @Test
    public void RecalculateRatingConfirm() {
        UUID commentId = UUID.fromString( "76001bd8-9eed-4068-8c2d-c960c3b9c126");

        commentService.deleteComment(commentId);

    }

    @Test
    public void RecalculateRatingFailed() {
//      Не верный id комментария для удаления
        UUID commentId = UUID.fromString( "3e46c185-7e5b-4681-8909-64e89afa27bf");
        Assertions.assertEquals("Comment not found", commentService.getComment(commentId).getBody());

    }
}

