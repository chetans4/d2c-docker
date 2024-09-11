package com.d2c.template.configuration.filter;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class D2CAuthenticationProvider implements AuthenticationProvider {

  /**
   * Add custom authentication if required.
   *
   * @param authentication the authentication request object.
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {

    log.info("authentication value CustomAuthenticationProvider : {}", authentication);

    String name = authentication.getName();
    String password = authentication.getCredentials().toString();
    return new UsernamePasswordAuthenticationToken(
        name, password, new ArrayList<>());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}