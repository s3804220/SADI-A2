package com.example.BackendComponent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="sale_details")
public class SaleDetail {
    @Id
    @Column
    private Long saleDetailID;

    @ManyToOne
    @JsonBackReference(value = "sale-product")
    @JoinColumn(name="productID")
    private Product saleProduct;

    @Column
    private int saleQuantity;

    @Column
    private BigDecimal salePrice;

    @Column
    private BigDecimal totalValue;

    @ManyToOne
    @JsonBackReference(value = "sale-detail")
    @JoinColumn(name = "saleID")
    private SaleInvoice saleInvoice;

    public SaleDetail(){}

    public SaleDetail(Long saleDetailID, Product saleProduct, int saleQuantity, SaleInvoice saleInvoice){
        this.saleDetailID = saleDetailID;
        this.saleProduct = saleProduct;
        this.saleQuantity = saleQuantity;
        this.saleInvoice = saleInvoice;
    }

    public Long getSaleDetailID() {
        return saleDetailID;
    }

    public void setSaleDetailID(Long saleDetailID) {
        this.saleDetailID = saleDetailID;
    }

    public Product getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(Product saleProduct) {
        this.saleProduct = saleProduct;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public SaleInvoice getSaleInvoice() {
        return saleInvoice;
    }

    public void setSaleInvoice(SaleInvoice saleInvoice) {
        this.saleInvoice = saleInvoice;
    }

    @PrePersist
    @PreUpdate
    public void setValue(){
        salePrice = BigDecimal.ZERO;
        totalValue = BigDecimal.ZERO;
        if(saleProduct != null){
            salePrice = saleProduct.getPrice();
            totalValue = salePrice.multiply(BigDecimal.valueOf(saleQuantity));
        }
    }
}
