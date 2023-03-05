package com.jinseong.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jinseong.test.Model.User;
import com.jinseong.test.auth.JwtUtils;
import com.jinseong.test.repository.UserRepository;

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
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String login(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        User findUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if (!passwordEncoder.matches(user.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }

        return jwtUtils.generateToken(user.getEmail());
    }
}