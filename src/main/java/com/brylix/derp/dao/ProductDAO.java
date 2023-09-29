package com.brylix.derp.dao;

import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.model.Product;

import java.util.List;

public interface ProductDAO {
    public List<ProductDTO> getAllProducts() throws Exception;
    public ProductDTO getProductByCode(String productCode,boolean withStock) throws Exception;
    public void saveProduct(ProductDTO product) throws Exception;
    public void updateProduct(ProductDTO product) throws Exception;
    public void deleteProduct(String productCode) throws Exception;
}
