package com.brylix.derp.model;

import java.util.Date;

public class Product {
    private String productCode;
    private String productName;
    private String productDescription;
    private String productImage;
    private int lowLevel;
    private boolean isService;
    private double productWeight;
    private Date createdAt;
    private Date updatedAt;
    private Category category;
    private Brand brand;
    private Float price;

    public Product(String productCode, String productName, String productDescription, String productImage,
                   int lowLevel, boolean isService, double productWeight, Date createdAt, Date updatedAt,
                   Category category,Brand brand, Float price) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.lowLevel = lowLevel;
        this.isService = isService;
        this.productWeight = productWeight;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(int lowLevel) {
        this.lowLevel = lowLevel;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
