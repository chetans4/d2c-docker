package com.d2c.notification.controller;

import com.d2c.notification.dto.resp.ResponseBodyWrapper;
import com.d2c.notification.dto.resp.ServiceRespDTO;
import com.d2c.notification.service.external.AccountServiceCaller;
import com.d2c.notification.service.external.PaymentServiceCaller;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NotificationController {

    @Autowired
    private AccountServiceCaller accountServiceCaller;

    @Autowired
    private PaymentServiceCaller paymentServiceCaller;

    @Value("${d2c.config.account-service.host}")
    private String accountServiceHost;

    @Value("${d2c.config.payment-service.host}")
    private String paymentServiceHost;

    @GetMapping("/")
    public String root() {
        return "Notification root accessed!";
    }

    @GetMapping("/list")
    public String list(@Value("${d2c.config.account-service.path.list}") String accountServiceList) {

        accountServiceCaller.getAPITrigger(accountServiceHost, accountServiceList, null);

        return "Notification list returned!";
    }

    @GetMapping("/{notificationId}")
    public String getOne(@PathVariable Integer notificationId) {
        return String.format("Notification get one accessed! notificationId : %d", notificationId);
    }

    @PostMapping("/")
    public String create(@RequestBody Map notificationBody) {
        return String.format("Notification create accessed! notificationBody : %s", notificationBody);
    }

    @PutMapping("/")
    public String update(@RequestBody Map notificationBody) {
        return String.format("Notification update accessed! notificationBody : %s", notificationBody);
    }

    @DeleteMapping("/{notificationId}")
    public String deleteOne(@PathVariable Integer notificationId) {
        return String.format("Notification delete one accessed! notificationId : %d", notificationId);
    }

    @SneakyThrows
    @GetMapping("/send")
    public ResponseBodyWrapper<ServiceRespDTO> sendInstant() {
//        Thread.sleep(40000);
        return new ResponseBodyWrapper<>(200, "Sent Successfully!",
                new ServiceRespDTO(11121, String.format("Notification triggered for: %s", 11121),
                null, null));
    }
}
