package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryNote",cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "delivery-detail")
    private Set<DeliveryDetail> deliveryDetails;

    public DeliveryNote(){}

    public DeliveryNote(Long deliveryNoteID, LocalDate deliveryDate, Staff deliveryStaff){
        this.deliveryNoteID = deliveryNoteID;
        this.deliveryDate = deliveryDate;
        this.deliveryStaff = deliveryStaff;
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

    public Set<DeliveryDetail> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(Set<DeliveryDetail> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
