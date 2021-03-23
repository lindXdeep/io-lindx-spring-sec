package io.lindx.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.lindx.sec.security.SuccessUserHandler;

import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@EnableWebSecurity
@ComponentScan(value = "io.lindx.sec")
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final SuccessUserHandler successUserHandler;

  @Autowired
  public ConfigSecurity(@Qualifier("userDetailsService") UserDetailsService userDetailsService, 
                                                         SuccessUserHandler successUserHandler) {
    this.userDetailsService = userDetailsService;
    this.successUserHandler = successUserHandler;
  }

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
          .passwordParameter("password")
          .defaultSuccessUrl("/", true)
          .failureUrl("/login?error=true")
          .successHandler(successUserHandler);

    http
      .logout()
        .logoutUrl("/logout");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  // Применяем шифрование 
  @Bean
  public static PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
  }
}
