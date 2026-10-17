package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtService;
import com.example.demo.service.UserService;
import com.example.demo.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }
    
    

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        try {

            User loggedInUser = userService.loginUser(user);

            String token = jwtService.generateToken(loggedInUser);

            Map<String, String> response = new HashMap<>();

            response.put("token", token);
            response.put("role", loggedInUser.getRole());

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }
    }
  
    
   
}