package com.alvegag.transcendence.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvegag.transcendence.dto.LoginRequest;
import com.alvegag.transcendence.dto.LoginResponse;
import com.alvegag.transcendence.dto.RegisterRequest;
import com.alvegag.transcendence.dto.RegisterResponse;
import com.alvegag.transcendence.entity.User;
import com.alvegag.transcendence.repository.UserRepository;

@RestController
public class AuthController {

  private final UserRepository userRepository;

  public AuthController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {

    RegisterResponse res = new RegisterResponse();

    if (!request.getPassword().equals(request.getConfirmPassword())) {
      res.setSuccess(false);
      res.setMessage("Passwords are not equal");
      return ResponseEntity.badRequest().body(res);
    }

    Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
    if (existingUser.isPresent()) {
      res.setSuccess(false);
      res.setMessage("User already exists");
      return ResponseEntity.badRequest().body(res);
    }

    User user = new User(request.getEmail(), request.getUsername(), request.getPassword());
    userRepository.save(user);

    res.setSuccess(true);
    res.setMessage("Registration successful");

    return ResponseEntity.ok(res);

  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

    LoginResponse res = new LoginResponse();

    Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
    if (!existingUser.isPresent()) {
      res.setSuccess(false);
      res.setMessage("User not found");
      return ResponseEntity.badRequest().body(res);
    }
    User user = existingUser.get();
    if (!user.getPassword().equals(request.getPassword())) {
      res.setSuccess(false);
      res.setMessage("Incorrect password");
      return ResponseEntity.badRequest().body(res);
    }

    res.setSuccess(true);
    res.setMessage("Login successful");

    return ResponseEntity.ok(res);
  }
}
