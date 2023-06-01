package com.brylix.derp.service;

import com.brylix.derp.dao.ProductService;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.repository.ProductRepositoryImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepositoryImpl productRepositoryImpl;

    public ProductServiceImpl() {
        this.productRepositoryImpl = new ProductRepositoryImpl();
    }

    public List<ProductDTO> getAllProducts() {
       return this.productRepositoryImpl.getAllProducts();
    }

    public void saveProduct(ProductDTO product) {
        this.productRepositoryImpl.saveProduct(product);
    }

    public void updateProduct(ProductDTO product) {
        this.productRepositoryImpl.updateProduct(product);
    }

    public void deleteProduct(String productCode){
        this.productRepositoryImpl.deleteProduct(productCode);
    }


}
