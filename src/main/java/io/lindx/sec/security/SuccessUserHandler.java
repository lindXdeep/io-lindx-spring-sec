package io.lindx.sec.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
  /*
   * Spring Security использует объект Authentication, пользователя авторизованной
   * сессии.
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, 
                                      HttpServletResponse response,
                                      Authentication authentication
                                      ) throws IOException, ServletException {

    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

    if (roles.contains("ROLE_USER")) {
      response.sendRedirect("/user?id=1");
    } else {
      response.sendRedirect("/");
    }

  }
}