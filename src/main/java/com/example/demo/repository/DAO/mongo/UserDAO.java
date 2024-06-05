//package com.example.demo.DAO;
//
//import com.example.demo.repository.DAO.UUIDGenerate;
//import com.example.demo.model.User;
//import com.example.demo.repository.DAO.UserMongoDAO;
//import com.example.demo.repository.DAO.UserDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//import java.util.List;
//
//@Component
//public class UserDAO implements UserDAO {
//    @Autowired
//    UserMongoDAO userRepository;
//
//
//    public List<User> getUsers() {
//
//            List<User> users = userRepository.findAll();
//            return users;
//    }
//
//    public User getUser(String username) throws Exception {
//            if(userRepository.findUserByName(username) == null) {
//                throw new Exception("User doesn`t exists");
//            }
//            User user = userRepository.findUserByName(username);
//            return user;
//    }
//
//    public void createUser(User user) throws Exception {
//
//            if(userRepository.findUserByName(user.getUsername()) != null) {
//                throw new Exception("User already exists");
//            }
//            user.setUUID(UUIDGenerate.generateID());
//            userRepository.save(user);
//
//        }
//
//    public void updateUser(String username, User user) {
//
//            User editUser = userRepository.findUserByName(username);
//            editUser.setUsername(user.getUsername());
//            editUser.setPassword(user.getPassword());
//
//            userRepository.save(editUser);
//    }
//
//    public void deleteUser(String username) {
//
//            String id = userRepository.findUserByName(username).getUUID().toString();
//
//            userRepository.deleteById(id);
//    }
//
//
//
//}
