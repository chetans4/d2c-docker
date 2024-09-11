package com.d2c.template;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class D2CServiceApplication {

  /**
   * Run application with Default timezone setting.
   *
   * @param args
   */
  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    SpringApplication.run(D2CServiceApplication.class, args);
  }

}
