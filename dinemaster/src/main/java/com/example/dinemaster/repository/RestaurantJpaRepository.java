package com.example.dinemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dinemaster.model.Restaurant;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Integer>{
    
}