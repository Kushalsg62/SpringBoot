package com.example.geohub.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.service.CityJpaService;

@RestController
public class CityController{
    @Autowired
    private CityJpaService cityJpaService;

    @GetMapping("/countries/cities")
    public ArrayList<City> getAllCities(){
        return cityJpaService.getAllCities();
    }

    @GetMapping("/countries/cities/{cityId}")
    public City getCityById(@PathVariable ("cityId") int cityId){
        return cityJpaService.getCityById(cityId);
    }

    @PostMapping("/countries/cities")
    public City addCity(@RequestBody City city){
        return cityJpaService.addCity(city);
    }

    @PutMapping("/countries/cities/{cityId}")
    public City updateCity(@PathVariable ("cityId") int cityId, @RequestBody City city){
        return cityJpaService.updateCity(cityId, city);
    }

    @DeleteMapping("/countries/cities/{cityId}")
    public void deleteCity(@PathVariable ("cityId") int cityId){
        cityJpaService.deleteCity(cityId);
    }

    @GetMapping("/cities/{cityId}/country")
    public Country getCountryCities(@PathVariable ("cityId") int cityId){
        return cityJpaService.getCountryCities(cityId);
    }
}
