package com.example.BackendComponent.service;

import com.example.BackendComponent.exception.CustomerNotFoundException;
import com.example.BackendComponent.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.BackendComponent.entity.*;

@Transactional
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return StreamSupport
                .stream(customerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Customer getCustomerByID(Long id){
        return customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException(id));
    }

    public Customer deleteCustomer(Long id){
        Customer customerToDelete = getCustomerByID(id);
        customerRepository.delete(customerToDelete);
        return customerToDelete;
    }

    public Customer updateCustomer(Long id, Customer newCustomer){
        Customer customerToUpdate = getCustomerByID(id);
        customerToUpdate.setCustomerName(newCustomer.getCustomerName());
        customerToUpdate.setCustomerAddress(newCustomer.getCustomerAddress());
        customerToUpdate.setCustomerFax(newCustomer.getCustomerFax());
        customerToUpdate.setCustomerPhone(newCustomer.getCustomerPhone());
        customerToUpdate.setCustomerContactPerson(newCustomer.getCustomerContactPerson());
        customerToUpdate.setCustomerEmail(newCustomer.getCustomerEmail());
        return customerToUpdate;
    }
}
