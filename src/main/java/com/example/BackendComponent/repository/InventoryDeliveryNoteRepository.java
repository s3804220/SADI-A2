package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.InventoryDeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDeliveryNoteRepository extends JpaRepository<InventoryDeliveryNote, Long> {
}
