package com.brylix.derp.dao;

import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceDAO {
    public void saveInvoice(InvoiceDTO invoice);
    public List<InvoiceDTO> getAllInvoices();
}
