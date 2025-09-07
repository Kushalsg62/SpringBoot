package com.example.dinemaster.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "cuisinetype")
    private String cuisineType;

    @Column(name = "rating")
    private int rating;

    public Restaurant(){

    }

    public Restaurant(int id, String name, String address, String cuisineType, int rating){
        this.id = id;
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.rating = rating;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getCuisineType(){
        return cuisineType;
    }

    public int getRating(){
        return rating;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setCuisineType(String cuisineType){
        this.cuisineType = cuisineType;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
}