package com.StockSync.sourav.StockSync.security;

import com.StockSync.sourav.StockSync.entity.User;
import com.StockSync.sourav.StockSync.exception.NotFoundException;
import com.StockSync.sourav.StockSync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepo.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User Email not found"));
        return AuthUser.builder()
                .user(user)
                .build();
    }


}
