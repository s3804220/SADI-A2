package com.example.BackendComponent.entity;

import com.example.BackendComponent.repository.OrderRepository;
import com.fasterxml.jackson.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receivingNote",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "receive-detail")
    private Set<ReceivingDetail> receivingDetails;

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

    public Order getReceiveOrder() {
        return receiveOrder;
    }

    public void setReceiveOrder(Order receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    public Set<ReceivingDetail> getReceivingDetails() {
        return receivingDetails;
    }

    public void setReceivingDetails(Set<ReceivingDetail> receivingDetails) {
        this.receivingDetails = receivingDetails;
    }

    /*@PrePersist
    @PreUpdate
    public void setDataFromOrder(){
        if(receiveOrder != null){
            receiveProduct = receiveOrder.getOrderProduct();
            receiveQuantity = receiveOrder.getOrderQuantity();
        }
    }*/
}
