package io.lindx.sec.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.lindx.sec.models.User;
import io.lindx.sec.service.UserService;

/**
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-11
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Инкапсулирует в себе данные которые ползователь предоставит с формы.
   */
  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

    String email = authentication.getName();
    User user = userService.getByMail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    String password = authentication.getCredentials().toString();

    if (!password.equals(user.getPassword())) {
      throw new BadCredentialsException("Bad credentials");
    }

    // if(!passwordEncoder.matches(password, user.getPassword())){
    // System.out.println("Authentication: password:" + "Bad credentials");
    // throw new BadCredentialsException("Bad credentials");
    // }

    List<GrantedAuthority> authorities = new ArrayList<>();
    return new UsernamePasswordAuthenticationToken(user, null, authorities);
  }

  /**
   * Служебный метод.
   */
  @Override
  public boolean supports(final Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
