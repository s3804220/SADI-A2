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
import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	private DeliveryDetailService deliveryDetailService;
	@Autowired
	private SaleInvoiceService saleInvoiceService;
	@Autowired
	private SaleDetailService saleDetailService;

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

	@Test
	@org.junit.jupiter.api.Order(9)
	void ReceivingDetailTests() {
		ReceivingDetail receivingDetail1 = receivingDetailService.getReceivingDetailByID(1L);
		ReceivingDetail receivingDetail2 = receivingDetailService.getReceivingDetailByID(2L);
		assertThrows(ReceivingDetailNotFoundException.class, () -> {
			receivingDetailService.deleteReceivingDetail(2L);
			receivingDetailService.getReceivingDetailByID(2L);
		});
		assertEquals(receivingDetail2, receivingDetailService.addReceivingDetail(receivingDetail2));
		assertEquals(receivingDetail2.getReceivingNote().getReceivingNoteID(), receivingDetailService.getReceivingDetailByID(2L).getReceivingNote().getReceivingNoteID());
		assertThrows(ReceivingDetailAlreadyExistException.class, () -> receivingDetailService.addReceivingDetail(receivingDetail1));
		receivingDetail2.setReceiveQuantity(200);
		receivingDetailService.updateReceivingDetail(receivingDetail2);
		assertEquals(receivingDetail2.getReceiveQuantity(), receivingDetailService.getReceivingDetailByID(2L).getReceiveQuantity());
	}

	@Test
	@org.junit.jupiter.api.Order(10)
	void DeliveryNoteTests() {
		DeliveryNote deliveryNote1 = deliveryNoteService.getDeliveryNoteByID(1L);
		DeliveryNote deliveryNote2 = new DeliveryNote(2L, LocalDate.of(2021, 5, 17), staffService.getStaffByID(2L));
		assertEquals(deliveryNote2, deliveryNoteService.addDeliveryNote(deliveryNote2));
		assertEquals(deliveryNote2.getDeliveryDate(), deliveryNoteService.getDeliveryNoteByID(2L).getDeliveryDate());
		assertThrows(DeliveryNoteAlreadyExistException.class, () -> deliveryNoteService.addDeliveryNote(deliveryNote1));
		assertThrows(DeliveryNoteNotFoundException.class, () -> {
			deliveryNoteService.deleteDeliveryNote(2L);
			deliveryNoteService.getDeliveryNoteByID(2L);
		});
		deliveryNoteService.addDeliveryNote(deliveryNote2);
		deliveryNote2.setDeliveryDate(LocalDate.of(2021, 7, 3));
		deliveryNoteService.updateDeliveryNote(deliveryNote2);
		assertEquals(deliveryNote2.getDeliveryDate(), deliveryNoteService.getDeliveryNoteByID(2L).getDeliveryDate());
	}

	@Test
	@org.junit.jupiter.api.Order(10)
	void DeliveryDetailTests() {
		DeliveryDetail deliveryDetail1 = deliveryDetailService.getDeliveryDetailByID(1L);
		DeliveryDetail deliveryDetail2 = new DeliveryDetail(2L, productService.getProductById(2L), 20, deliveryNoteService.getDeliveryNoteByID(2L));
		assertEquals(deliveryDetail2, deliveryDetailService.addDeliveryDetail(deliveryDetail2));
		assertEquals(deliveryDetail2.getDeliveryNote().getDeliveryNoteID(), deliveryDetailService.getDeliveryDetailByID(2L).getDeliveryNote().getDeliveryNoteID());
		assertThrows(DeliveryDetailAlreadyExistException.class, () -> deliveryDetailService.addDeliveryDetail(deliveryDetail1));
		assertThrows(DeliveryDetailNotFoundException.class, () -> {
			deliveryDetailService.deleteDeliveryDetail(2L);
			deliveryDetailService.getDeliveryDetailByID(2L);
		});
		deliveryDetailService.addDeliveryDetail(deliveryDetail2);
		deliveryDetail2.setDeliveryProduct(productService.getProductById(2L));
		deliveryDetailService.updateDeliveryDetail(deliveryDetail2);
		assertEquals(deliveryDetail2.getDeliveryProduct().getProductID(), deliveryDetailService.getDeliveryDetailByID(2L).getDeliveryProduct().getProductID());
	}

	@Test
	@org.junit.jupiter.api.Order(11)
	void SaleTests() {
		SaleInvoice saleInvoice1 = saleInvoiceService.getSaleInvoiceById(1L);
		SaleInvoice saleInvoice2 = new SaleInvoice(2L, LocalDate.of(2021, 3, 13), staffService.getStaffByID(2L), customerService.getCustomerByID(2L));
		SaleDetail saleDetail1 = saleDetailService.getSaleDetailByID(1L);
		SaleDetail saleDetail2 = new SaleDetail(2L, productService.getProductById(2L), 40, saleInvoice2);
		saleDetail2.setValue();
		Set<SaleDetail> set = new HashSet<>();
		set.add(saleDetail2);
		saleInvoice2.setSaleDetails(set);
		saleInvoice2.setPrice();
		assertEquals(saleInvoice2, saleInvoiceService.addSaleInvoice(saleInvoice2));
		assertEquals(saleInvoice2.getTotalPrice(), saleInvoiceService.getSaleInvoiceById(2L).getTotalPrice());
		assertThrows(SaleInvoiceAlreadyExistException.class, () -> saleInvoiceService.addSaleInvoice(saleInvoice1));
		assertThrows(SaleInvoiceNotFoundException.class, () -> {
			saleInvoiceService.deleteSaleInvoice(2L);
			saleInvoiceService.getSaleInvoiceById(2L);
		});
		saleInvoiceService.addSaleInvoice(saleInvoice2);
		saleInvoice2.setSaleDate(LocalDate.of(2020, 1,3));
		saleInvoiceService.updateSaleInvoice(saleInvoice2);
		assertEquals(saleInvoice2.getSaleDate(), saleInvoiceService.getSaleInvoiceById(2L).getSaleDate());

		assertThrows(SaleDetailNotFoundException.class, () -> {
			saleDetailService.deleteSaleDetail(2L);
			saleDetailService.getSaleDetailByID(2L);
		});
		assertEquals(saleDetail2, saleDetailService.addSaleDetail(saleDetail2));
		assertEquals(saleDetail2.getSalePrice(), saleDetailService.getSaleDetailByID(2L).getSalePrice());
		assertThrows(SaleDetailAlreadyExistException.class, () -> saleDetailService.addSaleDetail(saleDetail1));
		saleDetail2.setSaleQuantity(30);
		saleDetailService.updateSaleDetail(saleDetail2);
		assertEquals(saleDetail2.getSaleQuantity(), saleDetailService.getSaleDetailByID(2L).getSaleQuantity());
		assertEquals(saleDetail2.getSaleProduct().getPrice().multiply(new BigDecimal(saleDetail2.getSaleQuantity())),saleDetailService.getSaleDetailByID(2L).getTotalValue());
	}

}
