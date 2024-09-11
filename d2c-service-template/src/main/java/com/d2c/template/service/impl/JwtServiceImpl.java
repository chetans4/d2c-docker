package com.d2c.template.service.impl;

import com.d2c.template.configuration.ApplicationPropertiesConfig;
import com.d2c.template.data.ActivePrincipal;
import com.d2c.template.data.entity.User;
import com.d2c.template.exception.ApplicationException;
import com.d2c.template.exception.InvalidTokenException;
import com.d2c.template.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

  @Autowired
  private ApplicationPropertiesConfig properties;

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public Object extractValueFromClaim(String token, String key) {
    try {
      Function<Claims, Object> claimsResolver = claims -> claims.get(key);
      Object value = extractClaim(token, claimsResolver);
      log.info("Value for key : {} is {}", key, value);
      return value;
    } catch (ExpiredJwtException e) {
      log.warn("Token is expired", e);
      throw new InvalidTokenException(e);
    } catch (Exception e) {
      log.error("Error while extracting User Id from Token", e);
      throw new ApplicationException(e);
    }
  }


  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    log.info("claims : {}", claims);
    return claimsResolver.apply(claims);
  }

  /**
   * This method is used to extract claims from given jwt
   *
   * @param token jwt
   * @return claims object
   */
  @Override
  public Claims extractAllClaims(String token) {
    try {
      return Jwts.parserBuilder().build()
          .parseClaimsJwt(token.substring(0, token.lastIndexOf('.') + 1)).getBody();
    } catch (Exception ex) {
      log.error("Error while parsing JWT token. ", ex);
      throw new InvalidTokenException("Invalid or expired token", ex);
    }

  }

  /**
   * Test methods
   *
   * @param user
   * @return
   */
  @Override
  @Deprecated
  public String generateToken(User user) {
    return generateToken(new HashMap<>(), user);
  }

  @Deprecated
  public String generateToken(Map<String, Object> extraClaims, User user) {
    return Jwts.builder().setClaims(extraClaims).setSubject(user.getUserId())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).compact();
  }

  @Override
  public boolean isTokenValid(String token, ActivePrincipal principal) {
    final String username = extractUsername(token);
    return (username.equals(principal.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  @Override
  public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
    List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(getAuthorities(jwt));
    ActivePrincipal principal = new ActivePrincipal(
        (String) this.extractValueFromClaim(jwt, "claim_id"), jwt,
        (List<String>) extractValueFromClaim(jwt, "access_list"),
        authorityList);
    return new UsernamePasswordAuthenticationToken(principal, jwt, authorityList);
  }

  /**
   * This method creates authorities array
   *
   * @param jwt
   * @return array of authorities for user
   */
  private String[] getAuthorities(String jwt) {
    List<String> permissions = (List<String>)extractValueFromClaim(jwt, "permission_list");
    return permissions.toArray(new String[0]);
  }
}
