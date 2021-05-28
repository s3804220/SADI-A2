package com.example.BackendComponent;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.controller.*;
import com.example.BackendComponent.exception.*;
import com.example.BackendComponent.service.*;
import com.example.BackendComponent.repository.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BackendComponentApplicationTests {
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private ProductController productController;

	@Test
	void CategoryTests() {
		Category category1 = new Category(1L, "Phone");
		Category category2 = new Category(2L, "Car");
		assertEquals(category2, categoryController.addCategory(category2));
		assertEquals(category2.getCategoryName(), categoryController.getCategoryById(2L).getCategoryName());
		assertThrows(CategoryAlreadyExistException.class, () -> categoryController.addCategory(category1));
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryController.deleteCategory(2L);
			categoryController.getCategoryById(2L);
		});
		categoryController.addCategory(category2);
		category2.setCategoryName("Computer");
		categoryController.updateCategory(category2);
		assertEquals(category2.getCategoryName(), categoryController.getCategoryById(2L).getCategoryName());
	}

	@Test
	void ProductTests() {
		Category category1 = new Category(1L, "Phone");
		Category category2 = new Category(2L, "Computer");
		Product product1 = new Product(1L, "iPhone", "10", "Apple", "Apple", "iPhone X", new BigDecimal(999), category1);
		Product product2 = new Product(2L, "MSI Gaming Laptop", "Pulse GL 66", "MSI", "Micro-Star International", "Gaming Laptop", new BigDecimal(1299), category2);
		assertEquals(product2, productController.addProduct(product2));
		assertEquals(product2.getProductName(), productController.getProductById(2L).getProductName());
		assertThrows(ProductAlreadyExistException.class, () -> productController.addProduct(product1));
	}
}
