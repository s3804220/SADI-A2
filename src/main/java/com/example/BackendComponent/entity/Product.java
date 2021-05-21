package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name="categoryID")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProduct")
    private Set<Order> productOrders;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiveProduct")
    private Set<ReceivingNote> productReceivingNotes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryProduct")
    private Set<DeliveryNote> productDeliveryNotes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleProduct")
    private Set<SaleInvoice> productInvoice;

    public Product(Long productID, String productName, String model, String brand, String company, String description, float price, Category category){
        super();
        this.productID = productID;
        this.productName = productName;
        this.model = model;
        this.brand = brand;
        this.company = company;
        this.description = description;
        this.price = price;
        this.category = category;
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

    //public void deleteCategory(){ this.category = null;}

    public Set<Order> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<Order> productOrders) {
        this.productOrders = productOrders;
    }

    public Set<ReceivingNote> getProductReceivingNotes() {
        return productReceivingNotes;
    }

    public void setProductReceivingNotes(Set<ReceivingNote> productReceivingNotes) {
        this.productReceivingNotes = productReceivingNotes;
    }

    public Set<DeliveryNote> getProductDeliveryNotes() {
        return productDeliveryNotes;
    }

    public void setProductDeliveryNotes(Set<DeliveryNote> productDeliveryNotes) {
        this.productDeliveryNotes = productDeliveryNotes;
    }

    public Set<SaleInvoice> getProductInvoice() {
        return productInvoice;
    }

    public void setProductInvoice(Set<SaleInvoice> productInvoice) {
        this.productInvoice = productInvoice;
    }

    //public void addProductOrder(Order order){this.productOrders.add(order);}
    //public void deleteProductOrder(Order order){this.productOrders.remove(order);}

    @Override
    public String toString() {
        String output=
                "productName='" + productName + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' + ", pricePerUnit=" + price;
        if (category != null){
            output += ", category='" + category.getCategoryName() + '\'';
        }
        return output;
    }
}
