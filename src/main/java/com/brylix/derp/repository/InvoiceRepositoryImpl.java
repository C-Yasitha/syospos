package com.brylix.derp.repository;

import com.brylix.derp.dao.InvoiceRepository;
import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.GrnItemDTO;
import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.dto.InvoiceItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    private DatabaseQueryExecutor queryExecutor;

    public InvoiceRepositoryImpl() {
        queryExecutor = new DatabaseQueryExecutor();
    }
    public void saveInvoice(InvoiceDTO invoice) {
        //save invoice first
        int returnId = 0;
        String query = "INSERT INTO invoices (customer, inv_date, total, discount,tendered) VALUES (?, ?, ?, ?, ?)";
        try {
            returnId = queryExecutor.executeUpdate(query,invoice.getCustomer(),invoice.getInvDate(),invoice.getTotal(),invoice.getDiscount(),invoice.getTendered());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        //save invoice items
        if(returnId!=0){
            for (InvoiceItemDTO invoiceItems:invoice.getProducts()) {
                String queryItem = "INSERT INTO invoice_items (invoice_id, product_code, product_name, price, qty ) VALUES (?, ?, ?, ?, ?)";
                try {
                    queryExecutor.executeUpdate(queryItem,returnId,invoiceItems.getProductCode(),invoiceItems.getProductName(),invoiceItems.getPrice(),invoiceItems.getQty());
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception according to your application's error handling mechanism
                }
            }
        }
    }

    public List<InvoiceDTO> getAllInvoices() {
        List<InvoiceDTO> invoices = new ArrayList<>();
        String query = "SELECT * FROM invoices";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customer = resultSet.getString("customer");
                Date invDate = resultSet.getDate("inv_date");
                Float total = resultSet.getFloat("total");
                Float discount = resultSet.getFloat("discount");
                Float tendered = resultSet.getFloat("tendered");

                //get invoice items
                List<InvoiceItemDTO> invoice_items = new ArrayList<>();
                String query2 = "SELECT * FROM invoice_items WHERE invoice_id = ?";
                ResultSet resultSet2 = queryExecutor.executeQuery(query2,id);
                while (resultSet2.next()) {
                    InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO(resultSet2.getInt("product_code"),
                            resultSet2.getString("product_name"),
                            resultSet2.getFloat("price"),
                            resultSet2.getFloat("qty"));
                    invoiceItemDTO.setId(resultSet2.getInt("id"));
                    invoiceItemDTO.setInvoiceId(id);
                    invoice_items.add(invoiceItemDTO);
                }

                InvoiceDTO invoiceDTO = new InvoiceDTO(customer,invDate,total,discount,tendered,invoice_items);
                invoiceDTO.setId(id);

                invoices.add(invoiceDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return invoices;
    }

    public List<InvoiceItemDTO> getTotalSale(String date){
        List<InvoiceItemDTO> invoiceItems = new ArrayList<>();
        String query = "SELECT ii.product_code,ii.product_name,SUM(ii.price) as price,SUM(qty) as qty FROM invoice_items ii,invoices i WHERE i.id=ii.invoice_id AND i.inv_date = ? GROUP BY ii.product_code";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query,date);
            while (resultSet.next()) {
                InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO(resultSet.getInt("product_code"),resultSet.getString("product_name"),resultSet.getFloat("price"),resultSet.getFloat("qty"));
                invoiceItems.add(invoiceItemDTO);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return invoiceItems;
    }
}
