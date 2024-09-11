package com.d2c.template.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSessionDto {

  private Integer id;
  private String userId;
  private String lastLogin;
  private String isLoggedIn;
  private Integer sessionExpirationTime;

}
