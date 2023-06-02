package com.brylix.derp.service;

import com.brylix.derp.dao.InvoiceService;
import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.repository.GrnRepositoryImpl;
import com.brylix.derp.repository.InvoiceRepositoryImpl;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepositoryImpl invoiceRepositoryImpl;
    private GrnRepositoryImpl grnRepositoryImpl;

    public InvoiceServiceImpl() {
        this.invoiceRepositoryImpl = new InvoiceRepositoryImpl();
        this.grnRepositoryImpl = new GrnRepositoryImpl();
    }

    public void saveInvoice(InvoiceDTO invoice) {
        //stock reduce
        this.grnRepositoryImpl.reduceStock(invoice.getProducts());
        //save invoice
        this.invoiceRepositoryImpl.saveInvoice(invoice);
    }

    public List<InvoiceDTO> getAllInvoices() {
        return this.invoiceRepositoryImpl.getAllInvoices();
    }


}
