package com.d2c.payment.controller;

import com.d2c.payment.service.external.NotificationServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private NotificationServiceCaller notificationServiceCaller;

    @Value("${d2c.config.notification-service.host}")
    private String notificationServiceHost;

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

    @PostMapping("/with-notification")
    public String createAndSendNotification(
            @Value("${d2c.config.notification-service.path.root}") String rootPath,
            @RequestBody Map paymentBody) {

        Map response = notificationServiceCaller.getAPITrigger(notificationServiceHost, rootPath);

        return String.format("Payment create accessed! paymentBody : %s " +
                "\n response from notification service %s", paymentBody, response);
    }


}
