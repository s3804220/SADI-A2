package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
}
