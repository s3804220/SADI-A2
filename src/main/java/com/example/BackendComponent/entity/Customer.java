package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @Column
    private Long customerID;

    @Column
    private String customerName;

    @Column
    private String customerAddress;

    @Column
    private String customerPhone;

    @Column
    private String customerFax;

    @Column
    private String customerEmail;

    @Column
    private String customerContactPerson;

    @JsonManagedReference(value = "sale-customer")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<SaleInvoice> customerInvoice;

    public Customer(){};

    public Customer(Long customerID, String customerName, String customerAddress, String customerPhone, String customerFax, String customerEmail, String customerContactPerson) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerFax = customerFax;
        this.customerEmail = customerEmail;
        this.customerContactPerson = customerContactPerson;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerContactPerson() {
        return customerContactPerson;
    }

    public void setCustomerContactPerson(String customerContactPerson) {
        this.customerContactPerson = customerContactPerson;
    }

    public Set<SaleInvoice> getCustomerInvoice() {
        return customerInvoice;
    }

    public void setCustomerInvoice(Set<SaleInvoice> customerInvoice) {
        this.customerInvoice = customerInvoice;
    }
}
