package com.brylix.derp.dao;

import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.facade.InvoiceFacade;

import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO{

    InvoiceFacade invoiceFacade;

    public InvoiceDAOImpl() {
        this.invoiceFacade = new InvoiceFacade();
    }

    @Override
    public void saveInvoice(InvoiceDTO invoice) {
        this.invoiceFacade.saveInvoice(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return this.invoiceFacade.getAllInvoices();
    }
}
