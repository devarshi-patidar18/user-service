package com.agrosense.user_service.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrosense.user_service.repo.UserRepository;
import com.agrosense.user_service.web.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserRepository usersRepo;

  // @GetMapping("/me")
  // public UserResponse me(Authentication auth) {
  //   var u = users.findByEmail(auth.getName()).orElseThrow();
  //   return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole().name()); 
  // }

  @GetMapping("/{id}")
  public UserResponse getUserById(@PathVariable Long id) {
    var user = usersRepo.findById(id).orElseThrow();
    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole().name());
  }
}

