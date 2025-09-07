package com.example.geohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.geohub.model.City;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Integer>{

}