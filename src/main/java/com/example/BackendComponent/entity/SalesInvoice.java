package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "salesInvoice")
public class SalesInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesInvoiceId;

    @Column
    private Date salesInvoiceDate;

    @Column
    private Staff salesInvoiceStaff;

    @Column
    private Customer salesInvoiceCustomer;

    @Column
    private String salesInvoiceQuantity;

    @Column
    private float salesInvoicePrice;

    @Column
    private float salesInvoiceTotalValue;

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Customer> customerList;

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Staff> staffList;

    public SalesInvoice(int salesInvoiceId, Date salesInvoiceDate, Staff salesInvoiceStaff, Customer salesInvoiceCustomer, String salesInvoiceQuantity, float salesInvoicePrice, float salesInvoiceTotalValue) {
        this.salesInvoiceId = salesInvoiceId;
        this.salesInvoiceDate = salesInvoiceDate;
        this.salesInvoiceStaff = salesInvoiceStaff;
        this.salesInvoiceCustomer = salesInvoiceCustomer;
        this.salesInvoiceQuantity = salesInvoiceQuantity;
        this.salesInvoicePrice = salesInvoicePrice;
        this.salesInvoiceTotalValue = salesInvoiceTotalValue;
    }

    public int getSalesInvoiceId() {
        return salesInvoiceId;
    }

    public void setSalesInvoiceId(int salesInvoiceId) {
        this.salesInvoiceId = salesInvoiceId;
    }

    public Date getSalesInvoiceDate() {
        return salesInvoiceDate;
    }

    public void setSalesInvoiceDate(Date salesInvoiceDate) {
        this.salesInvoiceDate = salesInvoiceDate;
    }

    public Staff getSalesInvoiceStaff() {
        return salesInvoiceStaff;
    }

    public void setSalesInvoiceStaff(Staff salesInvoiceStaff) {
        this.salesInvoiceStaff = salesInvoiceStaff;
    }

    public Customer getSalesInvoiceCustomer() {
        return salesInvoiceCustomer;
    }

    public void setSalesInvoiceCustomer(Customer salesInvoiceCustomer) {
        this.salesInvoiceCustomer = salesInvoiceCustomer;
    }

    public String getSalesInvoiceQuantity() {
        return salesInvoiceQuantity;
    }

    public void setSalesInvoiceQuantity(String salesInvoiceQuantity) {
        this.salesInvoiceQuantity = salesInvoiceQuantity;
    }

    public float getSalesInvoicePrice() {
        return salesInvoicePrice;
    }

    public void setSalesInvoicePrice(float salesInvoicePrice) {
        this.salesInvoicePrice = salesInvoicePrice;
    }

    public float getSalesInvoiceTotalValue() {
        return salesInvoiceTotalValue;
    }

    public void setSalesInvoiceTotalValue(float salesInvoiceTotalValue) {
        this.salesInvoiceTotalValue = salesInvoiceTotalValue;
    }

    public Set<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Set<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addSalesInvoiceCustomer(Customer customer) {
        this.customerList.add(customer);
    }

    public void deleteSalesInvoiceCustomer(Customer customer) {
        this.customerList.remove(customer);
    }

    public Set<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(Set<Staff> staffList) {
        this.staffList = staffList;
    }

    public void addSalesInvoiceStaff(Staff staff) {
        this.staffList.add(staff);
    }

    public void deleteSalesInvoiceStaff(Staff staff) {
        this.staffList.remove(staff);
    }

    @Override
    public String toString() {
        return "SalesInvoice{" +
                "salesInvoiceId=" + salesInvoiceId +
                ", salesInvoiceDate=" + salesInvoiceDate +
                ", salesInvoiceStaff=" + salesInvoiceStaff +
                ", salesInvoiceCustomer=" + salesInvoiceCustomer +
                ", salesInvoiceQuantity='" + salesInvoiceQuantity + '\'' +
                ", salesInvoicePrice=" + salesInvoicePrice +
                ", salesInvoiceTotalValue=" + salesInvoiceTotalValue +
                ", customerList=" + customerList +
                ", staffList=" + staffList +
                '}';
    }
}
