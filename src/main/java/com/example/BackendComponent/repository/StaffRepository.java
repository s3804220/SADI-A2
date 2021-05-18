package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends  JpaRepository<Staff, Long>{
}
