package com.system.moviebooking.controller;

import com.system.moviebooking.model.Payment;
import com.system.moviebooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{bookingId}")
    public ResponseEntity<Payment> makePayment(@PathVariable String bookingId) {
        return ResponseEntity.ok(paymentService.addPayment(bookingId));
    }
}

