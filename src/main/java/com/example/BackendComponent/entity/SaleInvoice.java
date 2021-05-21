package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saleinvoice")
public class SaleInvoice {
    @Id
    @Column
    private Long saleID;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate saleDate;

    @ManyToOne
    @JsonBackReference(value = "sale-staff")
    @JoinColumn(name="staffID")
    private Staff saleStaff;

    @ManyToOne
    @JsonBackReference(value = "sale-customer")
    @JoinColumn(name="customerID")
    private Customer customer;

    @ManyToOne
    @JsonBackReference(value = "sale-product")
    @JoinColumn(name="productID")
    private Product saleProduct;

    @Column
    private int saleQuantity;

    @Column
    private BigDecimal salePrice;

    @Column
    private BigDecimal totalValue;

    public SaleInvoice(){}

    public SaleInvoice(Long saleID, LocalDate saleDate, Staff saleStaff, Customer customer, Product saleProduct, int saleQuantity){
        this.saleID = saleID;
        this.saleDate = saleDate;
        this.saleStaff = saleStaff;
        this.customer = customer;
        this.saleProduct = saleProduct;
        this.saleQuantity = saleQuantity;
    }

    public Long getSaleID() {
        return saleID;
    }

    public void setSaleID(Long saleID) {
        this.saleID = saleID;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Staff getSaleStaff() {
        return saleStaff;
    }

    public void setSaleStaff(Staff saleStaff) {
        this.saleStaff = saleStaff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(Product saleProduct) {
        this.saleProduct = saleProduct;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    @PrePersist
    @PreUpdate
    public void setValue(){
        if(saleProduct != null){
            salePrice = saleProduct.getPrice();
            totalValue = salePrice.multiply(BigDecimal.valueOf(saleQuantity));
        }
    }
}
