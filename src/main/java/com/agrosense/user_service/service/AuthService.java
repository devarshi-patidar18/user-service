package com.agrosense.user_service.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrosense.user_service.domain.Role;
import com.agrosense.user_service.domain.User;
import com.agrosense.user_service.repo.UserRepository;
import com.agrosense.user_service.security.JwtService;
import com.agrosense.user_service.web.dto.AuthResponse;
import com.agrosense.user_service.web.dto.LoginRequest;
import com.agrosense.user_service.web.dto.RegisterRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  @Autowired
  private UserRepository users;
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private JwtService jwt;

  @Transactional
  public AuthResponse register(RegisterRequest req) {
    if (users.existsByEmail(req.email())) throw new IllegalArgumentException("Email already used");
    var user = User.builder()
        .name(req.name())
        .email(req.email())
        .passwordHash(encoder.encode(req.password()))
        .role(Role.FARMER)
        .build();
    users.save(user);
    var token = jwt.generate(user.getEmail(), Map.of("role", user.getRole().name(), "uid", user.getId()));
    return new AuthResponse(token, null);
  }

  public AuthResponse login(LoginRequest req) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.password()));
    var user = users.findByEmail(req.email()).orElseThrow();
    var token = jwt.generate(user.getEmail(), Map.of("role", user.getRole().name(), "uid", user.getId()));
    return new AuthResponse(token, user);
  }
}

