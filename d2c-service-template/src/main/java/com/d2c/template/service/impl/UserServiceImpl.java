package com.d2c.template.service.impl;

import com.d2c.template.data.entity.User;
import com.d2c.template.data.repository.UserRepository;
import com.d2c.template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public User findByUserId(String userId) {
    return repository.findByUserId(userId)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
