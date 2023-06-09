package com.brylix.derp.facade;

import com.brylix.derp.dao.InvoiceService;
import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.service.InvoiceServiceImpl;

import java.util.List;

public class InvoiceFacade {

    InvoiceServiceImpl invoiceServiceImpl;

    public InvoiceFacade() {
        invoiceServiceImpl = new InvoiceServiceImpl();
    }

    public void saveInvoice(InvoiceDTO invoice) {
        this.invoiceServiceImpl.saveInvoice(invoice);
    }

    public List<InvoiceDTO> getAllInvoices() {
        return this.invoiceServiceImpl.getAllInvoices();
    }
}
