package com.geeksforless.mathhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class GeeksForLessApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeeksForLessApplication.class, args);
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}