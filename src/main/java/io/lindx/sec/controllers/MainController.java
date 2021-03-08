package io.lindx.sec.controllers;

import org.springframework.stereotype.Controller;
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
  public String main() {

    System.out.println("we are in main !!!");

    return "main";
  }
}
