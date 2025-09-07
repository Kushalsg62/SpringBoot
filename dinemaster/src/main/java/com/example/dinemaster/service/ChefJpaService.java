package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.repository.ChefRepository;
import com.example.dinemaster.repository.ChefJpaRepository;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantJpaRepository;

@Service
public class ChefJpaService implements ChefRepository{
    @Autowired
    private ChefJpaRepository chefJpaRepository;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public ArrayList<Chef> getAllChef(){
        List<Chef> listChef = chefJpaRepository.findAll();
        ArrayList<Chef> allChefs = new ArrayList<>(listChef);
        return allChefs;
    }

    @Override
    public Chef getChefById(int id){
        try{
            Chef chef = chefJpaRepository.findById(id).get();
            return chef;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef addChef(Chef chef){
        Restaurant restaurant = chef.getRestaurant();
        int restaurantId = restaurant.getId();
        try{
            restaurant = restaurantJpaRepository.findById(restaurantId).get();
            chef.setRestaurant(restaurant);
            chefJpaRepository.save(chef);
            return chef;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef updateChef(int id, Chef chef){
        try{
            Chef newChef = chefJpaRepository.findById(id).get();
            if(chef.getFirstName() != null){
                newChef.setFirstName(chef.getFirstName());
            }
            if(chef.getLastName() != null){
                newChef.setLastName(chef.getLastName());
            }
            if(chef.getExpertise() != null){
                newChef.setExpertise(chef.getExpertise());
            }
            if(chef.getExperienceYears() != 0){
                newChef.setExperienceYears(chef.getExperienceYears());
            }
            if(chef.getRestaurant() != null){
                Restaurant restaurant = chef.getRestaurant();
                int restaurantId = restaurant.getId();
                Restaurant newRestaurant = restaurantJpaRepository.findById(restaurantId).get();
                newChef.setRestaurant(newRestaurant);
            }
            chefJpaRepository.save(newChef);
            return newChef;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteChef(int id){
        try{
            chefJpaRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Restaurant getRestaurantChef(int id){
        try{
            Chef chef = chefJpaRepository.findById(id).get();
            return chef.getRestaurant();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}