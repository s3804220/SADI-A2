package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Query("SELECT c FROM Customer c WHERE UPPER(CONCAT(c.customerName, '', c.customerAddress, '', c.customerContactPerson, '', c.customerEmail, '', c.customerFax, '', c.customerPhone, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    List<Customer> search(String keyword);
}
