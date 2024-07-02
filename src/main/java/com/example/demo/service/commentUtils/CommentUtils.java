package com.example.demo.service.commentUtils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentUtils {

    public boolean checkScore(int score) {
        if (0 < score && score < 6) {
            return true;
        }
        return false;
    }


}


