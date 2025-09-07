package com.example.wordlyweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.*;

@Entity
@Table(name = "writer")
public class Writer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int writerId;

    @Column(name = "name")
    private String writerName;

    @Column(name = "bio")
    private String bio;

    @ManyToMany
    @JoinTable(name = "writer_magazine",
                joinColumns = @JoinColumn(name = "writerid"),
                inverseJoinColumns = @JoinColumn(name = "magazineid")
    )
    @JsonIgnoreProperties("writers")
    private List<Magazine> magazines;

    public Writer(){

    }

    public Writer(int writerId, String writerName, String bio, List<Magazine> magazines){
        this.writerId = writerId;
        this.writerName = writerName;
        this.bio = bio;
        this.magazines = magazines;
    }

    public int getWriterId() {
        return writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getBio() {
        return bio;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }
    
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }
}