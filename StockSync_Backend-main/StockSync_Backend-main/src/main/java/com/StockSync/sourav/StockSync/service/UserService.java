package com.StockSync.sourav.StockSync.service;

import com.StockSync.sourav.StockSync.dto.*;
import com.StockSync.sourav.StockSync.entity.User;
import jakarta.validation.Valid;

public interface UserService {

    Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUser();

    User getCurrentLoggedInUser();

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);

    Response resetPassword(PasswordUpdateDTO passwordUpdateDTO);


    Response registerUserManager(RegisterRequest registerRequest);
}
