package com.jinseong.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jinseong.backend.Model.User;
import com.jinseong.backend.auth.JwtUtils;
import com.jinseong.backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public void signup(User user) {
        User saveUser = new User();
        saveUser.setEmail(user.getEmail());
        saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(saveUser);
    }

    public String login(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        
        User findUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if (!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }

        return jwtUtils.generateToken(user.getEmail());
    }
}   