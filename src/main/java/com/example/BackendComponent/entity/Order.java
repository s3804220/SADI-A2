package com.example.BackendComponent.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "company_order")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderDate;

    @Column
    private int orderQuantity;

    @ManyToOne
    @JsonIgnore
    private Product orderProduct;

    @ManyToOne
    @JsonIgnore
    private Provider orderProvider;

    @ManyToOne
    @JsonIgnore
    private Staff orderStaff;

    public Order(){}

    public Order(long orderID, LocalDate orderDate, int orderQuantity) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderQuantity = orderQuantity;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    public void deleteOrderProduct(){this.orderProduct = null;}

    public Provider getOrderProvider() {
        return orderProvider;
    }

    public void setOrderProvider(Provider orderProvider) {
        this.orderProvider = orderProvider;
    }

    public void deleteOrderProvider(){this.orderProvider = null;}

    public Staff getOrderStaff() {
        return orderStaff;
    }

    public void setOrderStaff(Staff orderStaff) {
        this.orderStaff = orderStaff;
    }

    public void deleteOrderStaff(){
        this.orderStaff = null;
    }

    @Override
    public String toString() {
        String output = "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                ", orderQuantity=" + orderQuantity;
        if (orderProduct != null){
            output += ", orderProduct=" + orderProduct;
        }
        if (orderProvider != null){
            output += ", orderProvider=" + orderProvider;
        }
        if (orderStaff != null){
            output += ", orderStaff=" + orderStaff;
        }
        output += '}';
        return output;
    }
}
