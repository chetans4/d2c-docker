package com.d2c.template.data.repository;

import com.d2c.template.data.entity.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByUserId(String userId);

}
