package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /*@Query("SELECT o FROM Order o WHERE ((coalesce(?1) IS NOT NULL AND coalesce(?2) IS NULL) AND o.orderDate >= cast(?1 as date)) OR" +
            "((coalesce(?2) IS NOT NULL AND coalesce(?1) IS NULL) AND o.orderDate <= CAST(?2 AS date)) OR" +
            "((coalesce(?1) IS NOT NULL AND coalesce(?2) IS NOT NULL) AND o.orderDate >= cast(?1 as date) AND o.orderDate <= CAST(?2 AS date))")*/
    @Query("SELECT o FROM Order o WHERE (coalesce(?1) IS NULL OR o.orderDate >= cast(?1 as date)) AND" +
            "(coalesce(?2) IS NULL OR o.orderDate <= CAST(?2 AS date))")
    List<Order> searchOrderBy(@Param("fromdate") @Temporal Date fromdate, @Param("todate") @Temporal Date todate);
}
