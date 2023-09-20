package com.example.testtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for PasswordEncoder bean.
 */
@Configuration
public class PasswordEncoderConfig {

  /**
   * Password encoder bean definition.
   *
   * @return instance of BCryptPasswordEncoder.
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
