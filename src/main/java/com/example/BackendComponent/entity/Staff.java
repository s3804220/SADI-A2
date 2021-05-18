package com.example.BackendComponent.entity;
import javax.persistence.*;

@Entity
@Table(name="staff")
public class Staff {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffID;

    @Column
    private String staffName;

    @Column
    private String staffAddress;

    @Column
    private String staffPhone;

    @Column
    private String staffEmail;

    public Staff(){};

    public Staff(String staffName, String staffAddress, String phone, String email) {
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
}
