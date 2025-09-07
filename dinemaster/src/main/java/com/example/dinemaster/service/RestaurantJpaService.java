package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;

@Service
public class RestaurantJpaService implements RestaurantRepository{
    @Autowired 
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public ArrayList<Restaurant> getAllRestaurant(){
        List<Restaurant> listRestaurant = restaurantJpaRepository.findAll();
        ArrayList<Restaurant> allRestaurants = new ArrayList<>(listRestaurant);
        return allRestaurants;
    }

    @Override
    public Restaurant getRestaurantById(int id){
        try{
            Restaurant restaurant = restaurantJpaRepository.findById(id).get();
            return restaurant;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant){
        restaurantJpaRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(int id, Restaurant restaurant){
        try{
            Restaurant newRestaurant = restaurantJpaRepository.findById(id).get();
            if(restaurant.getName() != null){
                newRestaurant.setName(restaurant.getName());
            }
            if(restaurant.getAddress() != null){
                newRestaurant.setAddress(restaurant.getAddress());
            }
            if(restaurant.getCuisineType() != null){
                newRestaurant.setCuisineType(restaurant.getCuisineType());
            }
            if(restaurant.getRating() != 0){
                newRestaurant.setRating(restaurant.getRating());
            }
            restaurantJpaRepository.save(newRestaurant);
            return newRestaurant;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteRestaurant(int id){
        try{
            restaurantJpaRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}