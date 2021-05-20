package com.example.BackendComponent.controller;

import java.util.List;

import com.example.BackendComponent.service.CustomerService;
import com.example.BackendComponent.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path="/customers")
    public List<Customer> getAllCustomer(){return customerService.getAllCustomer(null);}

    @RequestMapping(path="/customers/{id}")
    public Customer getCustomerByID(@PathVariable Long id){return customerService.getCustomerByID(id);}

    @PostMapping(path="/customers")
    public Customer addCustomer(@RequestBody Customer customer){return customerService.addCustomer(customer);}

    @PutMapping(path="/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody final Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping(path="/customers/{id}")
    public Customer deleteCustomer(@PathVariable Long id){return customerService.deleteCustomer(id);}

    @RequestMapping(path="/customers/search")
    public List<Customer> searchCustomer(@Param("keyword") String keyword){
        return customerService.getAllCustomer(keyword);
    }
}
