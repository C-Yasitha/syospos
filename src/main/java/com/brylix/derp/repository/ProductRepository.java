package com.brylix.derp.repository;

import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.model.Category;
import com.brylix.derp.model.Brand;
import com.brylix.derp.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductRepository {
    private DatabaseQueryExecutor queryExecutor;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;

    public ProductRepository() {
        queryExecutor = new DatabaseQueryExecutor();
        categoryRepository = new CategoryRepository();
        brandRepository = new BrandRepository();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
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
                int categoryId = resultSet.getInt("category_id");
                int brandId = resultSet.getInt("brand_id");
                Float price = resultSet.getFloat("price");

                Category category = categoryRepository.getCategoryById(categoryId);
                Brand brand = brandRepository.getBrandById(brandId);

                Product product = new Product(productCode, productName, productDescription, productImage, lowLevel, isService,
                        productWeight, createdAt, updatedAt, category, brand, price);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return products;
    }

    public void saveProduct(Product product) {
        String query = "INSERT INTO products (product_code, product_name, product_description, product_image, low_level, is_service, " +
                "product_weight, created_at, updated_at, category_id, brand_id, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            queryExecutor.executeUpdate(query, product.getProductCode(), product.getProductName(), product.getProductDescription(),
                    product.getProductImage(), product.getLowLevel(), product.isService(), product.getProductWeight(),
                    product.getCreatedAt(), product.getUpdatedAt(), product.getCategory().getId(),
                    product.getBrand().getId(), product.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void updateProduct(Product product) {
        String query = "UPDATE products SET product_code = ?, product_name = ?, product_description = ?, product_image = ?, " +
                "low_level = ?, is_service = ?, product_weight = ?, created_at = ?, updated_at = ?, " +
                "category_id = ?, brand_id = ?, price = ? WHERE product_code = ?";
        try {
            queryExecutor.executeUpdate(query, product.getProductCode(), product.getProductName(), product.getProductDescription(),
                    product.getProductImage(), product.getLowLevel(), product.isService(), product.getProductWeight(),
                    product.getCreatedAt(), product.getUpdatedAt(), product.getCategory().getId(),
                    product.getBrand().getId(), product.getPrice(), product.getProductCode());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void deleteProduct(Product product) {
        String query = "DELETE FROM products WHERE product_code = ?";
        try {
            queryExecutor.executeUpdate(query, product.getProductCode());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }
}
