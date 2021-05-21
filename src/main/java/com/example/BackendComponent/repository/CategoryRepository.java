package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE UPPER(CONCAT(c.categoryName, '')) LIKE UPPER(CONCAT('%', ?1, '%'))")
    List<Category> search(String keyword);
}
