package io.lindx.sec.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.lindx.sec.models.User;
import io.lindx.sec.service.UserService;

/**
 * 
 * {@link AuthenticationProvider} — механизм, опираясь на который,
 * {@link AuthenticationManager} выполняет фактическую аутентификацию
 * пользователей. Пространство имен предоставляет поддержку нескольких
 * стандартных вариантов, а так же средства добавления пользовательских бинов,
 * объявленных с помощью традиционного синтаксиса.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-11
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

  private UserService userService;
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public AuthProviderImpl(UserService userService, @Qualifier("passwordEncoder") BCryptPasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * {@link Authentication} - представляет принципала (пользователя авторизованной
   * сессии) т.е Инкапсулирует в себе данные которые ползователь предоставляет с
   * формы.
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
   
    String email = authentication.getName();
    User user = userService.getByMail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    String password = authentication.getCredentials().toString();
    if(!passwordEncoder.matches(password, user.getPassword())){
      throw new BadCredentialsException("Bad credentials");
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
  
    return new UsernamePasswordAuthenticationToken(user, null, authorities);
  }

  /**
   * Служебный метод.
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
