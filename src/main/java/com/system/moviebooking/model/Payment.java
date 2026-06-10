package com.system.moviebooking.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "payments")
public class Payment {

    @Id
    private String paymentId;

    private String bookingId;

    private int amount;

    private String paymentStatus;

    private LocalDateTime paymentTime;
}