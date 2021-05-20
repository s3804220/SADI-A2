package com.example.BackendComponent.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventoryReceivingNote")
public class InventoryReceivingNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryReceivingNoteId;

    @Column
    private Date inventoryReceivingNoteDate;

    @Column
    private int inventoryReceivingNoteStaff;

    @OneToOne(mappedBy = "inventoryReceivingNote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InventoryReceivingNote> inventoryReceivingNoteList;

    @ManyToOne
    @JoinColumn(name = "inventoryReceivingNoteStaff")
    private List<Staff> staffList;

    public InventoryReceivingNote(int inventoryReceivingNoteId, Date inventoryReceivingNoteDate, int inventoryReceivingNoteStaff, List<InventoryReceivingNote> inventoryReceivingNoteList, List<Staff> staffList) {
        this.inventoryReceivingNoteId = inventoryReceivingNoteId;
        this.inventoryReceivingNoteDate = inventoryReceivingNoteDate;
        this.inventoryReceivingNoteStaff = inventoryReceivingNoteStaff;
        this.inventoryReceivingNoteList = inventoryReceivingNoteList;
        this.staffList = staffList;
    }

    public int getInventoryReceivingNoteId() {
        return inventoryReceivingNoteId;
    }

    public void setInventoryReceivingNoteId(int inventoryReceivingNoteId) {
        this.inventoryReceivingNoteId = inventoryReceivingNoteId;
    }

    public Date getInventoryReceivingNoteDate() {
        return inventoryReceivingNoteDate;
    }

    public void setInventoryReceivingNoteDate(Date inventoryReceivingNoteDate) {
        this.inventoryReceivingNoteDate = inventoryReceivingNoteDate;
    }

    public int getInventoryReceivingNoteStaff() {
        return inventoryReceivingNoteStaff;
    }

    public void setInventoryReceivingNoteStaff(int inventoryReceivingNoteStaff) {
        this.inventoryReceivingNoteStaff = inventoryReceivingNoteStaff;
    }

    public List<InventoryReceivingNote> getInventoryReceivingNoteList() {
        return inventoryReceivingNoteList;
    }

    public void setInventoryReceivingNoteList(List<InventoryReceivingNote> inventoryReceivingNoteList) {
        this.inventoryReceivingNoteList = inventoryReceivingNoteList;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
