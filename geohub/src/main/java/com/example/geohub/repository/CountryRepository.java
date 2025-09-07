package com.example.geohub.repository;

import java.util.*;
import com.example.geohub.model.Country;

public interface CountryRepository{
    ArrayList<Country> getAllCountries();
    Country getCountryById(int countryId);
    Country addCountry(Country country);
    Country updateCountry(int countryId, Country country);
    void deleteCountry(int countryId);
}