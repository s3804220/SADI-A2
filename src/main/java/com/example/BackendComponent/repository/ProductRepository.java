package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE UPPER(CONCAT(p.productName, '',p.model, '',p.description, '',p.company, '',p.brand, '',p.price, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    List<Product> search(String keyword);
}
