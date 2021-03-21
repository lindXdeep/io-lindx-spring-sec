package io.lindx.sec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.lindx.sec.service.UserService;

@Controller
public class MainController {

  @Autowired
  private UserService userService;

  /**
   * all users.
   * 
   * @return users page.
   */
  @GetMapping("/")
  public String home() {
    return "redirect:/users";
  }

  /**
   * Message.
   * 
   * @param name
   * @param model
   * @return message for user.
   */
  @GetMapping("/user/{name}")
  public String view(@PathVariable("name") final String name, final Model model) {
    model.addAttribute("msg", "Hello " + name + "!");
    return "/index";
  }

  /**
   * User by Id.
   * 
   * @param id
   * @param model
   * @return user.
   */
  @GetMapping("/user")
  public String user(@RequestParam(value = "id", defaultValue = "1") final  Integer id, final Model model) {
    model.addAttribute("user", userService.getUser(id));
    return "main";
  }

  /**
   * All users.
   * 
   * @param model
   * @return List<User>.
   */
  @GetMapping("/users")
  public String getUsers(final Model model) {
    model.addAttribute("users", userService.getAll());
    return "users";
  }
}
