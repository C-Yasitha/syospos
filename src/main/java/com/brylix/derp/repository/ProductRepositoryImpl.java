package com.brylix.derp.repository;

import com.brylix.derp.dao.ProductRepository;
import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private DatabaseQueryExecutor queryExecutor;

    public ProductRepositoryImpl() {
        queryExecutor = new DatabaseQueryExecutor();
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            while (resultSet.next()) {
                String productCode = resultSet.getString("product_code");
                String productName = resultSet.getString("product_name");
                String productDescription = resultSet.getString("product_description");
                String productImage = resultSet.getString("product_image");
                int lowLevel = resultSet.getInt("low_level");
                boolean isService = resultSet.getBoolean("is_service");
                double productWeight = resultSet.getDouble("product_weight");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                Float price = resultSet.getFloat("price");
                String category = resultSet.getString("category");
                String brand = resultSet.getString("brand");

                ProductDTO product = new ProductDTO(productCode, productName, productDescription, productImage, lowLevel, isService,
                        productWeight, createdAt, updatedAt, category, brand, price);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return products;
    }

    public void saveProduct(ProductDTO product) {
        String query = "INSERT INTO products (product_code, product_name, product_description, product_image, low_level, is_service, " +
                "product_weight, category, brand, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            queryExecutor.executeUpdate(query, product.getProductCode(), product.getProductName(), product.getProductDescription(),
                    product.getProductImage(), product.getLowLevel(), product.isService(), product.getProductWeight(),
                    product.getCategory(),
                    product.getBrand(), product.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void updateProduct(ProductDTO product) {
        String query = "UPDATE products SET product_code = ?, product_name = ?, product_description = ?, product_image = ?, " +
                "low_level = ?, is_service = ?, product_weight = ?," +
                "category = ?, brand = ?, price = ? WHERE product_code = ?";
        try {
            queryExecutor.executeUpdate(query, product.getProductCode(), product.getProductName(), product.getProductDescription(),
                    product.getProductImage(), product.getLowLevel(), product.isService(), product.getProductWeight(),
                    product.getCategory(),
                    product.getBrand(), product.getPrice(), product.getProductCode());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void deleteProduct(String productCode) {
        String query = "DELETE FROM products WHERE product_code = ?";
        try {
            queryExecutor.executeUpdate(query, productCode);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }
}
