package com.example.wordlyweek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wordlyweek.model.Writer;

@Repository
public interface WriterJpaRepository extends JpaRepository<Writer, Integer>{

}
