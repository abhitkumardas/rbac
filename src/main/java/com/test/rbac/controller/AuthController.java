package com.test.rbac.controller;


import com.test.rbac.model.dto.SignUpDto;
import com.test.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.signin(email, password));
    }

}
