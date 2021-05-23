package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "inventoryDeliveryNote")
public class InventoryDeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryDeliveryNoteId;

    @Column
    private Date inventoryDeliveryNoteDate;

    @Column
    private Staff inventoryDeliveryNoteStaff;

    @OneToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InventoryDeliveryNote> inventoryDeliveryNoteList;

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Staff> staffList;

    public InventoryDeliveryNote(int inventoryDeliveryNoteId, Date inventoryDeliveryNoteDate, Staff inventoryDeliveryNoteStaff) {
        this.inventoryDeliveryNoteId = inventoryDeliveryNoteId;
        this.inventoryDeliveryNoteDate = inventoryDeliveryNoteDate;
        this.inventoryDeliveryNoteStaff = inventoryDeliveryNoteStaff;
    }

    public int getInventoryDeliveryNoteId() {
        return inventoryDeliveryNoteId;
    }

    public void setInventoryDeliveryNoteId(int inventoryDeliveryNoteId) {
        this.inventoryDeliveryNoteId = inventoryDeliveryNoteId;
    }

    public Date getInventoryDeliveryNoteDate() {
        return inventoryDeliveryNoteDate;
    }

    public void setInventoryDeliveryNoteDate(Date inventoryDeliveryNoteDate) {
        this.inventoryDeliveryNoteDate = inventoryDeliveryNoteDate;
    }

    public Staff getInventoryDeliveryNoteStaff() {
        return inventoryDeliveryNoteStaff;
    }

    public void setInventoryDeliveryNoteStaff(Staff inventoryDeliveryNoteStaff) {
        this.inventoryDeliveryNoteStaff = inventoryDeliveryNoteStaff;
    }

    public Set<InventoryDeliveryNote> getInventoryDeliveryNoteList() {
        return inventoryDeliveryNoteList;
    }

    public void setInventoryDeliveryNoteList(Set<InventoryDeliveryNote> inventoryDeliveryNoteList) {
        this.inventoryDeliveryNoteList = inventoryDeliveryNoteList;
    }

    public void addInventoryDeliveryNote(InventoryDeliveryNote inventoryDeliveryNote) {
        this.inventoryDeliveryNoteList.add(inventoryDeliveryNote);
    }

    public void deleteInventoryDeliveryNote(InventoryDeliveryNote inventoryDeliveryNote) {
        this.inventoryDeliveryNoteList.remove(inventoryDeliveryNote);
    }

    public Set<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(Set<Staff> staffList) {
        this.staffList = staffList;
    }

    public void addInventoryDeliveryNoteStaff (Staff staff) {
        this.staffList.add(staff);
    }

    public void deleteInventoryDeliveryNoteStaff (Staff staff) {
        this.staffList.remove(staff);
    }

    @Override
    public String toString() {
        return "InventoryDeliveryNote{" +
                "inventoryDeliveryNoteId=" + inventoryDeliveryNoteId +
                ", inventoryDeliveryNoteDate=" + inventoryDeliveryNoteDate +
                ", inventoryDeliveryNoteStaff=" + inventoryDeliveryNoteStaff +
                ", inventoryDeliveryNoteList=" + inventoryDeliveryNoteList +
                ", staffList=" + staffList +
                '}';
    }
}
