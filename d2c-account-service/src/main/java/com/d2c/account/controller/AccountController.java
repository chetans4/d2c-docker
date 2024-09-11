package com.d2c.account.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {

    @GetMapping("/")
    public String root() {
        return "Account root accessed!";
    }

    @GetMapping("/list")
    public String list() {
        return "Account list returned!";
    }

    @GetMapping("/{accountId}")
    public String getOne(@PathVariable Integer accountId) {
        return String.format("Account get one accessed! accountId : %d", accountId);
    }

    @PostMapping("/")
    public String create(@RequestBody Map accountBody) {
        return String.format("Account create accessed! accountBody : %s", accountBody);
    }

    @PutMapping("/")
    public String update(@RequestBody Map accountBody) {
        return String.format("Account update accessed! accountBody : %s", accountBody);
    }

    @DeleteMapping("/{accountId}")
    public String deleteOne(@PathVariable Integer accountId) {
        return String.format("Account delete one accessed! accountId : %d", accountId);
    }
}
