package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column
    private Long orderID;

    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderDate;

    @ManyToOne
    @JsonBackReference(value = "order-staff")
    @JoinColumn(name="staffID")
    private Staff orderStaff;

    @ManyToOne
    @JsonBackReference(value = "order-provider")
    @JoinColumn(name="providerID")
    private Provider orderProvider;

    @ManyToOne
    @JsonBackReference(value = "order-product")
    @JoinColumn(name="productID")
    private Product orderProduct;

    @Column
    private int orderQuantity;

    @Column
    private BigDecimal orderPrice;

    @OneToOne(mappedBy = "receiveOrder")
    @JsonManagedReference(value = "receive-order")
    private ReceivingNote noteForOrder;

    public Order(){}

    public Order(Long orderID, LocalDate orderDate, Staff orderStaff, Provider orderProvider, Product orderProduct, int orderQuantity) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStaff = orderStaff;
        this.orderProvider = orderProvider;
        this.orderProduct = orderProduct;
        this.orderQuantity = orderQuantity;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
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

    //public void deleteOrderProduct(){this.orderProduct = null;}

    public Provider getOrderProvider() {
        return orderProvider;
    }

    public void setOrderProvider(Provider orderProvider) {
        this.orderProvider = orderProvider;
    }

    //public void deleteOrderProvider(){this.orderProvider = null;}

    public Staff getOrderStaff() {
        return orderStaff;
    }

    public void setOrderStaff(Staff orderStaff) {
        this.orderStaff = orderStaff;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public ReceivingNote getNoteForOrder() {
        return noteForOrder;
    }

    public void setNoteForOrder(ReceivingNote noteForOrder) {
        this.noteForOrder = noteForOrder;
    }

    @PrePersist
    @PreUpdate
    public void setPrice(){
        if(orderProduct!= null){
            orderPrice = orderProduct.getPrice();
        }
    }

    //public void deleteOrderStaff(){this.orderStaff = null;}

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
