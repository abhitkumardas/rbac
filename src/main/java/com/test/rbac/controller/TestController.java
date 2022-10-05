package com.test.rbac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/ping")
    private ResponseEntity test() {
        return ResponseEntity.ok("Ping Success");
    }
}
