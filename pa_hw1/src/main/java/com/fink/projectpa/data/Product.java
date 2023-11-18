/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;

/**
 *
 * @author marko
 */
public class Product implements Serializable {
    
    private int productId;
    private String productName;
    private Supplier supplier;
    private String productCategory;
    private int pricePerUnit;

    public Product() {
    }
    
    

    public Product(int productId, String productName, Supplier supplier, String productCategory, int pricePerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.supplier = supplier;
        this.productCategory = productCategory;
        this.pricePerUnit = pricePerUnit;
    }

    public Product(String productName, Supplier supplier, String productCategory, int pricePerUnit) {
        this.productName = productName;
        this.supplier = supplier;
        this.productCategory = productCategory;
        this.pricePerUnit = pricePerUnit;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", supplier=").append(supplier);
        sb.append(", productCategory=").append(productCategory);
        sb.append(", pricePerUnit=").append(pricePerUnit);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
    
}
