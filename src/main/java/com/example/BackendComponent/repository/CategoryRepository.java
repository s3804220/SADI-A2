package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE UPPER(CONCAT(c.categoryName, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    Page<Category> search(String keyword, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE (?1 IS NULL OR LOWER(c.categoryName) LIKE LOWER(CONCAT('%',CAST(?1 AS text),'%')))")
    Page<Category> searchCategoryBy(@Param("name") String name, Pageable pageable);
}
