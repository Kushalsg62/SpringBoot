package com.example.dinemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.service.ChefJpaService;
import com.example.dinemaster.model.Restaurant;

@RestController
public class ChefController{
    @Autowired
    private ChefJpaService chefJpaService;

    @GetMapping("/restaurants/chefs")
    public ArrayList<Chef> getAllChef(){
        return chefJpaService.getAllChef();
    }

    @GetMapping("/restaurants/chefs/{chefId}")
    public Chef getChefById(@PathVariable ("chefId") int id){
        return chefJpaService.getChefById(id);
    }

    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef){
        return chefJpaService.addChef(chef);
    }

    @PutMapping("/restaurants/chefs/{chefId}")
    public Chef updateChef(@PathVariable ("chefId") int id, @RequestBody Chef chef){
        return chefJpaService.updateChef(id, chef);
    }

    @DeleteMapping("/restaurants/chefs/{chefId}")
    public void deleteChef(@PathVariable ("chefId") int id){
        chefJpaService.deleteChef(id);
    }

    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getRestaurantChef(@PathVariable ("chefId") int id){
        return chefJpaService.getRestaurantChef(id);
    }
}