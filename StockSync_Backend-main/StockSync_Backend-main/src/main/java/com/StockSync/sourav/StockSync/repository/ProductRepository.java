package com.StockSync.sourav.StockSync.repository;

import com.StockSync.sourav.StockSync.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
