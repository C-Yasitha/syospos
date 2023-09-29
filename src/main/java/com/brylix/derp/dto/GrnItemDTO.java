package com.brylix.derp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class GrnItemDTO {
    public int id;
    public int grnId;
    public int productId;
    public String productName;
    public Date expDate;
    public Float qty;
    public Float cost;

    public GrnItemDTO(int productId, String productName, Date expDate, Float qty, Float cost) {
        this.productId = productId;
        this.productName = productName;
        this.expDate = expDate;
        this.qty = qty;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrnId() {
        return grnId;
    }

    public void setGrnId(int grnId) {
        this.grnId = grnId;
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

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date exp_date) {
        this.expDate = exp_date;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();
        return  gson.toJson(this);
    }
}
