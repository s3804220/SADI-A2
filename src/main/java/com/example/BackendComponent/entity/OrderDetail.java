package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_details")
public class OrderDetail {
    @Id
    @Column
    private Long orderDetailID;

    @ManyToOne
    @JsonBackReference(value = "order-product")
    @JoinColumn(name="productID")
    private Product orderProduct;

    @Column
    private int orderQuantity;

    @Column
    private BigDecimal orderPrice;

    @ManyToOne
    @JsonBackReference(value = "order-detail")
    @JoinColumn(name="orderID")
    private Order order;

    public OrderDetail(){}

    public OrderDetail(Long orderDetailID, Product orderProduct, int orderQuantity, Order order){
        this.orderDetailID = orderDetailID;
        this.orderProduct = orderProduct;
        this.orderQuantity = orderQuantity;
        this.order = order;
    }

    public Long getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Long orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @PrePersist
    @PreUpdate
    public void setPrice(){
        if(orderProduct!= null){
            orderPrice = orderProduct.getPrice();
        }
    }
}
