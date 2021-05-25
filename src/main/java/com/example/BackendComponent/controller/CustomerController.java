package com.example.BackendComponent.controller;

import java.util.List;
import java.util.Optional;

import com.example.BackendComponent.service.CustomerService;
import com.example.BackendComponent.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/customers")
    public List<Customer> getAllCustomer(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return customerService.getAllCustomer(thePage, pageable);
    }

    @GetMapping(path="/customers/{id}")
    public Customer getCustomerByID(@PathVariable Long id){return customerService.getCustomerByID(id);}

    @PostMapping(path="/customers")
    public Customer addCustomer(@RequestBody Customer customer){return customerService.addCustomer(customer);}

    @PutMapping(path="/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(path="/customers/{id}")
    public Customer deleteCustomer(@PathVariable Long id){return customerService.deleteCustomer(id);}

    @GetMapping(path="/customers/search")
    public List<Customer> searchCustomer(@RequestParam("keyword") String keyword, @RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return customerService.searchCustomer(keyword,thePage,pageable);
    }

    @GetMapping(path="/customers/search/filter")
    public List<Customer> searchCustomerBy(@RequestParam Optional<String> name, @RequestParam Optional<String> address, @RequestParam Optional<String> phone, @RequestParam Optional<String> fax, @RequestParam Optional<String> email,@RequestParam Optional<String> contact, @RequestParam Optional<Integer> page){
        String newname, newaddress, newphone, newfax, newemail, newcontact;
        newname = name.orElse(null);
        newaddress = address.orElse(null);
        newphone = phone.orElse(null);
        newfax = fax.orElse(null);
        newemail = email.orElse(null);
        newcontact = contact.orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return customerService.searchCustomerBy(newname,newaddress,newphone,newfax,newemail,newcontact,thePage,pageable);
    }
}
