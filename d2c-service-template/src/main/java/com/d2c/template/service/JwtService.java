package com.d2c.template.service;

import com.d2c.template.data.ActivePrincipal;
import com.d2c.template.data.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface JwtService {

  String extractUsername(String token);

  Object extractValueFromClaim(String token, String key);

  Claims extractAllClaims(String token);

  /**
   * Test method
   *
   * @param user
   * @return
   */
  @Deprecated
  String generateToken(User user);

  boolean isTokenValid(String token, ActivePrincipal principal);

  UsernamePasswordAuthenticationToken getAuthentication(String jwt);
}
