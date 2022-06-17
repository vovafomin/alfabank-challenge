package com.vovafomin.alfabankchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AlfabankChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlfabankChallengeApplication.class, args);
  }
}
