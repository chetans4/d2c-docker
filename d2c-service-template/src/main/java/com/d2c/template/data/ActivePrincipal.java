package com.d2c.template.data;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@ToString
@Getter
@Slf4j
public class ActivePrincipal extends User {

  private final String userId;

  @ToString.Exclude
  private String jwt;

  public ActivePrincipal(String userId) {
    super(userId, null, null);
    this.userId = userId;
  }

  public ActivePrincipal(String userId, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(userId, password, authorities);
    this.userId = userId;
  }

  public ActivePrincipal(String claimId, String jwt, List<String> accessList,
                         List<GrantedAuthority> authorities) {
    super(claimId, jwt, authorities);
    this.userId = claimId;
    this.jwt = jwt;
    log.info("this.userId : {}", this.userId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ActivePrincipal principal = (ActivePrincipal) o;
    return getUserId().equals(principal.getUserId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getUserId());
  }
}
