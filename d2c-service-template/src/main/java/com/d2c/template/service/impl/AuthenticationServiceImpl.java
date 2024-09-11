package com.d2c.template.service.impl;

import com.d2c.template.service.AuthenticationService;
import com.d2c.template.service.JwtService;
import com.d2c.template.service.UserService;
import com.d2c.template.data.entity.User;
import com.d2c.template.dto.request.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserService userService;
  private final JwtService jwtService;

  @Override
  public String generateToken(UserDto request) {
//    User user = userService.findByUserId(request.getUserId());
    User user = User.builder().userId("DummyUserId").build();
    log.info("User fetched by userId : {}", user);
    return jwtService.generateToken(user);
  }

}
