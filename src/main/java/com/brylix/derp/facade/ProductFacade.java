package com.brylix.derp.facade;

import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.service.ProductServiceImpl;

import java.util.List;

public class ProductFacade {
    private ProductServiceImpl productServiceImpl;

    public ProductFacade() {
        this.productServiceImpl = new ProductServiceImpl();
    }

    public List<ProductDTO> getAllProducts() {
        return this.productServiceImpl.getAllProducts();
    }

    public ProductDTO getProductByCode(String productCode,boolean withStock){
        return this.productServiceImpl.getProductByCode(productCode,withStock);
    }

    public void saveProduct(ProductDTO product) {
        this.productServiceImpl.saveProduct(product);
    }

    public void updateProduct(ProductDTO product) {
        this.productServiceImpl.updateProduct(product);
    }

    public void deleteProduct(String productCode){
        this.productServiceImpl.deleteProduct(productCode);
    }
}
