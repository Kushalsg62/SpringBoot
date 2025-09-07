package com.example.geohub.repository;

import java.util.*;
import com.example.geohub.model.Country;
import com.example.geohub.model.City;

public interface CityRepository{
    ArrayList<City> getAllCities();
    City getCityById(int cityId);
    City addCity(City city);
    City updateCity(int cityId, City city);
    void deleteCity(int cityId);
    Country getCountryCities(int cityId);
}