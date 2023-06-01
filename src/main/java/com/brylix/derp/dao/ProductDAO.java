package com.brylix.derp.dao;

import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.model.Product;

import java.util.List;

public interface ProductDAO {
    public List<ProductDTO> getAllProducts();
    public void saveProduct(ProductDTO product);
    public void updateProduct(ProductDTO product);
    public void deleteProduct(String productCode);
}
