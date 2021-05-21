package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deliverynote")
public class DeliveryNote {
    @Id
    @Column
    private Long deliveryNoteID;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate deliveryDate;

    @ManyToOne
    @JsonBackReference(value = "delivery-staff")
    @JoinColumn(name="staffID")
    private Staff deliveryStaff;

    @ManyToOne
    @JsonBackReference(value = "delivery-product")
    @JoinColumn(name="productID")
    private Product deliveryProduct;

    @Column
    private int deliveryQuantity;

    public DeliveryNote(){}

    public DeliveryNote(Long deliveryNoteID, LocalDate deliveryDate, Staff deliveryStaff, Order deliveryOrder){
        this.deliveryNoteID = deliveryNoteID;
        this.deliveryDate = deliveryDate;
        this.deliveryStaff = deliveryStaff;
        this.deliveryProduct = deliveryOrder.getOrderProduct();
        this.deliveryQuantity = deliveryOrder.getOrderQuantity();
    }

    public Long getDeliveryNoteID() {
        return deliveryNoteID;
    }

    public void setDeliveryNoteID(Long deliveryNoteID) {
        this.deliveryNoteID = deliveryNoteID;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Staff getDeliveryStaff() {
        return deliveryStaff;
    }

    public void setDeliveryStaff(Staff deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
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
}
