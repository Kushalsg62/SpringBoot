package com.StockSync.sourav.StockSync.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDTO {

    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Old Password is required")
    private String oldPassword;
    @NotBlank(message = "New Password is required")
    private String newPassword;
    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
}
