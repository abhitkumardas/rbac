package com.test.rbac.controller;

import com.test.rbac.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private TestService testService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity fetchTransactions() {
        return ResponseEntity.ok(testService.findUsers());
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity fetchTransactionsForAdmin() {
        return ResponseEntity.ok(testService.findUsers());
    }

}
