package com.d2c.product.controller;

import com.d2c.product.service.external.AccountServiceCaller;
import com.d2c.product.service.external.PaymentServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

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
        return "Product root accessed!";
    }

    @GetMapping("/list")
    public String list(@Value("${d2c.config.account-service.path.list}") String accountServiceList) {

        accountServiceCaller.getAPITrigger(accountServiceHost, accountServiceList, null);

        return "Product list returned!";
    }

    @GetMapping("/{productId}")
    public String getOne(@PathVariable Integer productId) {
        return String.format("Product get one accessed! productId : %d", productId);
    }

    @PostMapping("/")
    public String create(@RequestBody Map productBody) {
        return String.format("Product create accessed! productBody : %s", productBody);
    }

    @PutMapping("/")
    public String update(@RequestBody Map productBody) {
        return String.format("Product update accessed! productBody : %s", productBody);
    }

    @DeleteMapping("/{productId}")
    public String deleteOne(@PathVariable Integer productId) {
        return String.format("Product delete one accessed! productId : %d", productId);
    }

    @GetMapping("/payment/list/{productId}")
    public String paymentList(@Value("${d2c.config.payment-service.path.list}") String paymentList,
                              @PathVariable Integer productId) {

        Map response = paymentServiceCaller.getAPITrigger(paymentServiceHost, paymentList);

        return String.format("Payment list returned: %s", response);
    }
}
