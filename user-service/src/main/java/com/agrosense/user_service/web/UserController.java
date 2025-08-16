package com.agrosense.user_service.web;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrosense.user_service.repo.UserRepository;
import com.agrosense.user_service.web.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserRepository users;

  @GetMapping("/me")
  public UserResponse me(Authentication auth) {
    var u = users.findByEmail(auth.getName()).orElseThrow();
    return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole().name());
  }
}

