package com.brylix.derp.facade;

import com.brylix.derp.service.ReportService;

public class ReportFacade {

    private ReportService reportService;

    public ReportFacade() {
        this.reportService = new ReportService();
    }

    public String TotalSale(String date) {
        return this.reportService.TotalSale(date);
    }


    public String Reshelved(String date) {
        return this.reportService.Reshelved(date);
    }


    public String LowLevel(String date) {
        return this.reportService.LowLevel(date);
    }


    public String StockReport(String date) {
        return this.reportService.StockReport(date);
    }


    public String BillReport(String date) {
        return this.reportService.BillReport(date);
    }
}
