package com.example.BackendComponent.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToOne(mappedBy = "inventoryDeliveryNote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InventoryDeliveryNote> inventoryDeliveryNoteList;

    @ManyToOne
    @JoinColumn(name = "inventoryDeliveryNoteStaff")
    private List<Staff> staffList;

    public InventoryDeliveryNote(int inventoryDeliveryNoteId, Date inventoryDeliveryNoteDate, Staff inventoryDeliveryNoteStaff, List<InventoryDeliveryNote> inventoryDeliveryNoteList, List<Staff> staffList) {
        this.inventoryDeliveryNoteId = inventoryDeliveryNoteId;
        this.inventoryDeliveryNoteDate = inventoryDeliveryNoteDate;
        this.inventoryDeliveryNoteStaff = inventoryDeliveryNoteStaff;
        this.inventoryDeliveryNoteList = inventoryDeliveryNoteList;
        this.staffList = staffList;
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

    public List<InventoryDeliveryNote> getInventoryDeliveryNoteList() {
        return inventoryDeliveryNoteList;
    }

    public void setInventoryDeliveryNoteList(List<InventoryDeliveryNote> inventoryDeliveryNoteList) {
        this.inventoryDeliveryNoteList = inventoryDeliveryNoteList;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
