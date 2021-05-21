package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="provider")
public class Provider {
    @Id
    @Column
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
    private String providerEmail;

    @Column
    private String providerContactPerson;

    @JsonManagedReference(value = "order-provider")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProvider")
    private Set<Order> providerOrders;

    public Provider(){};

    public Provider(Long providerID, String providerName, String providerAddress, String providerPhone, String providerFax, String providerEmail, String providerContactPerson) {
        this.providerID = providerID;
        this.providerName = providerName;
        this.providerAddress = providerAddress;
        this.providerPhone = providerPhone;
        this.providerFax = providerFax;
        this.providerEmail = providerEmail;
        this.providerContactPerson = providerContactPerson;
    }

    public Long getProviderID() {
        return providerID;
    }

    public void setProviderID(Long providerID) {
        this.providerID = providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    public String getProviderFax() {
        return providerFax;
    }

    public void setProviderFax(String providerFax) {
        this.providerFax = providerFax;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderContactPerson() {
        return providerContactPerson;
    }

    public void setProviderContactPerson(String providerContactPerson) {
        this.providerContactPerson = providerContactPerson;
    }

    public Set<Order> getProviderOrders() {
        return providerOrders;
    }

    public void setProviderOrders(Set<Order> providerOrders) {
        this.providerOrders = providerOrders;
    }
}
