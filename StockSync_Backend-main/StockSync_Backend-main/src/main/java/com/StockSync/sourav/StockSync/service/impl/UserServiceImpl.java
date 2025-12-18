package com.StockSync.sourav.StockSync.service.impl;

import com.StockSync.sourav.StockSync.dto.*;
import com.StockSync.sourav.StockSync.entity.User;
import com.StockSync.sourav.StockSync.enums.UserRole;
import com.StockSync.sourav.StockSync.exception.InvalidCredentialsException;
import com.StockSync.sourav.StockSync.exception.NotFoundException;
import com.StockSync.sourav.StockSync.repository.UserRepository;
import com.StockSync.sourav.StockSync.security.JwtUtils;
import com.StockSync.sourav.StockSync.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;



    @Override
    public Response registerUser(RegisterRequest registerRequest) {

        UserRole role = UserRole.ROLE_CUSTOMER;

        if (registerRequest.getRole()!=null){
            role = UserRole.valueOf("ROLE_"+registerRequest.getRole());
        }

        User userToSave = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(role)
                .build();


        userRepository.save(userToSave);

        return Response.builder()
                .status(201)
                .message("User Created successfully")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new NotFoundException("User not Found"));

            if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
                throw new InvalidCredentialsException("Wrong password!");
            }
            String token = jwtUtils.generateToken(user.getEmail());


            return Response.builder()
                    .status(200)
                    .message("User logged in successfully")
                    .role(String.valueOf(user.getRole()))
                    .token(token)
                    .expirationTime("6 month")
                    .build();
    }

    @Override
    public Response getAllUser() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());

        userDTOS.forEach(userDTO -> userDTO.setTransactions(null));
        return Response.builder()
                .status(200)
                .message("success")
                .users(userDTOS)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User Not Found"));

        user.setTransactions(null);

        return user;
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {


        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User Not Found"));

        if (userDTO.getEmail() != null) existingUser.setEmail(userDTO.getEmail());
        if (userDTO.getName() != null) existingUser.setName(userDTO.getName());
        if (userDTO.getPhoneNumber() != null) existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getRole() != null){
            String role = "ROLE_"+String.valueOf(userDTO.getRole());
            existingUser.setRole(UserRole.valueOf(role));
        }


        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPhoneNumber(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(existingUser);

        return Response.builder()
                .status(204)
                .message("User Successfully updated")
                .build();
    }


    @Override
    public Response deleteUser(Long id) {

        userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
        userRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("User Successfully Deleted")
                .build();
    }

    @Override
    public Response getUserTransactions(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User Not Found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        userDTO.getTransactions().forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("success")
                .user(userDTO)
                .build();
    }

    @Override
    public Response resetPassword(PasswordUpdateDTO passwordUpdateDTO) {
        User existingUser = userRepository.findByEmail(passwordUpdateDTO.getEmail())
                .orElseThrow(()-> new NotFoundException("User Not Found"));

        if (!passwordEncoder.matches(passwordUpdateDTO.getOldPassword(), existingUser.getPassword())){
            throw new InvalidCredentialsException("Old Password is incorrect");
        }else {
            if (!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
                throw new InvalidCredentialsException("New Password and Confirm Password do not match");
            }else {
                existingUser.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
                userRepository.save(existingUser);
            }
        }

        return Response.builder()
                .status(204)
                .message("Password Successfully updated")
                .build();
    }

    @Override
    public Response registerUserManager(RegisterRequest registerRequest) {
        UserRole role = UserRole.ROLE_MANAGER;

        User userToSave = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(role)
                .build();


        userRepository.save(userToSave);

        return Response.builder()
                .status(201)
                .message("User Created successfully")
                .build();
    }

}
