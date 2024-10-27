package com.yuki.catalogservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/")
  public String getGreeting() {
    return "welcome to yuki catalog service";
  }
}
