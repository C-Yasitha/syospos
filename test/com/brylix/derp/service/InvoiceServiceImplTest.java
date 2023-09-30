package com.brylix.derp.service;

import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.dto.InvoiceItemDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceImplTest {

    @Test
    void saveInvoiceWithNoStock() {

        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO(1,"HP envy laptop new",850F, 10F);
        List<InvoiceItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(invoiceItemDTO);
        InvoiceDTO invoiceDTO = new InvoiceDTO("Pasan",new Date(),8500F,500F,10000F,itemDTOList);


        int numThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            futures.add(executorService.submit(() -> {
                try {
                    InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
                    Exception exception = assertThrows(Exception.class, () -> {
                        invoiceService.saveInvoice(invoiceDTO);
                    });
                    System.out.println(exception.getMessage());
                    assertEquals("Stock error", exception.getMessage());
                } finally {
                    latch.countDown();
                }
                return null;
            }));
        }

        try {
            latch.await();  // wait for all tasks to complete
            executorService.shutdown();

            for (Future<Void> future : futures) {
                future.get(); // to make sure no other exceptions are thrown
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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