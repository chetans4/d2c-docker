package com.d2c.template.configuration;

import com.d2c.template.configuration.filter.D2CAuthenticationProvider;
import com.d2c.template.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class ApplicationBeansConfig {

  @Bean
  public UserDetailsService inMemoryUsers() {
    UserDetails user = User.builder()
        .username("user")
        .password("{noop}ps@123")
        .roles("USER")
        .build();
    UserDetails admin = User.withUsername("admin")
        .password("{noop}ps@123")
        .roles("USER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public AuthenticationProvider authenticationProvider(
      D2CAuthenticationProvider authenticationProvider) {
    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

}
