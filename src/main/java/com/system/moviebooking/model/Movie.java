package com.system.moviebooking.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String movieId;

    private String title;

    private String theatreName;

    private LocalDateTime showTime;

    private int price;

    private String location;

    private int availableSeats; 
}
