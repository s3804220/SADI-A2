package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.DeliveryNote;
import com.example.BackendComponent.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
    @Query("SELECT d FROM DeliveryNote d WHERE (coalesce(?1) IS NULL OR d.deliveryDate >= cast(?1 as date)) AND" +
            "(coalesce(?2) IS NULL OR d.deliveryDate <= CAST(?2 AS date))")
    Page<DeliveryNote> searchDeliveryNoteBy(@Param("fromdate") @Temporal Date fromdate, @Param("todate") @Temporal Date todate, Pageable pageable);
}
