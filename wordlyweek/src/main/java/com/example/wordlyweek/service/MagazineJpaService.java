package com.example.wordlyweek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import com.example.wordlyweek.repository.MagazineRepository;
import com.example.wordlyweek.repository.WriterJpaRepository;
import com.example.wordlyweek.repository.MagazineJpaRepository;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;

@Service
public class MagazineJpaService implements MagazineRepository{
    @Autowired
    private MagazineJpaRepository magazineJpaRepository;

    @Autowired
    private WriterJpaRepository writerJpaRepository;

    @Override
    public ArrayList<Magazine> getAllMagazine(){
        List<Magazine> listCollection = magazineJpaRepository.findAll();
        ArrayList<Magazine> allMagazine = new ArrayList<>(listCollection);
        return allMagazine;
    }

    @Override
    public Magazine getMagazineById(int magazineId){
        try{
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            return magazine;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
public Magazine addMagazine(Magazine magazine) {
    if (magazine.getWriters() == null || magazine.getWriters().isEmpty()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    List<Integer> writerIds = new ArrayList<>();
    for (Writer writer : magazine.getWriters()) {
        writerIds.add(writer.getWriterId());
    }

    List<Writer> writers = writerJpaRepository.findAllById(writerIds);
    if (writers.size() != writerIds.size()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    magazine.setWriters(writers);

    for (Writer writer : writers) {
        writer.getMagazines().add(magazine);
    }

    return magazineJpaRepository.save(magazine);
}


    public Magazine updateMagazine(int magazineId, Magazine magazine) {
        try {
            Magazine newMagazine = magazineJpaRepository.findById(magazineId).get();
            if (magazine.getMagazineName() != null) {
                newMagazine.setMagazineName(magazine.getMagazineName());
            }
            if (magazine.getPublicationDate() != null) {
                newMagazine.setPublicationDate(magazine.getPublicationDate());
            }
            if (magazine.getWriters() != null) {
                List<Writer> writers = newMagazine.getWriters();
                for (Writer writer : writers) {
                    writer.getMagazines().remove(newMagazine);
                }
                writerJpaRepository.saveAll(writers);
                List<Integer> newWriterIds = new ArrayList<>();
                for (Writer writer : magazine.getWriters()) {
                    newWriterIds.add(writer.getWriterId());
                }
                List<Writer> newWriters = writerJpaRepository.findAllById(newWriterIds);
                for (Writer writer : newWriters) {
                    writer.getMagazines().add(newMagazine);
                }
                writerJpaRepository.saveAll(newWriters);
                newMagazine.setWriters(newWriters);
            }
            newMagazine = magazineJpaRepository.save(newMagazine);
            return newMagazine;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteMagazine(int magazineId){
        try{
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();

            List<Writer> writers = magazine.getWriters();
            for (Writer writer : writers) {
                writer.getMagazines().remove(magazine);
            }

            writerJpaRepository.saveAll(writers);

            magazineJpaRepository.deleteById(magazineId);

        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Writer> getMagazineWriters(int magazineId){
        try{
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            return magazine.getWriters();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}