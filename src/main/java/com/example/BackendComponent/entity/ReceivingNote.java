package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "receivingnote")
public class ReceivingNote {
    @Id
    @Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receivingNoteID;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate receiveDate;

    @ManyToOne
    @JsonBackReference(value = "receive-staff")
    @JoinColumn(name="staffID")
    private Staff receiveStaff;

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
        this.receiveProduct = receiveOrder.getOrderProduct();
        this.receiveQuantity = receiveOrder.getOrderQuantity();
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
}
