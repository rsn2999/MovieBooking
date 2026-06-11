package com.system.moviebooking.service;

import com.system.moviebooking.model.User;
import com.system.moviebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUserDetails(User user) {

        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setUserName(updatedUser.getUserName());
            user.setUserPwd(updatedUser.getUserPwd());
            user.setUserEmail(updatedUser.getUserEmail());
            return userRepository.save(user);
        }

        return null;
    }


    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}