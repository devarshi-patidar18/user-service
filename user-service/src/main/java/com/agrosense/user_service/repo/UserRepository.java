package com.agrosense.user_service.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrosense.user_service.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
}
