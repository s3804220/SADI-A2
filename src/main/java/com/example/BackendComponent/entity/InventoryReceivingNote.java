package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "inventoryReceivingNote")
public class InventoryReceivingNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryReceivingNoteId;

    @Column
    private Date inventoryReceivingNoteDate;

    @Column
    private Staff inventoryReceivingNoteStaff;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InventoryReceivingNote> inventoryReceivingNoteList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Staff> staffList;

    public InventoryReceivingNote(int inventoryReceivingNoteId, Date inventoryReceivingNoteDate, Staff inventoryReceivingNoteStaff) {
        this.inventoryReceivingNoteId = inventoryReceivingNoteId;
        this.inventoryReceivingNoteDate = inventoryReceivingNoteDate;
        this.inventoryReceivingNoteStaff = inventoryReceivingNoteStaff;
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

    public Staff getInventoryReceivingNoteStaff() {
        return inventoryReceivingNoteStaff;
    }

    public void setInventoryReceivingNoteStaff(Staff inventoryReceivingNoteStaff) {
        this.inventoryReceivingNoteStaff = inventoryReceivingNoteStaff;
    }

    public Set<InventoryReceivingNote> getInventoryReceivingNoteList() {
        return inventoryReceivingNoteList;
    }

    public void setInventoryReceivingNoteList(Set<InventoryReceivingNote> inventoryReceivingNoteList) {
        this.inventoryReceivingNoteList = inventoryReceivingNoteList;
    }

    public void addInventoryReceivingNote(InventoryReceivingNote inventoryReceivingNote) {
        this.inventoryReceivingNoteList.add(inventoryReceivingNote);
    }

    public void deleteInventoryReceivingNote(InventoryReceivingNote inventoryReceivingNote) {
        this.inventoryReceivingNoteList.remove(inventoryReceivingNote);
    }

    public Set<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(Set<Staff> staffList) {
        this.staffList = staffList;
    }

    public void addInventoryReceivingStaff(Staff staff) {
        this.staffList.add(staff);
    }

    public void deleteInventoryReceivingStaff(Staff staff) {
        this.staffList.remove(staff);
    }

    @Override
    public String toString() {
        return "InventoryReceivingNote{" +
                "inventoryReceivingNoteId=" + inventoryReceivingNoteId +
                ", inventoryReceivingNoteDate=" + inventoryReceivingNoteDate +
                ", inventoryReceivingNoteStaff=" + inventoryReceivingNoteStaff +
                ", inventoryReceivingNoteList=" + inventoryReceivingNoteList +
                ", staffList=" + staffList +
                '}';
    }
}
