package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetail, Long> {
}
