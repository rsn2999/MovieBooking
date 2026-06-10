package com.system.moviebooking.service;

import com.system.moviebooking.model.Booking;
import com.system.moviebooking.model.Movie;
import com.system.moviebooking.repository.BookingRepository;
import com.system.moviebooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.system.moviebooking.constants.BMSConstants.CANCELLED;
import static com.system.moviebooking.constants.BMSConstants.PENDING;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Booking createBooking(String userId,String movieId, int seats){
        Movie movie =  movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("Movie Not Found"));
        Booking booking =  new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setUserId(userId);
        booking.setMovieId(movieId);
        booking.setSeats(seats);

        int totalAmount = seats * movie.getPrice();

        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus(PENDING);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(String bookId){
        Booking booking = bookingRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));

        int refundAmount = booking.getTotalAmount() / 2;

        booking.setTotalAmount(refundAmount);
        booking.setBookingStatus(CANCELLED);
        return bookingRepository.save(booking);
    }
}
