package com.example.geohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CountryRepository;

@Service
public class CountryJpaService implements CountryRepository{
    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<Country> getAllCountries(){
        List<Country> listCountries = countryJpaRepository.findAll();
        ArrayList<Country> allCountries = new ArrayList<>(listCountries);
        return allCountries;
    }

    @Override
    public Country getCountryById(int countryId){
        try{
            Country country = countryJpaRepository.findById(countryId).get();
            return country;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country addCountry(Country country){
        countryJpaRepository.save(country);
        return country;
    }

    @Override
    public Country updateCountry(int countryId, Country country){
        try{
            Country newCountry = countryJpaRepository.findById(countryId).get();
            if(country.getCountryName() != null){
                newCountry.setCountryName(country.getCountryName());
            }
            if(country.getCurrency() != null){
                newCountry.setCurrency(country.getCurrency());
            }
            if(country.getPopulation() != 0){
                newCountry.setPopulation(country.getPopulation());
            }
            if(country.getLatitude() != null){
                newCountry.setLatitude(country.getLatitude());
            }
            if(country.getLongitude() != null){
                newCountry.setLongitude(country.getLongitude());
            }
            countryJpaRepository.save(newCountry);
            return newCountry;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCountry(int countryId){
        try{
            countryJpaRepository.deleteById(countryId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}