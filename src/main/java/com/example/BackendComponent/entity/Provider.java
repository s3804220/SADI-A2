package com.example.BackendComponent.entity;
import javax.persistence.*;

@Entity
@Table(name="provider")
public class Provider {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerID;

    @Column
    private String providerName;

    @Column
    private String providerAddress;

    @Column
    private String providerPhone;

    @Column
    private String providerFax;

    @Column
    private String providerContactPerson;

    public Provider(){};

    public Provider(String providerName, String providerAddress, String providerPhone, String providerFax, String providerContactPerson) {
        this.providerName = providerName;
        this.providerAddress = providerAddress;
        this.providerPhone = providerPhone;
        this.providerFax = providerFax;
        this.providerContactPerson = providerContactPerson;
    }

    public Long getproviderID() {
        return providerID;
    }

    public void setproviderID(Long providerID) {
        this.providerID = providerID;
    }

    public String getproviderName() {
        return providerName;
    }

    public void setproviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getproviderAddress() {
        return providerAddress;
    }

    public void setproviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getproviderPhone() {
        return providerPhone;
    }

    public void setproviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    public String getproviderFax() {
        return providerFax;
    }

    public void setproviderFax(String providerFax) {
        this.providerFax = providerFax;
    }

    public String getproviderContactPerson() {
        return providerContactPerson;
    }

    public void setproviderContactPerson(String providerContactPerson) {
        this.providerContactPerson = providerContactPerson;
    }
}
