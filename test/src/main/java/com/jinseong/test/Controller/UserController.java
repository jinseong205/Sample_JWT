package com.jinseong.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.test.Model.User;
import com.jinseong.test.service.AuthService;
import com.jinseong.test.vo.JwtVO;
import com.jinseong.test.vo.MessageVO;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        authService.signup(user);
        return ResponseEntity.ok(new MessageVO("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = authService.login(user);
        return ResponseEntity.ok(new JwtVO(token));
    }
}