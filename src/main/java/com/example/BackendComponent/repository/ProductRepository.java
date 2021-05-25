package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Product;
import org.hibernate.type.BigDecimalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE UPPER(CONCAT(p.productName, '',p.model, '',p.description, '',p.company, '',p.brand, '',p.price, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    Page<Product> search(String keyword, Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE (?1 IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%',CAST(?1 AS text),'%'))) AND"
            +"(?2 is null or LOWER(p.model) like LOWER(CONCAT('%',CAST(?2 AS text),'%'))) AND"
            +"(?3 is null or LOWER(p.brand) like LOWER(CONCAT('%',CAST(?3 AS text),'%'))) AND"
            +"(?4 is null or LOWER(p.company) like LOWER(CONCAT('%',CAST(?4 AS text),'%'))) AND"
            +"(?5 is null or LOWER(p.description) like LOWER(CONCAT('%',CAST(?5 AS text),'%'))) AND"
            +"(?6 IS NULL OR p.price >= CAST(CAST(?6 AS text) AS big_decimal)) AND"
            + "(?7 IS NULL OR p.price <= CAST(CAST(?7 AS text) AS big_decimal))")
    Page<Product> searchProductBy(@Param("name") String name, @Param("model") String model, @Param("brand") String brand, @Param("company") String company, @Param("description") String description, @Param("minprice") BigDecimal minprice, @Param("maxprice") BigDecimal maxprice, Pageable pageable);

}
