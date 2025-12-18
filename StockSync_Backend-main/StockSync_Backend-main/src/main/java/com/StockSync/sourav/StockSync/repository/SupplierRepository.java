package com.StockSync.sourav.StockSync.repository;

import com.StockSync.sourav.StockSync.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
