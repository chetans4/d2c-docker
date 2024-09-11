package com.d2c.template.controller;

import com.d2c.template.dto.request.UserDto;
import com.d2c.template.service.AuthenticationService;
import com.d2c.template.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller to validate generate token and validation
 */
@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @Deprecated
  @PostMapping("/generate")
  public ResponseEntity<String> register(@RequestBody UserDto request) {
    return ResponseEntity.ok(service.generateToken(request));
  }

  @GetMapping("/warmup")
  public ResponseEntity<String> warmup() {
    return ResponseEntity.ok(DateUtil.NOW.toString());
  }

}
