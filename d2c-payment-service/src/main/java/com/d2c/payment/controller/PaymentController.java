package com.d2c.payment.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PaymentController {

    @GetMapping("/")
    public String root() {
        return "Payment root accessed!";
    }

    @GetMapping("/list")
    public String list() {
        return "Payment list returned!";
    }

    @GetMapping("/{paymentId}")
    public String getOne(@PathVariable Integer paymentId) {
        return String.format("Payment get one accessed! paymentId : %d", paymentId);
    }

    @PostMapping("/")
    public String create(@RequestBody Map paymentBody) {
        return String.format("Payment create accessed! paymentBody : %s", paymentBody);
    }

}
