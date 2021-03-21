package io.lindx.sec.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.lindx.sec.models.User;
import io.lindx.sec.service.UserService;

@Controller
public class AuthController {

  @Autowired
  private UserService userService;
  /**
   * Sing up.
   * @param model
   * @return Sing up page.
   */
  @GetMapping("/sign_up")
  public String getSignUp(final Model model) {
    model.addAttribute("user", new User());
    return "sign_up";
  }

  /**
   * Add user.
   * @param user
   * @return all user page
   */
  @PostMapping("/sign_up")
  public String signUp(@ModelAttribute final User user) {
    userService.setUser(user);
    return "redirect:/users";
  }

  /**
   * Sign in page.
   * @param error
   * @param model
   * @return Sign in page.
   */
  @RequestMapping("/login")
  public String login() {
    return "sign_in";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request){

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(auth != null){
      request.getSession().invalidate();
    }
    return "redirect:/sign_in";
  }
}
