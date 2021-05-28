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
	@Autowired
	private CustomerController customerController;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReceivingNoteService receivingNoteService;
	@Autowired
	private DeliveryNoteService deliveryNoteService;


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
		assertThrows(ProductNotFoundException.class, () -> {
			productController.deleteProduct(2L);
			productController.getProductById(2L);
		});
		productController.addProduct(product2);
		product2.setProductName("Laptop");
		productController.updateProduct(product2);
		assertEquals(product2.getProductName(), productController.getProductById(2L).getProductName());
	}

	@Test
	void CustomerTests() {
		Customer customer1 = new Customer(1L, "Hannah", "123 Abc str", "0298145237", "13235551234", "cus1@email.com", "That Guy");
		Customer customer2 = new Customer(2L, "Dennis", "456 Cdb str", "1309256348", "13156781319", "cus2@email.com", "That Girl");
		assertEquals(customer2, customerController.addCustomer(customer2));
		assertEquals(customer2.getCustomerName(), customerController.getCustomerByID(2L).getCustomerName());
		assertThrows(CustomerAlreadyExistException.class, () -> customerController.addCustomer(customer1));
		assertThrows(CustomerNotFoundException.class, () -> {
			customerController.deleteCustomer(2L);
			customerController.getCustomerByID(2L);
		});
		customerController.addCustomer(customer2);
		customer2.setCustomerName("Laptop");
		customerController.updateCustomer(customer2);
		assertEquals(customer2.getCustomerName(), customerController.getCustomerByID(2L).getCustomerName());
	}

	
}
