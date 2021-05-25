package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Query("SELECT c FROM Customer c WHERE UPPER(CONCAT(c.customerName, '', c.customerAddress, '', c.customerContactPerson, '', c.customerEmail, '', c.customerFax, '', c.customerPhone, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    Page<Customer> search(String keyword, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE (?1 IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%',CAST(?1 AS text),'%'))) AND"
            +"(?2 is null or LOWER(c.customerAddress) like LOWER(CONCAT('%',CAST(?2 AS text),'%'))) AND"
            +"(?3 is null or LOWER(c.customerPhone) like LOWER(CONCAT('%',CAST(?3 AS text),'%'))) AND"
            +"(?4 is null or LOWER(c.customerFax) like LOWER(CONCAT('%',CAST(?4 AS text),'%'))) AND"
            +"(?5 is null or LOWER(c.customerEmail) like LOWER(CONCAT('%',CAST(?5 AS text),'%'))) AND"
            + "(?6 is null or LOWER(c.customerContactPerson) like LOWER(CONCAT('%',CAST(?6 AS text),'%')))")
    Page<Customer> searchCustomerBy(@Param("name") String name, @Param("address") String address, @Param("phone") String phone, @Param("fax") String fax, @Param("email") String email, @Param("contact") String contact, Pageable pageable);
}
