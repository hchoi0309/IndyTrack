package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.cms.model.entity.Admin;
import com.example.cms.model.entity.Student;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.StudentRepository;
import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.repository.AdminRepository;
import com.example.cms.controller.dto.LoginDto;
import com.example.cms.controller.dto.UserDto;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public User register(@RequestBody UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use");
        }
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        
        User user;
        if (userDto.getRole().equalsIgnoreCase("student")) {
            user = new Student();
        } else if (userDto.getRole().equalsIgnoreCase("admin")) {
            user = new Admin();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user type");
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(hashedPassword);
        user.setStatus("unverified");
        return userRepository.save(user);
        // TODO: send verification email
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        User user = userOptional.get();
        
        // TODO: remove this once we have a verification process
        // if (!"verified".equals(user.getStatus())) {
        //     throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Account has not been verified");
        // }

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
        return user;
    }

}
