//package com.StockSync.sourav.StockSync.repository;
//
//import com.StockSync.sourav.StockSync.entity.Transaction;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//
//    @Query("SELECT t FROM Transaction t WHERE YEAR(t.createdAt) = :year AND MONTH(t.createdAt) = :month")
//    List<Transaction> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);
//
//
//    //we are searching these field; Transaction's description, note, status, Product's name, sku
//    @Query("SELECT t FROM Transaction t " +
//            "LEFT JOIN t.product p " +
//            "WHERE (:searchText IS NULL OR " +
//            "LOWER(CAST(t.description AS text)) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(CAST(t.status AS text)) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(CAST(p.name AS text)) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(CAST(p.sku AS text)) LIKE LOWER(CONCAT('%', :searchText, '%'))) " +
//            "ORDER BY t.id DESC")
//    Page<Transaction> searchTransactions(@Param("searchText") String searchText, Pageable pageable);
//}
//
package com.StockSync.sourav.StockSync.repository;

import com.StockSync.sourav.StockSync.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.createdAt) = :year AND MONTH(t.createdAt) = :month")
    List<Transaction> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);


    @Query(value = "SELECT t.* FROM transactions t " +
            "LEFT JOIN products p ON t.product_id = p.id " +
            "WHERE (:searchText IS NULL OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(t.status) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(p.sku) LIKE LOWER(CONCAT('%', :searchText, '%'))) " +
            "ORDER BY t.id DESC",
            countQuery = "SELECT COUNT(t.id) FROM transactions t " +
                    "LEFT JOIN products p ON t.product_id = p.id " +
                    "WHERE (:searchText IS NULL OR " +
                    "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(t.status) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
                    "LOWER(p.sku) LIKE LOWER(CONCAT('%', :searchText, '%')))",
            nativeQuery = true)
    Page<Transaction> searchTransactionsNative(@Param("searchText") String searchText, Pageable pageable);

}