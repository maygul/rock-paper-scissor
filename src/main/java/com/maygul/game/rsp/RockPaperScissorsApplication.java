package com.maygul.game.rsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class RockPaperScissorsApplication {

  public static void main(String[] args) {
    SpringApplication.run(RockPaperScissorsApplication.class, args);
  }

}
