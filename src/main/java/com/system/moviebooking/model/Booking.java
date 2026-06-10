package com.system.moviebooking.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bookings")
public class Booking {

    @Id
    private String bookingId;
    private String userId;
    private String movieId;
    private int seats;
    private int totalAmount;
    private String bookingStatus;
}


