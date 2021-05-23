package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="delivery_details")
public class DeliveryDetail {
    @Id
    @Column
    private Long deliveryDetailID;

    @ManyToOne
    @JsonBackReference(value = "delivery-product")
    @JoinColumn(name="productID")
    private Product deliveryProduct;

    @Column
    private int deliveryQuantity;

    @ManyToOne
    @JsonBackReference(value = "delivery-detail")
    @JoinColumn(name = "deliveryNoteID")
    private DeliveryNote deliveryNote;

    public DeliveryDetail(){}

    public DeliveryDetail(Long deliveryDetailID, Product deliveryProduct, int deliveryQuantity, DeliveryNote deliveryNote){
        this.deliveryDetailID = deliveryDetailID;
        this.deliveryProduct = deliveryProduct;
        this.deliveryQuantity = deliveryQuantity;
        this.deliveryNote = deliveryNote;
    }

    public Long getDeliveryDetailID() {
        return deliveryDetailID;
    }

    public void setDeliveryDetailID(Long deliveryDetailID) {
        this.deliveryDetailID = deliveryDetailID;
    }

    public Product getDeliveryProduct() {
        return deliveryProduct;
    }

    public void setDeliveryProduct(Product deliveryProduct) {
        this.deliveryProduct = deliveryProduct;
    }

    public int getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(int deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }
}
