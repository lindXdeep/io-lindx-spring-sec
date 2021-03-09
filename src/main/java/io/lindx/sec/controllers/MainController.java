package io.lindx.sec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

  /**
   * Main controller.
   * @return main page
   */
  @GetMapping
  public String main(Model model) {
    class User {
      private String name;
      public User(String name){
        this.name = name;
      } 
      public String getName() {
        return name;
      }
    }

    User user = new User("user");

    model.addAttribute("user", user);

    return "main";
  }
}
