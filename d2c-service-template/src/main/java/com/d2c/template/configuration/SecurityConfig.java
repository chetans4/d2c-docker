package com.d2c.template.configuration;

import com.d2c.template.configuration.filter.AuthenticationFilter;
import com.d2c.template.exception.handler.JwtAuthenticationFailureHandler;
import io.swagger.models.HttpMethod;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

  @Value("${localhost.origin:http://localhost:8081}")
  private String localhostOrigin;

  @Value("${response.maxage:1800}")
  private Long responseMaxAge;

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(localhostOrigin));
    configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(),
        HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(),
        HttpMethod.OPTIONS.name()));
    configuration.setMaxAge(responseMaxAge);
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(true);
    configuration
        .setExposedHeaders(
            Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain enableSecurity(HttpSecurity http,
      AuthenticationFilter authenticationFilter,
      AuthenticationProvider authenticationProvider,
      JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler) throws Exception {
    log.info("authenticationFilter : {}", authenticationFilter);

    http
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/api/auth/**", "/v1/change-password")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(
            exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, exception) -> {
                  jwtAuthenticationFailureHandler.handleAuthFailure(request, response, exception);
                })
        )
        .exceptionHandling(exceptionHandling ->
            exceptionHandling.accessDeniedHandler(jwtAuthenticationFailureHandler)
        );

    return http.build();
  }

}
