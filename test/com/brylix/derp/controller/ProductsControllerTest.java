package com.brylix.derp.controller;

import com.brylix.derp.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsControllerTest {

    @Test
    public void testSaveProductWithValidation_ValidProduct() {
        // Arrange
        ProductDTO validProduct = new ProductDTO("P001", "Test Product", "Description", "", 1,
                false, 1.0, null, null, "Category", "Brand", 10.0f);

        ProductsController productsController = new ProductsController();
        // Act
        boolean result = productsController.saveProductWithValidation(validProduct);
        assertEquals(true,result);
    }

    @Test
    public void testSaveProductWithValidation_InvalidProduct() {
        // Arrange
        ProductDTO validProduct = new ProductDTO(null, "Test Product", "Description", "", 1,
                false, 1.0, null, null, "Category", "Brand", 10.0f);

        ProductsController productsController = new ProductsController();
        // Act
        boolean result = productsController.saveProductWithValidation(validProduct);
        assertEquals(false,result);
    }
}