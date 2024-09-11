package com.d2c.template.configuration.filter;

import com.d2c.template.exception.TokenRequiredException;
import com.d2c.template.service.JwtService;
import com.d2c.template.util.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile({"dev", "sit", "uat", "prod"})
public class JwtAuthenticationFilter extends AuthenticationFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    final String jwt = this.getJWT(request);
    if (StringUtil.isNotBlank(jwt)) {
      this.validateToken(jwt, request, response);
    } else if ("Public".equalsIgnoreCase(request.getHeader("Auth-Type"))) {
      this.enablePublicAuth(request);
    } else {
      throw new TokenRequiredException("Token not found in request");
    }
    filterChain.doFilter(request, response);
  }

  public String getJWT(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    String authToken = null;
    if (header != null && header.startsWith("Bearer ")) {
      authToken = header.replace("Bearer ", "");
    }
    return authToken;
  }

  private void validateToken(String jwt, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    log.info("Taken Received in filter");
    Object userIdObj = jwtService.extractValueFromClaim(jwt, "user_id");
    final String userId = Objects.nonNull(userIdObj) ? userIdObj.toString() : null;
    log.info("userId from jwt : {}", userId);
    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UsernamePasswordAuthenticationToken authToken = jwtService.getAuthentication(jwt);
      log.info("authToken : {}", authToken);

      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);

    }
  }

  /**
   * TODO: Think if we need In memory Auth here
   *
   * @param request
   */
  private void enablePublicAuth(HttpServletRequest request) {
    var authentication = new UsernamePasswordAuthenticationToken(null, null,
        AuthorityUtils.createAuthorityList());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

}
