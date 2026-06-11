package com.system.moviebooking.controller;

import com.system.moviebooking.dto.LoginRequest;
import com.system.moviebooking.model.User;
import com.system.moviebooking.repository.UserRepository;
import com.system.moviebooking.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userRepository.findByUserEmail(request.getEmail());

        System.out.println("Request Email = " + request.getEmail());
        System.out.println("Request Password = " + request.getPassword());

        if(user != null){
            System.out.println("DB Email = " + user.getUserEmail());
            System.out.println("DB Password = " + user.getUserPwd());
        }

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getUserPwd())) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(user.getUserEmail());
    }
}
