package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.ReceivingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingDetailRepository extends JpaRepository<ReceivingDetail, Long> {
}
