package com.system.moviebooking;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviebookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviebookingApplication.class, args);
    }
}


