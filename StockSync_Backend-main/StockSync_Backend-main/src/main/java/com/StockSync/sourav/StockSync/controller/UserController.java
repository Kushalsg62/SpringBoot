package com.StockSync.sourav.StockSync.controller;

import com.StockSync.sourav.StockSync.dto.RegisterRequest;
import com.StockSync.sourav.StockSync.dto.Response;
import com.StockSync.sourav.StockSync.dto.UserDTO;
import com.StockSync.sourav.StockSync.entity.User;
import com.StockSync.sourav.StockSync.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }


    @GetMapping("/transaction/{userid}")
    public ResponseEntity<Response> getUserAndTransactions(@PathVariable Long userid) {
        return ResponseEntity.ok(userService.getUserTransactions(userid));
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentLoggedInUser());
    }

    @PostMapping("/register/manager")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> registerUserAsManager(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUserManager(registerRequest));
    }
}
