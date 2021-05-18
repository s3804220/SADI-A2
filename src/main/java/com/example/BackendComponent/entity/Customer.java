package com.example.BackendComponent.entity;
import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String customerContactPerson;

    @Column
    private String customerEmail;

    public Customer(){};

    public Customer(String customerName, String customerAddress, String customerPhone, String customerFax, String customerContactPerson, String customerEmail) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerFax = customerFax;
        this.customerContactPerson = customerContactPerson;
        this.customerEmail = customerEmail;
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

    public String getCustomerContactPerson() {
        return customerContactPerson;
    }

    public void setCustomerContactPerson(String customerContactPerson) {
        this.customerContactPerson = customerContactPerson;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
