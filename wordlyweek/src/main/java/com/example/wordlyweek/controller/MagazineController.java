package com.example.wordlyweek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.wordlyweek.service.MagazineJpaService;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;

@RestController
public class MagazineController{

    @Autowired
    private MagazineJpaService magazineJpaService;

    @GetMapping("/magazines")
    public ArrayList<Magazine> getAllMagazine(){
        return magazineJpaService.getAllMagazine();
    }

    @GetMapping("/magazines/{magazineId}")
    public Magazine getMagazineById(@PathVariable ("magazineId") int magazineId){
        return magazineJpaService.getMagazineById(magazineId);
    }

    @PostMapping("/magazines")
    public Magazine addMagazine(@RequestBody Magazine magazine){
        return magazineJpaService.addMagazine(magazine);
    }

    @PutMapping("/magazines/{magazineId}")
    public Magazine updateMagazine(@PathVariable ("magazineId") int magazineId, @RequestBody Magazine magazine){
        return magazineJpaService.updateMagazine(magazineId, magazine);
    }

    @DeleteMapping("/magazines/{magazineId}")
    public void deleteMagazine(@PathVariable ("magazineId") int magazineId){
        magazineJpaService.deleteMagazine(magazineId);
    }

    @GetMapping("/magazines/{magazineId}/writers")
    public List<Writer> getMagazineWriters(@PathVariable ("magazineId") int magazineId){
        return magazineJpaService.getMagazineWriters(magazineId);
    }
}