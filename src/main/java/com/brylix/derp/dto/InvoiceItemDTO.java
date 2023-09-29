package com.brylix.derp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InvoiceItemDTO {
    public int id;
    public int invoiceId;
    public int productCode;
    public String productName;
    public Float price;
    public Float qty;

    public InvoiceItemDTO(int productCode, String productName, Float price, Float qty) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();
        return  gson.toJson(this);
    }
}
