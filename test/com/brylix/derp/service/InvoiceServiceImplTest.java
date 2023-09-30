package com.brylix.derp.service;

import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.dto.InvoiceItemDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceImplTest {

    @Test
    void saveInvoiceWithNoStock() {
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();

        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO(1,"HP envy laptop new",850F, 10F);
        List<InvoiceItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(invoiceItemDTO);
        InvoiceDTO invoiceDTO = new InvoiceDTO("Pasan",new Date(),8500F,500F,10000F,itemDTOList);


        Exception exception = assertThrows(Exception.class, () -> {
            invoiceService.saveInvoice(invoiceDTO);
        });

        System.out.println(exception.getMessage());

        assertEquals("Stock error", exception.getMessage());
    }

    @Test
    void saveInvoiceWithStock() {
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();

        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO(2,"HP envy laptop new",850F, 10F);
        List<InvoiceItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(invoiceItemDTO);
        InvoiceDTO invoiceDTO = new InvoiceDTO("Pasan",new Date(),8500F,500F,10000F,itemDTOList);

        try {
            boolean isSaved = invoiceService.saveInvoice(invoiceDTO);
            assertEquals(true, isSaved);
        }catch (Exception er){

        }

    }
}