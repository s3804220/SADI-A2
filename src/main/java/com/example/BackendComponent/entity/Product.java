package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column
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
    private BigDecimal price;

    @Column(columnDefinition = "int default 0")
    private int numberInStock;

    @ManyToOne
    @JsonBackReference(value = "product-category")
    @JoinColumn(name="categoryID")
    private Category category;

    @JsonManagedReference(value = "order-product")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProduct")
    private Set<OrderDetail> productOrderDetails;

    @JsonManagedReference(value = "receive-product")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiveProduct")
    private Set<ReceivingDetail> productReceivingDetails;

    @JsonManagedReference(value = "delivery-product")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryProduct")
    private Set<DeliveryNote> productDeliveryNotes;

    @JsonManagedReference(value = "sale-product")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleProduct")
    private Set<SaleInvoice> productInvoice;

    public Product(Long productID, String productName, String model, String brand, String company, String description, BigDecimal price, Category category){
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //public void deleteCategory(){ this.category = null;}

    public Set<OrderDetail> getProductOrderDetails() {
        return productOrderDetails;
    }

    public void setProductOrderDetails(Set<OrderDetail> productOrderDetails) {
        this.productOrderDetails = productOrderDetails;
    }

    public Set<ReceivingDetail> getProductReceivingDetails() {
        return productReceivingDetails;
    }

    public void setProductReceivingDetails(Set<ReceivingDetail> productReceivingDetails) {
        this.productReceivingDetails = productReceivingDetails;
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
