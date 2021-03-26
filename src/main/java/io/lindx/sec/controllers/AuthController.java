package io.lindx.sec.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

import io.lindx.sec.models.Role;
import io.lindx.sec.models.User;
import io.lindx.sec.service.UserService;

@Controller
public class AuthController {

  private UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Sing up.
   * @param model
   * @return Sing up page.
   */
  @GetMapping("/sign_up")
  public String getSignUp(Model model) {
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
     
    Role role = new Role();
    role.setTitle("ROLE_USER");

    user.setRoles(Collections.singleton(role));

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
  public String login(@RequestParam(name = "error",required = false) final Boolean error, final Model model) {

    if(Boolean.TRUE.equals(error)){
      model.addAttribute("error", true);
    }

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

  @GetMapping("/setadmin")
  public String setadmin(
                      @RequestParam(name = "m", required = false) String email,
                      @RequestParam(name = "p", required = false) String password
  ) {

    Role role = new Role();
    role.setTitle("ROLE_ADMIN");

    User admin = new User();
        admin.setUsername("admin");
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRoles(Collections.singleton(role));

    userService.setUser(admin);

    return "redirect:/";
  }
}

  

