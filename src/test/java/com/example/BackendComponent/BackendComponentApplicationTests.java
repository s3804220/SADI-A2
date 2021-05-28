package com.example.BackendComponent;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.controller.*;
import com.example.BackendComponent.exception.*;
import com.example.BackendComponent.service.*;
import com.example.BackendComponent.repository.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BackendComponentApplicationTests {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ReceivingNoteService receivingNoteService;
	@Autowired
	private ReceivingDetailService receivingDetailService;
	@Autowired
	private DeliveryNoteService deliveryNoteService;


	@Test
	@org.junit.jupiter.api.Order(1)
	void CategoryTests() {
		Category category1 = categoryService.getCategoryById(1L);
		Category category2 = new Category(2L, "Car");
		assertEquals(category2, categoryService.addCategory(category2));
		assertEquals(category2.getCategoryName(), categoryService.getCategoryById(2L).getCategoryName());
		assertThrows(CategoryAlreadyExistException.class, () -> categoryService.addCategory(category1));
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.deleteCategory(2L);
			categoryService.getCategoryById(2L);
		});
		categoryService.addCategory(category2);
		category2.setCategoryName("Computer");
		categoryService.updateCategory(category2);
		assertEquals(category2.getCategoryName(), categoryService.getCategoryById(2L).getCategoryName());
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	void ProductTests() {
		Category category2 = new Category(2L, "Computer");
		Product product1 = productService.getProductById(1L);
		Product product2 = new Product(2L, "MSI Gaming Laptop", "Pulse GL 66", "MSI", "Micro-Star International", "Gaming Laptop", new BigDecimal(1299), category2);
		assertEquals(product2, productService.addProduct(product2));
		assertEquals(product2.getProductName(), productService.getProductById(2L).getProductName());
		assertThrows(ProductAlreadyExistException.class, () -> productService.addProduct(product1));
		assertThrows(ProductNotFoundException.class, () -> {
			productService.deleteProduct(2L);
			productService.getProductById(2L);
		});
		productService.addProduct(product2);
		product2.setProductName("Laptop");
		productService.updateProduct(product2);
		assertEquals(product2.getProductName(), productService.getProductById(2L).getProductName());
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	void CustomerTests() {
		Customer customer1 = customerService.getCustomerByID(1L);
		Customer customer2 = new Customer(2L, "Dennis", "456 Cdb str", "1309256348", "13156781319", "cus2@email.com", "That Girl");
		assertEquals(customer2, customerService.addCustomer(customer2));
		assertEquals(customer2.getCustomerName(), customerService.getCustomerByID(2L).getCustomerName());
		assertThrows(CustomerAlreadyExistException.class, () -> customerService.addCustomer(customer1));
		assertThrows(CustomerNotFoundException.class, () -> {
			customerService.deleteCustomer(2L);
			customerService.getCustomerByID(2L);
		});
		customerService.addCustomer(customer2);
		customer2.setCustomerName("Remilia");
		customerService.updateCustomer(customer2);
		assertEquals(customer2.getCustomerName(), customerService.getCustomerByID(2L).getCustomerName());
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	void ProviderTests() {
		Provider provider1 = providerService.getProviderByID(1L);
		Provider provider2 = new Provider(2L, "BBB Retail", "659 Wall Street", "0126354156", "958616918176", "prov2@game.co", "Rachel Moore");
		assertEquals(provider2, providerService.addProvider(provider2));
		assertEquals(provider2.getProviderName(), providerService.getProviderByID(2L).getProviderName());
		assertThrows(ProviderAlreadyExistException.class, () -> providerService.addProvider(provider1));
		assertThrows(ProviderNotFoundException.class, () -> {
			providerService.deleteProvider(2L);
			providerService.getProviderByID(2L);
		});
		providerService.addProvider(provider2);
		provider2.setProviderName("Flandre");
		providerService.updateProvider(provider2);
		assertEquals(provider2.getProviderName(), providerService.getProviderByID(2L).getProviderName());
	}

	@Test
	@org.junit.jupiter.api.Order(5)
	void StaffTests() {
		Staff staff1 = staffService.getStaffByID(1L);
		Staff staff2 = new Staff(2L, "Elly Tran", "616 Orchid Boulevard", "0452316159", "anotherstaff@mycompany.com");
		assertEquals(staff2, staffService.addStaff(staff2));
		assertEquals(staff2.getStaffName(), staffService.getStaffByID(2L).getStaffName());
		assertThrows(StaffAlreadyExistException.class, () -> staffService.addStaff(staff1));
		assertThrows(StaffNotFoundException.class, () -> {
			staffService.deleteStaff(2L);
			staffService.getStaffByID(2L);
		});
		staffService.addStaff(staff2);
		staff2.setStaffName("Alice");
		staffService.updateStaff(staff2);
		assertEquals(staff2.getStaffName(), staffService.getStaffByID(2L).getStaffName());
	}

	@Test
	@org.junit.jupiter.api.Order(6)
	void OrderTests() {
		Order order1 = orderService.getOrderByID(1L);
		Order order2 = new Order(2L, LocalDate.of(2021, 03, 17), staffService.getStaffByID(2L), providerService.getProviderByID(2L));
		assertEquals(order2, orderService.addOrder(order2));
		assertEquals(order2.getOrderDate(), orderService.getOrderByID(2L).getOrderDate());
		assertThrows(OrderAlreadyExistException.class, () -> orderService.addOrder(order1));
		assertThrows(OrderNotFoundException.class, () -> {
			orderService.deleteOrder(2L);
			orderService.getOrderByID(2L);
		});
		orderService.addOrder(order2);
		order2.setOrderDate(LocalDate.of(2021, 11, 11));
		orderService.updateOrder(order2);
		assertEquals(order2.getOrderDate(), orderService.getOrderByID(2L).getOrderDate());
	}

	@Test
	@org.junit.jupiter.api.Order(7)
	void OrderDetailTests() {
		OrderDetail orderDetail1 = orderDetailService.getOrderDetailByID(1L);
		OrderDetail orderDetail2 = new OrderDetail(2L, productService.getProductById(2L), 50, new BigDecimal(27980), orderService.getOrderByID(2L));
		assertEquals(orderDetail2, orderDetailService.addOrderDetail(orderDetail2));
		assertEquals(orderDetail2.getOrder().getOrderID(), orderDetailService.getOrderDetailByID(2L).getOrder().getOrderID());
		assertThrows(OrderDetailAlreadyExistException.class, () -> orderDetailService.addOrderDetail(orderDetail1));
		assertThrows(OrderDetailNotFoundException.class, () -> {
			orderDetailService.deleteOrderDetail(2L);
			orderDetailService.getOrderDetailByID(2L);
		});
		orderDetailService.addOrderDetail(orderDetail2);
		orderDetail2.setOrderProduct(productService.getProductById(1L));
		orderDetailService.updateOrderDetail(orderDetail2);
		assertEquals(orderDetail2.getOrderProduct().getProductID(), orderDetailService.getOrderDetailByID(2L).getOrderProduct().getProductID());
	}

	@Test
	@org.junit.jupiter.api.Order(8)
	void ReceivingNoteTests() {
		ReceivingNote receivingNote1 = receivingNoteService.getReceivingNoteByID(1L);
		ReceivingNote receivingNote2 = new ReceivingNote(2L, LocalDate.of(2021,05,18), staffService.getStaffByID(2L), orderService.getOrderByID(2L));
		assertEquals(receivingNote2, receivingNoteService.addReceivingNote(receivingNote2));
		assertEquals(receivingNote2.getReceiveOrder().getOrderID(), receivingNoteService.getReceivingNoteByID(2L).getReceiveOrder().getOrderID());
		assertThrows(ReceivingNoteAlreadyExistException.class, () -> receivingNoteService.addReceivingNote(receivingNote1));
		assertThrows(ReceivingNoteNotFoundException.class, () -> {
			receivingNoteService.deleteReceivingNote(2L);
			receivingNoteService.getReceivingNoteByID(2L);
		});
		receivingNoteService.addReceivingNote(receivingNote2);
		receivingNote2.setReceiveStaff(staffService.getStaffByID(1L));
		receivingNoteService.updateReceivingNote(receivingNote2);
		assertEquals(receivingNote2.getReceiveStaff().getStaffID(), receivingNoteService.getReceivingNoteByID(2L).getReceiveStaff().getStaffID());
	}

}
