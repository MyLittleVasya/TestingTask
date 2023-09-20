package com.example.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Entry point of application.
 */
@SpringBootApplication
@EnableScheduling
public class TestTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestTaskApplication.class, args);
  }

}
