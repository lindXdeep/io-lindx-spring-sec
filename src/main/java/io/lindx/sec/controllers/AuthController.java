package io.lindx.sec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
  public String login(@RequestParam(name = "error", required = false) final Boolean error, final Model model) {

    if (Boolean.TRUE.equals(error)) {
      model.addAttribute("error", true);
    }
    return "sign_in";
  }
}
