package com.brylix.derp.service;

import com.brylix.derp.dto.InvoiceItemDTO;
import com.brylix.derp.repository.InvoiceRepositoryImpl;

import java.util.List;

public class ReportService {

    private InvoiceRepositoryImpl invoiceRepositoryImpl;

    public ReportService() {
        this.invoiceRepositoryImpl = new InvoiceRepositoryImpl();
    }

    public String TotalSale(String date) {
        List<InvoiceItemDTO> invoiceItemDTOS = this.invoiceRepositoryImpl.getTotalSale(date);
        String outPut = " <table>\n" ;
        Float totalQty = 0.00F;
        Float totalPrice = 0.00F;
        for (InvoiceItemDTO invoiceItemDTO : invoiceItemDTOS){
            outPut +=  "        <tr>\n" +
                        "            <th>"+invoiceItemDTO.getProductName()+"</th>\n" +
                        "            <th>"+invoiceItemDTO.getProductCode()+"</th>\n" +
                        "            <th>"+invoiceItemDTO.getQty()+"</th>\n" +
                        "            <th>"+invoiceItemDTO.getPrice()+"</th>\n" +
                        "        </tr>\n" ;
            totalQty += invoiceItemDTO.getQty();
            totalPrice += invoiceItemDTO.getPrice();
        }

        outPut += "    </table>\n"+
                    "    <h3>Total Quantity: "+totalQty+"</h3>\n" +
                    "    <h3>Total Revenue: "+totalPrice+"</h3>\n";

        return outPut;
    }

    public String Reshelved(String date) {
        return null;
    }


    public String LowLevel(String date) {
        return null;
    }


    public String StockReport(String date) {
        return null;
    }


    public String BillReport(String date) {
        return null;
    }
}
