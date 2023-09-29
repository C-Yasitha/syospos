package com.brylix.derp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

public class GrnDTO {
    public int id;
    public Date grnDate;
    public String supplierName;
    public Float total;
    public boolean isShelf;
    public String shelfStatus;
    public List<GrnItemDTO> grnItems;

    public GrnDTO(String supplierName, Float total, boolean isShelf, List<GrnItemDTO> grnItems) {
        this.supplierName = supplierName;
        this.total = total;
        this.isShelf = isShelf;
        this.grnItems = grnItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public boolean isShelf() {
        return isShelf;
    }

    public void setShelf(boolean shelf) {
        isShelf = shelf;
    }

    public List<GrnItemDTO> getGrnItems() {
        return grnItems;
    }

    public void setGrnItems(List<GrnItemDTO> grnItems) {
        this.grnItems = grnItems;
    }

    public Date getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(Date grnDate) {
        this.grnDate = grnDate;
    }

    public String getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(String shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();
        return  gson.toJson(this);
    }
}
