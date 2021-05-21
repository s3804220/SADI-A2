package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    @Column
    private String categoryName;

    @JsonManagedReference(value = "product-category")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Product> products;

    public Category(){}

    public Category(Long categoryID, String categoryName){
        super();
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    //public void addProduct(Product product){products.add(product);}
    //public void removeProduct(Product product){products.remove(product);}
    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
