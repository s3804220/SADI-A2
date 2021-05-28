package com.example.BackendComponent;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.repository.*;
import com.example.BackendComponent.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<OrderDetail> orderList = orderDetailService.getAllOrderDetails(0, false);
        for (OrderDetail temp: orderList) {
            orderDetailService.deleteOrderDetail(temp.getOrderDetailID());
        }
        List<ReceivingDetail> receivingDetailList = receivingDetailService.getAllReceivingDetails(0, false);
        for (ReceivingDetail temp: receivingDetailList) {
            receivingDetailService.deleteReceivingDetail(temp.getReceivingDetailID());
        }
        List<DeliveryDetail> deliveryDetailList = deliveryDetailService.getAllDeliveryDetails(0, false);
        for (DeliveryDetail temp: deliveryDetailList) {
            deliveryDetailService.deleteDeliveryDetail(temp.getDeliveryDetailID());
        }
        List<SaleInvoice> saleInvoiceList = saleInvoiceService.getAllSaleInvoice(0, false);
        for (SaleInvoice temp: saleInvoiceList) {
            saleInvoiceService.deleteSaleInvoice(temp.getSaleID());
        }
        List<SaleDetail> saleDetailList = saleDetailService.getAllSaleDetails(0, false);
        for (SaleDetail temp: saleDetailList) {
            saleDetailService.deleteSaleDetail(temp.getSaleDetailID());
        }
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
        Provider provider1 = new Provider(1L, "AAA Retail", "546 London Street", "0984756954", "152495118934", "prov1@game.co", "Keanu Reeves");
        Staff staff1 = new Staff(1L, "Suzie Nguyen", "303 Flower Boulevard", "0658794158", "astaff@mycompany.com");
        Order order1 = new Order(1L, LocalDate.of(2021, 3, 17), staff1, provider1);
        OrderDetail orderDetail1 = new OrderDetail(1L, product1, 20, new BigDecimal(19980), order1);
        ReceivingNote receivingNote1 = new ReceivingNote(1L, LocalDate.of(2021,3,18), staff1, order1);
        DeliveryNote deliveryNote1 = new DeliveryNote(1L, LocalDate.of(2021, 4, 17), staff1);
        DeliveryDetail deliveryDetail1 = new DeliveryDetail(1L, product1, 20, deliveryNote1);
        SaleInvoice saleInvoice1 = new SaleInvoice(1L, LocalDate.of(2021, 3, 17), staff1, customer1);
        SaleDetail saleDetail1 = new SaleDetail(1L, product1, 20, saleInvoice1);
        saleDetail1.setValue();
        Set<SaleDetail> set = new HashSet<>();
        set.add(saleDetail1);
        saleInvoice1.setSaleDetails(set);
        saleInvoice1.setPrice();

        categoryService.addCategory(category1);
        productService.addProduct(product1);
        customerService.addCustomer(customer1);
        providerService.addProvider(provider1);
        staffService.addStaff(staff1);
        orderService.addOrder(order1);
        orderDetailService.addOrderDetail(orderDetail1);
        receivingNoteService.addReceivingNote(receivingNote1);
        deliveryNoteService.addDeliveryNote(deliveryNote1);
        deliveryDetailService.addDeliveryDetail(deliveryDetail1);
        saleInvoiceService.addSaleInvoice(saleInvoice1);
    }
}
