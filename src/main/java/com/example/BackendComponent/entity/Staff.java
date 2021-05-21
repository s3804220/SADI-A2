package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="staff")
public class Staff {
    @Id
    @Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffID;

    @Column
    private String staffName;

    @Column
    private String staffAddress;

    @Column
    private String staffPhone;

    @Column
    private String staffEmail;

    @JsonManagedReference(value = "order-staff")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderStaff")
    private Set<Order> staffOrders;

    @JsonManagedReference(value = "receive-staff")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiveStaff")
    private Set<ReceivingNote> staffReceivingNotes;

    @JsonManagedReference(value = "delivery-staff")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryStaff")
    private Set<DeliveryNote> staffDeliveryNotes;

    @JsonManagedReference(value = "sale-staff")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleStaff")
    private Set<SaleInvoice> staffInvoice;

    public Staff(){};

    public Staff(Long staffID, String staffName, String staffAddress, String phone, String email) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffAddress = staffAddress;
        this.staffPhone = phone;
        this.staffEmail = email;
    }

    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public Set<Order> getStaffOrders() {
        return staffOrders;
    }

    public void setStaffOrders(Set<Order> staffOrders) {
        this.staffOrders = staffOrders;
    }

    public Set<ReceivingNote> getStaffReceivingNotes() {
        return staffReceivingNotes;
    }

    public void setStaffReceivingNotes(Set<ReceivingNote> staffReceivingNotes) {
        this.staffReceivingNotes = staffReceivingNotes;
    }

    public Set<DeliveryNote> getStaffDeliveryNotes() {
        return staffDeliveryNotes;
    }

    public void setStaffDeliveryNotes(Set<DeliveryNote> staffDeliveryNotes) {
        this.staffDeliveryNotes = staffDeliveryNotes;
    }

    public Set<SaleInvoice> getStaffInvoice() {
        return staffInvoice;
    }

    public void setStaffInvoice(Set<SaleInvoice> staffInvoice) {
        this.staffInvoice = staffInvoice;
    }

    /*public void addStaffOrders(Order order){this.staffOrders.add(order);}

    public void deleteStaffOrders(Order order){this.staffOrders.remove(order);}*/

    @Override
    public String toString() {
        return
                "staffName='" + staffName + '\'' +
                ", staffAddress='" + staffAddress + '\'' +
                ", staffPhone='" + staffPhone + '\'' + ", staffEmail='" + staffEmail + '\'';
    }
}
