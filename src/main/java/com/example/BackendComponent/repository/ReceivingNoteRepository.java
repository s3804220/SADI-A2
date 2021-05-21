package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingNoteRepository extends JpaRepository<ReceivingNote, Long> {
}
