package com.StockSync.sourav.StockSync.controller;

import com.StockSync.sourav.StockSync.dto.LoginRequest;
import com.StockSync.sourav.StockSync.dto.PasswordUpdateDTO;
import com.StockSync.sourav.StockSync.dto.RegisterRequest;
import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }

    @PutMapping("/update/password")
    public ResponseEntity<Response> updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO) {
        return ResponseEntity.ok(userService.resetPassword(passwordUpdateDTO));
    }


}
