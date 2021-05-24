package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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

    @Column
    private BigDecimal totalPrice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleInvoice",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "sale-detail")
    private Set<SaleDetail> saleDetails;

    public SaleInvoice(){}

    public SaleInvoice(Long saleID, LocalDate saleDate, Staff saleStaff, Customer customer){
        this.saleID = saleID;
        this.saleDate = saleDate;
        this.saleStaff = saleStaff;
        this.customer = customer;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(Set<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }

    @PrePersist
    @PreUpdate
    public void setPrice(){
        if(saleDetails != null){
            totalPrice = BigDecimal.ZERO;
            for(SaleDetail aDetail : saleDetails){
                if(aDetail.getTotalValue() != null){
                    totalPrice = totalPrice.add(aDetail.getTotalValue());
                }
            }
        }
    }
}
