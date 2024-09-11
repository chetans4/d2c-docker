package com.d2c.template.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationPropertiesConfig {

  @Value("${jwt.secret.key:this-is-secret-key}")
  private String jwtSecretKey;

}
