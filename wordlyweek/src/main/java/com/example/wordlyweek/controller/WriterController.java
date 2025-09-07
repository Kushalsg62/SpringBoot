package com.example.wordlyweek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.wordlyweek.service.WriterJpaService;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;

@RestController
public class WriterController{

    @Autowired
    private WriterJpaService writerJpaService;

    @GetMapping("/magazines/writers")
    public ArrayList<Writer> getAllWriters(){
        return writerJpaService.getAllWriters();
    }

    @GetMapping("/magazines/writers/{writerId}")
    public Writer getWriterById(@PathVariable ("writerId") int writerId){
        return writerJpaService.getWriterById(writerId);
    }

    @PostMapping("/magazines/writers")
    public Writer addWriter(@RequestBody Writer writer){
        return writerJpaService.addWriter(writer);
    }

    @PutMapping("/magazines/writers/{writerId}")
    public Writer updateWriter(@PathVariable ("writerId") int writerId, @RequestBody Writer writer){
        return writerJpaService.updateWriter(writerId, writer);
    }

    @DeleteMapping("/magazines/writers/{writerId}")
    public void deleteWriter(@PathVariable ("writerId") int writerId){
        writerJpaService.deleteWriter(writerId);
    }

    @GetMapping("/writers/{writerId}/magazines")
    public List<Magazine> getWriterMagazines(@PathVariable ("writerId") int writerId){
        return writerJpaService.getWriterMagazines(writerId);
    }
}