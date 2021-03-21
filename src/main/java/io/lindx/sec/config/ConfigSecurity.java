package io.lindx.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
    http
      .authorizeRequests()
        .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/users").access("hasAnyRole('ADMIN')")
        .anyRequest().authenticated();
    
    http
      .formLogin()
        .loginPage("/login").permitAll()
        .loginProcessingUrl("/login/process")
          .usernameParameter("email")
          .passwordParameter("password");
      
    http
      .logout()
        .logoutUrl("/logout");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth
      .inMemoryAuthentication()
        .withUser("user@user")
        .password(passwordEncoder().encode("user"))
        .roles("USER");

    auth
      .inMemoryAuthentication()
        .withUser("admin@admin")
        .password(passwordEncoder().encode("admin"))
        .roles("ADMIN");
  }

  // Применяем шифрование 
  @Bean
  public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
