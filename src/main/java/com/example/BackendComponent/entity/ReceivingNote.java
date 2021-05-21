package com.example.BackendComponent.entity;

import com.example.BackendComponent.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "receivingnote")
public class ReceivingNote {
    @Id
    @Column
    private Long receivingNoteID;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate receiveDate;

    @ManyToOne
    @JsonBackReference(value = "receive-staff")
    @JoinColumn(name="staffID")
    private Staff receiveStaff;

    @OneToOne
    @JsonBackReference(value = "receive-order")
    @JoinColumn(name = "orderID")
    private Order receiveOrder;

    @ManyToOne
    @JsonBackReference(value = "receive-product")
    @JoinColumn(name="productID")
    private Product receiveProduct;

    @Column
    private int receiveQuantity;

    public ReceivingNote(){}

    public ReceivingNote(Long receivingNoteID, LocalDate receiveDate, Staff receiveStaff, Order receiveOrder){
        this.receivingNoteID = receivingNoteID;
        this.receiveDate = receiveDate;
        this.receiveStaff = receiveStaff;
    }

    public Long getReceivingNoteID() {
        return receivingNoteID;
    }

    public void setReceivingNoteID(Long receivingNoteID) {
        this.receivingNoteID = receivingNoteID;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Staff getReceiveStaff() {
        return receiveStaff;
    }

    public void setReceiveStaff(Staff receiveStaff) {
        this.receiveStaff = receiveStaff;
    }

    public Product getReceiveProduct() {
        return receiveProduct;
    }

    public void setReceiveProduct(Product receiveProduct) {
        this.receiveProduct = receiveProduct;
    }

    public int getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(int receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public Order getReceiveOrder() {
        return receiveOrder;
    }

    public void setReceiveOrder(Order receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    @PrePersist
    @PreUpdate
    public void setDataFromOrder(){
        if(receiveOrder != null){
            receiveProduct = receiveOrder.getOrderProduct();
            receiveQuantity = receiveOrder.getOrderQuantity();
        }
    }
}
