package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT s FROM Staff s WHERE (?1 IS NULL OR LOWER(s.staffName) LIKE LOWER(CONCAT('%',CAST(?1 AS text),'%'))) AND"
            +"(?2 is null or LOWER(s.staffAddress) like LOWER(CONCAT('%',CAST(?2 AS text),'%'))) AND"
            +"(?3 is null or LOWER(s.staffPhone) like LOWER(CONCAT('%',CAST(?3 AS text),'%'))) AND"
            + "(?4 is null or LOWER(s.staffEmail) like LOWER(CONCAT('%',CAST(?4 AS text),'%')))")
    List<Staff> searchStaffBy(@Param("name") String name, @Param("address") String address, @Param("phone") String phone, @Param("email") String email);
}
