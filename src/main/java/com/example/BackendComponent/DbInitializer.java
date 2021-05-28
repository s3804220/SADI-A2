package com.example.BackendComponent;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@ConditionalOnProperty(name = "app.init-db", havingValue = "true")
public class DbInitializer implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private ProviderRepository providerRepository;
    private StaffRepository staffRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private DeliveryNoteRepository deliveryNoteRepository;
    private DeliveryDetailRepository deliveryDetailRepository;
    private ReceivingNoteRepository receivingNoteRepository;
    private ReceivingDetailRepository receivingDetailRepository;

    DbInitializer(CategoryRepository categoryRepository, ProductRepository productRepository, CustomerRepository customerRepository, ProviderRepository providerRepository, StaffRepository staffRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, DeliveryNoteRepository deliveryNoteRepository, DeliveryDetailRepository deliveryDetailRepository, ReceivingNoteRepository receivingNoteRepository, ReceivingDetailRepository receivingDetailRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.staffRepository = staffRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.deliveryDetailRepository = deliveryDetailRepository;
        this.receivingNoteRepository = receivingNoteRepository;
        this.receivingDetailRepository = receivingDetailRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        this.orderDetailRepository.deleteAll();
        this.deliveryDetailRepository.deleteAll();
        this.receivingDetailRepository.deleteAll();
        this.receivingNoteRepository.deleteAll();
        this.deliveryNoteRepository.deleteAll();
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
        ReceivingNote receivingNote1 = new ReceivingNote(1L, LocalDate.of(2021,03,18), staff1, order1);
        DeliveryNote deliveryNote1 = new DeliveryNote(1L, LocalDate.of(2021, 04, 17), staff1);


        this.categoryRepository.save(category1);
        this.productRepository.save(product1);
        this.customerRepository.save(customer1);
        this.providerRepository.save(provider1);
        this.staffRepository.save(staff1);
        this.orderRepository.save(order1);
        this.receivingNoteRepository.save(receivingNote1);
        this.deliveryNoteRepository.save(deliveryNote1);
    }
}
