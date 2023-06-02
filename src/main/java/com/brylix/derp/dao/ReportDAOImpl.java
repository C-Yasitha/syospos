package com.brylix.derp.dao;

import com.brylix.derp.facade.ReportFacade;

public class ReportDAOImpl implements ReportDAO{

    private ReportFacade reportFacade;

    public ReportDAOImpl() {
        this.reportFacade = new ReportFacade();
    }

    @Override
    public String TotalSale(String date) {
        return this.reportFacade.TotalSale(date);
    }

    @Override
    public String Reshelved(String date) {
        return this.reportFacade.Reshelved(date);
    }

    @Override
    public String LowLevel(String date) {
        return this.reportFacade.LowLevel(date);
    }

    @Override
    public String StockReport(String date) {
        return this.reportFacade.StockReport(date);
    }

    @Override
    public String BillReport(String date) {
        return this.reportFacade.BillReport(date);
    }
}
