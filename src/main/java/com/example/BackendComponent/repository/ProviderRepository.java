package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    @Query("SELECT p FROM Provider p WHERE UPPER(CONCAT(p.providerName, '', p.providerAddress, '', p.providerContactPerson, '', p.providerEmail, '', p.providerFax, '', p.providerPhone, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    Page<Provider> search(String keyword, Pageable pageable);

    @Query("SELECT p FROM Provider p WHERE (?1 IS NULL OR LOWER(p.providerName) LIKE LOWER(CONCAT('%',CAST(?1 AS text),'%'))) AND"
            +"(?2 is null or LOWER(p.providerAddress) like LOWER(CONCAT('%',CAST(?2 AS text),'%'))) AND"
            +"(?3 is null or LOWER(p.providerPhone) like LOWER(CONCAT('%',CAST(?3 AS text),'%'))) AND"
            +"(?4 is null or LOWER(p.providerFax) like LOWER(CONCAT('%',CAST(?4 AS text),'%'))) AND"
            +"(?5 is null or LOWER(p.providerEmail) like LOWER(CONCAT('%',CAST(?5 AS text),'%'))) AND"
            + "(?6 is null or LOWER(p.providerContactPerson) like LOWER(CONCAT('%',CAST(?6 AS text),'%')))")
    Page<Provider> searchProviderBy(@Param("name") String name, @Param("address") String address, @Param("phone") String phone, @Param("fax") String fax, @Param("email") String email, @Param("contact") String contact, Pageable pageable);
}
