package com.system.moviebooking.controller;

import com.system.moviebooking.model.Booking;
import com.system.moviebooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@RequestParam String userId, @RequestParam String movieId, @RequestParam int seats) {
        Booking booking = bookingService.createBooking(userId, movieId, seats);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
}


