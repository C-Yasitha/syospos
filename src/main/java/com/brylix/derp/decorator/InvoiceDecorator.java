package com.brylix.derp.decorator;

import com.brylix.derp.dao.InvoiceDAO;
import com.brylix.derp.dto.InvoiceDTO;

import java.util.List;

public class InvoiceDecorator implements InvoiceDAO {
    InvoiceDAO invoiceDAO;

    public InvoiceDecorator(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    @Override
    public void saveInvoice(InvoiceDTO invoice) {
        this.invoiceDAO.saveInvoice(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return this.invoiceDAO.getAllInvoices();
    }
}
