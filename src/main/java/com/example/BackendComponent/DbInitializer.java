package com.example.BackendComponent;

import com.example.BackendComponent.entity.Category;
import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.repository.CategoryRepository;
import com.example.BackendComponent.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DbInitializer implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    DbInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        this.categoryRepository.deleteAll();
        this.productRepository.deleteAll();

        Category category1 = new Category(2L, "Phone");
        Product product1 = new Product(1L, "iPhone", "10", "Apple", "Apple", "iPhone X", new BigDecimal(999), category1);

        this.categoryRepository.save(category1);
        this.productRepository.save(product1);
    }
}
