package com.brylix.derp.repository;

import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private DatabaseQueryExecutor queryExecutor;

    public CategoryRepository() {
        queryExecutor = new DatabaseQueryExecutor();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        String query = "SELECT * FROM categories WHERE id = " + id;
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return null;
    }

    public boolean isCategoryNameUnique(String categoryName) {
        String query = "SELECT COUNT(*) AS count FROM categories WHERE name = ?";
        try {
            ResultSet resultSet = queryExecutor.executeQuery(query, categoryName);
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
        return false;
    }

    public void saveCategory(Category category) {
        String query = "INSERT INTO categories (name) VALUES (?)";
        try {
            queryExecutor.executeUpdate(query, category.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void updateCategory(Category category) {
        String query = "UPDATE categories SET name = ? WHERE id = ?";
        try {
            queryExecutor.executeUpdate(query, category.getName(), category.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    public void deleteCategory(Category category) {
        String query = "DELETE FROM categories WHERE id = ?";
        try {
            queryExecutor.executeUpdate(query, category.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }
}
