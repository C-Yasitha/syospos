package com.brylix.derp.repository;

import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.model.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository {
    private DatabaseQueryExecutor queryExecutor;

    public BrandRepository() {
        queryExecutor = new DatabaseQueryExecutor();
    }

    public boolean isBrandNameUnique(String brandName) {
        String query = "SELECT * FROM brands WHERE name = ?";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query, brandName);
            return !resultSet.next();
        } catch (SQLException e) {
            // Handle the exception according to your application's error handling mechanism
            e.printStackTrace();
            return false; // Return a default value or throw a custom exception if necessary
        }
    }

    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();
        String query = "SELECT * FROM brands";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Brand brand = new Brand(id, name);
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return brands;
    }

    public Brand getBrandById(int id) {
        String query = "SELECT * FROM brands WHERE id = " + id;
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Brand(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return null;
    }

    public void saveBrand(Brand brand) {
        String query = "INSERT INTO brands (name) VALUES (?)";
        try {
            queryExecutor.executeUpdate(query, brand.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void updateBrand(Brand brand) {
        String query = "UPDATE brands SET name = ? WHERE id = ?";
        try {
            queryExecutor.executeUpdate(query, brand.getName(), brand.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void deleteBrand(Brand brand) {
        String query = "DELETE FROM brands WHERE id = ?";
        try {
            queryExecutor.executeUpdate(query, brand.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }
}
