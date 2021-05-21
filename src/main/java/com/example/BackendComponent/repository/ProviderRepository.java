package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    @Query("SELECT p FROM Provider p WHERE UPPER(CONCAT(p.providerName, '', p.providerAddress, '', p.providerContactPerson, '', p.providerEmail, '', p.providerFax, '', p.providerPhone, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    List<Provider> search(String keyword);
}
