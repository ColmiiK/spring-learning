package com.alvegag.transcendence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  @GetMapping("/")
  public String Hello() {
    return "Hello World!";
  }
}
