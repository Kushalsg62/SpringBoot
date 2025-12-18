package com.StockSync.sourav.StockSync.repository;

import com.StockSync.sourav.StockSync.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
