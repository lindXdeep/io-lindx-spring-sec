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
@ComponentScan("io.lindx.sec.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthProviderImpl authenticationProvider;

  /**
   * В этом методе описывается разделение доступа к хэндлерам.
   */
  @Override
  protected void configure(final HttpSecurity http) throws Exception {

    http
        .authorizeRequests()
              .antMatchers("/sing_up", "/login").anonymous()
              .antMatchers("/users").authenticated()
        
        .and().csrf().disable()

        .formLogin()
              .loginPage("/login")
              .loginProcessingUrl("/login/process")
              .usernameParameter("email")
              .failureUrl("/login?error=true")

        .and()
              .exceptionHandling().accessDeniedPage("/users")

        .and()
              .logout();
  }
  
  /**
   * Builder Authentication Provider.
   */
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  /**
   * @return BCryptPasswordEncoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
