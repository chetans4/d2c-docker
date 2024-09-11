package com.d2c.product.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/")
    public String root() {
        return "Product root accessed!";
    }

    @GetMapping("/list")
    public String list() {
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
}
