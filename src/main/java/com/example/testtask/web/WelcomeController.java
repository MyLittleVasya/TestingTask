package com.example.testtask.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic welcome controller. Used just for success login redirect.
 */
@RestController
public class WelcomeController {

  @PostMapping("/welcome")
  public String welcome() {
    return "welcome";
  }
}
