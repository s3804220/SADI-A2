package com.example.BackendComponent;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DbInitializer implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private ProviderRepository providerRepository;
    private StaffRepository staffRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private DeliveryNoteRepository deliveryNoteRepository;

    DbInitializer(CategoryRepository categoryRepository, ProductRepository productRepository, CustomerRepository customerRepository, ProviderRepository providerRepository, StaffRepository staffRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, DeliveryNoteRepository deliveryNoteRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.staffRepository = staffRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.deliveryNoteRepository = deliveryNoteRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        this.deliveryNoteRepository.deleteAll();
        this.orderDetailRepository.deleteAll();
        this.orderRepository.deleteAll();
        this.productRepository.deleteAll();
        this.categoryRepository.deleteAll();
        this.customerRepository.deleteAll();
        this.providerRepository.deleteAll();
        this.staffRepository.deleteAll();

        Category category1 = new Category(1L, "Phone");
        Product product1 = new Product(1L, "iPhone", "10", "Apple", "Apple", "iPhone X", new BigDecimal(999), category1);
        Customer customer1 = new Customer(1L, "Hannah", "123 Abc str", "0298145237", "13235551234", "cus1@email.com", "That Guy");
        Provider provider1 = new Provider(1L, "AAA Retail", "546 London Street", "0984756954", "152495118934", "prov@game.co", "Keanu Reeves");
        Staff staff1 = new Staff(1L, "Suzie Nguyen", "303 Flower Boulevard", "0658794158", "astaff@mycompany.com");
        Order order1 = new Order(1L, LocalDate.of(2021, 03, 17), staff1, provider1);
        OrderDetail orderDetail1 = new OrderDetail(1L, product1, 20, new BigDecimal(19980), order1);


        this.categoryRepository.save(category1);
        this.productRepository.save(product1);
        this.customerRepository.save(customer1);
        this.providerRepository.save(provider1);
        this.staffRepository.save(staff1);
        this.orderRepository.save(order1);
        this.orderDetailRepository.save(orderDetail1);
    }
}
