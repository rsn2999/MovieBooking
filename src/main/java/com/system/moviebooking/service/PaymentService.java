package com.system.moviebooking.service;

import com.system.moviebooking.model.Booking;
import com.system.moviebooking.model.Payment;
import com.system.moviebooking.repository.BookingRepository;
import com.system.moviebooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.system.moviebooking.constants.BMSConstants.CONFIRMED;
import static com.system.moviebooking.constants.BMSConstants.SUCCESS;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Payment addPayment(String bookId) {

        Booking booking = bookingRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Booking Not Found"));

        if(CONFIRMED.equals(booking.getBookingStatus())) {
            throw new RuntimeException("Booking Already Paid");
        }

        Payment payment = new Payment();
        payment.setBookingId(bookId);
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentStatus(SUCCESS);
        payment.setPaymentTime(LocalDateTime.now());
        Payment savedPayment = paymentRepository.save(payment);

        booking.setBookingStatus(CONFIRMED);
        bookingRepository.save(booking);

        return savedPayment;
    }
}
