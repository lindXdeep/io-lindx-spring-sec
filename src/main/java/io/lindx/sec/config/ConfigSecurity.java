package io.lindx.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.lindx.sec.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(value = "io.lindx.sec.security")
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

  protected AuthProviderImpl authProvider;

  @Autowired
  public ConfigSecurity(AuthProviderImpl authProvider) {
    this.authProvider = authProvider;
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
          .failureUrl("/login?error=true");

    http
      .logout()
        .logoutUrl("/logout");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
  }

  // Применяем шифрование 
  @Bean
  public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
