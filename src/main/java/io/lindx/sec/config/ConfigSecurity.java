package io.lindx.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
    
    http
      .authorizeRequests()
        
        // делаем доступной только юзером сролью USER
        .antMatchers("/users").hasAnyRole("USER")

        // все отальные запросы доступны только авторизованным юзерам
        .anyRequest().authenticated();
    
    // ннаша кастомная логин форма
    http
      .formLogin()
        .loginPage("/login").permitAll()
        .loginProcessingUrl("/login/process")
          .usernameParameter("email")
          .passwordParameter("password");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .inMemoryAuthentication()
        .withUser("user@user")
        .password(passwordEncoder().encode("user"))
        .roles("USER");
  }

  // В данном примере отключаемшифрование
  @Bean
  public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
