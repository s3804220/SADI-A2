package com.example.BackendComponent.service;

import com.example.BackendComponent.exception.CustomerAlreadyExistException;
import com.example.BackendComponent.exception.CustomerNotFoundException;
import com.example.BackendComponent.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.BackendComponent.entity.*;

@Transactional
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){
        if(!customerRepository.existsById(customer.getCustomerID())){
            customerRepository.save(customer);
        }else {
            throw new CustomerAlreadyExistException(customer.getCustomerID());
        }
        return customer;
    }

    public Customer updateCustomer(Customer customer){
        if(customerRepository.existsById(customer.getCustomerID())){
            customerRepository.save(customer);
        } else{
            throw new CustomerNotFoundException(customer.getCustomerID());
        }
        return customer;
    }

    public List<Customer> getAllCustomer(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return customerRepository.findAll(pageable).getContent();
    }

    public List<Customer> searchCustomer(String keyword,int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return customerRepository.search(keyword,pageable).getContent();
    }

    public List<Customer> searchCustomerBy(String name, String address, String phone, String fax, String email, String contact, int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return customerRepository.searchCustomerBy(name,address,phone,fax,email,contact, pageable).getContent();
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

    /*public Customer updateCustomer(Long id, Customer newCustomer){
        Customer customerToUpdate = getCustomerByID(id);
        customerToUpdate.setCustomerName(newCustomer.getCustomerName());
        customerToUpdate.setCustomerAddress(newCustomer.getCustomerAddress());
        customerToUpdate.setCustomerFax(newCustomer.getCustomerFax());
        customerToUpdate.setCustomerPhone(newCustomer.getCustomerPhone());
        customerToUpdate.setCustomerContactPerson(newCustomer.getCustomerContactPerson());
        customerToUpdate.setCustomerEmail(newCustomer.getCustomerEmail());
        return customerToUpdate;
    }*/
}
