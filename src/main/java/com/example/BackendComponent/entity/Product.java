package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column
    private String productName;

    @Column
    private String model;

    @Column
    private String brand;

    @Column
    private String company;

    @Column
    private String description;

    @Column
    private float price;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> productOrders;

    public Product(String productName, String model, String brand, String company, String description, float price){
        super();
        this.productName = productName;
        this.model = model;
        this.brand = brand;
        this.company = company;
        this.description = description;
        this.price = price;
    }

    public Product(){}

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void deleteCategory(){ this.category = null;}

    public Set<Order> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<Order> productOrders) {
        this.productOrders = productOrders;
    }
    public void addProductOrder(Order order){
        this.productOrders.add(order);
    }
    public void deleteProductOrder(Order order){
       this.productOrders.remove(order);
    }

}
