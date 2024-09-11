package com.d2c.template.configuration.filter;

import com.d2c.template.data.ActivePrincipal;
import com.d2c.template.util.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile({"test", "local"})
public class InMemoryAuthenticationFilter extends AuthenticationFilter {

  private final UserDetailsService inMemoryUsers;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    UserDetails user = inMemoryUsers.loadUserByUsername("ADMIN");
    log.info("identified user : {}", user);
    ActivePrincipal principal = new ActivePrincipal(user.getUsername(), user.getPassword(),
        user.getAuthorities());
    var authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(),
        principal.getAuthorities());

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }

}
