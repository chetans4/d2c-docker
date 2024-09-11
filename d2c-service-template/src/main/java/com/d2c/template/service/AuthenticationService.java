package com.d2c.template.service;


import com.d2c.template.dto.request.UserDto;

public interface AuthenticationService {

  String generateToken(UserDto request);
}
