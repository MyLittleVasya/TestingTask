package com.example.testtask.config;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Global web security config.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final PasswordEncoder encoder;

  /**
   * SecurityFilterChain bean definition. Defines core security logic of this app.
   *
   * @param http object to configure security.
   * @return SecurityFilterChain instance.
   * @throws Exception can be thrown during http build.
   */
  @Bean
  public SecurityFilterChain filterChain(@Nonnull final HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/products/create")).hasRole("MANAGER")
        .requestMatchers(AntPathRequestMatcher.antMatcher("/products/**"))
        .hasAnyRole("CLIENT, MANAGER")
        .requestMatchers(AntPathRequestMatcher.antMatcher("/orders/create")).hasRole("CLIENT")
        .requestMatchers(AntPathRequestMatcher.antMatcher("/orders/pay/**")).hasRole("CLIENT")
        .requestMatchers(AntPathRequestMatcher.antMatcher("/orders/**"))
        .hasAnyRole("CLIENT", "MANAGER")
        .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/welcome")).permitAll()
        .and()
        .formLogin().successForwardUrl("/welcome")
        .and()
        .logout();
    return http.build();
  }

  /**
   * UserDetailsService bean definition. Used for initializing in memory users with roles.
   *
   * @return {@link InMemoryUserDetailsManager} instance.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    final var encodedPassword = encoder.encode("1");
    UserDetails user =
        User.builder()
            .username("user")
            .password(encodedPassword)
            .roles("CLIENT")
            .build();

    UserDetails manager =
        User.builder()
            .username("manager")
            .password(encodedPassword)
            .roles("MANAGER")
            .build();

    return new InMemoryUserDetailsManager(user, manager);
  }
}
