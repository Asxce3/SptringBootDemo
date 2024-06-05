package com.example.demo.userUtilsTest;

import com.example.demo.model.User;
import com.example.demo.model.UserEdit;
import com.example.demo.service.UserService;
import com.example.demo.service.userUtils.UserUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserUtilsTest {
    @Autowired
    UserService userService;


    @Test
    public void getUserTest() {
        String username = "Samvel1234";

        System.out.println(userService.getUser(username));
    }

    @Test
    public void updateUserTest() {
        String username = "Samvel1234";
        User user = (User) userService.getUser(username).getBody();
//        UserEdit userEdit = new UserEdit("Samvdiaz1@gmail.com", "+77019884492", null);

//        System.out.println(userService.updateUser(username, userEdit));
    }
}
