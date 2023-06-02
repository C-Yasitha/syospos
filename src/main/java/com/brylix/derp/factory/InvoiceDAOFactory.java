package com.brylix.derp.factory;

import com.brylix.derp.dao.GrnDAO;
import com.brylix.derp.dao.GrnDAOImpl;
import com.brylix.derp.dao.InvoiceDAO;
import com.brylix.derp.dao.InvoiceDAOImpl;
import com.brylix.derp.decorator.GrnDecorator;
import com.brylix.derp.decorator.InvoiceDecorator;

public class InvoiceDAOFactory {
    public static InvoiceDAO getInvoiceDAO(){
        InvoiceDAO invoiceDAO = new InvoiceDAOImpl();

        invoiceDAO = new InvoiceDecorator(invoiceDAO);

        return invoiceDAO;
    }
}
