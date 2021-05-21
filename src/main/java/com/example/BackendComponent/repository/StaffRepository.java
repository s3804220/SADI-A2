package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
    @Query("SELECT s FROM Staff s WHERE UPPER(CONCAT(s.staffName, '', s.staffAddress, '', s.staffEmail, '', s.staffPhone, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    List<Staff> search(String keyword);
}
