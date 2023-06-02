package com.brylix.derp.repository;

import com.brylix.derp.dao.GrnRepository;
import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.GrnItemDTO;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.model.Grn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrnRepositoryImpl implements GrnRepository {
    private DatabaseQueryExecutor queryExecutor;

    public GrnRepositoryImpl() {
        queryExecutor = new DatabaseQueryExecutor();
    }

    public void saveGrn(GrnDTO grn) {
        //save grn first
        int returnId = 0;
        String query = "INSERT INTO grns (grn_date, supplier_name, total, is_shelf) VALUES (?, ?, ?, ?)";
        try {
            returnId = queryExecutor.executeUpdate(query,grn.getGrnDate(),grn.getSupplierName(),grn.getTotal(),grn.isShelf());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        //save grn items
        if(returnId!=0){
            for (GrnItemDTO grnItems:grn.getGrnItems()) {
                String queryItem = "INSERT INTO grn_items (grn_id, product_id, product_name, exp_date, qty, cost) VALUES (?, ?, ?, ?, ?, ?)";
                try {
                   queryExecutor.executeUpdate(queryItem,returnId,grnItems.getProductId(),grnItems.getProductName(),grnItems.getExpDate(),grnItems.getQty(),grnItems.getCost());
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception according to your application's error handling mechanism
                }
            }
        }
    }


    public List<GrnDTO> getAllGrns() {
        List<GrnDTO> grns = new ArrayList<>();
        String query = "SELECT * FROM grns";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String supplierName = resultSet.getString("supplier_name");
                Float total = resultSet.getFloat("total");
                Boolean isShelf = resultSet.getBoolean("is_shelf");
                Date getnDate = resultSet.getDate("grn_date");

                //get grn items
                List<GrnItemDTO> grn_items = new ArrayList<>();
                String query2 = "SELECT * FROM grn_items WHERE grn_id = ?";
                ResultSet resultSet2 = queryExecutor.executeQuery(query2,id);
                while (resultSet2.next()) {
                    GrnItemDTO grnItemDTO = new GrnItemDTO(resultSet2.getInt("product_id"),resultSet2.getString("product_name"),resultSet2.getDate("exp_date"),resultSet2.getFloat("qty"),resultSet2.getFloat("cost"));
                    grnItemDTO.setGrnId(id);
                    grnItemDTO.setId(resultSet2.getInt("id"));
                    grn_items.add(grnItemDTO);
                }

                GrnDTO grnDTO = new GrnDTO(supplierName,total,isShelf,grn_items);
                grnDTO.setId(id);
                grnDTO.setGrnDate(getnDate);

                grns.add(grnDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return grns;
    }

    public void moveGrn(int id){
        String query = "UPDATE grns SET is_shelf = !is_shelf WHERE id = ?";
        try {
            queryExecutor.executeUpdate(query,id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

}
