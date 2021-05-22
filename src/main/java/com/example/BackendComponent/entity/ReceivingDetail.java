package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="receiving_details")
public class ReceivingDetail {
    @Id
    @Column
    private Long receivingDetailID;

    @ManyToOne
    @JsonBackReference(value = "receive-product")
    @JoinColumn(name="productID")
    private Product receiveProduct;

    @Column
    private int receiveQuantity;

    @ManyToOne
    @JsonBackReference(value = "receive-detail")
    @JoinColumn(name = "receivingNoteID")
    private ReceivingNote receivingNote;

    public ReceivingDetail(){}

    public ReceivingDetail(Long receivingDetailID, Product receiveProduct, int receiveQuantity, ReceivingNote receivingNote){
        this.receivingDetailID = receivingDetailID;
        this.receiveProduct = receiveProduct;
        this.receiveQuantity = receiveQuantity;
        this.receivingNote = receivingNote;
    }

    public Long getReceivingDetailID() {
        return receivingDetailID;
    }

    public void setReceivingDetailID(Long receivingDetailID) {
        this.receivingDetailID = receivingDetailID;
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

    public ReceivingNote getReceivingNote() {
        return receivingNote;
    }

    public void setReceivingNote(ReceivingNote receivingNote) {
        this.receivingNote = receivingNote;
    }
}
