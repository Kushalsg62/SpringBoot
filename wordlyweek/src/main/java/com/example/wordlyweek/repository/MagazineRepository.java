package com.example.wordlyweek.repository;

import java.util.*;

import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;

public interface MagazineRepository{
    ArrayList<Magazine> getAllMagazine();
    Magazine getMagazineById(int magazineId);
    Magazine addMagazine(Magazine magazine);
    Magazine updateMagazine(int magazineId, Magazine magazine);
    void deleteMagazine(int magazineId);
    List<Writer> getMagazineWriters(int magazineId);
}