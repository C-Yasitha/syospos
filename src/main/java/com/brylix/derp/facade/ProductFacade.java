package com.brylix.derp.facade;

import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.service.ProductServiceImpl;

import java.util.List;

public class ProductFacade {
    private ProductServiceImpl productServiceImpl;

    public ProductFacade() {
        this.productServiceImpl = new ProductServiceImpl();
    }

    public List<ProductDTO> getAllProducts() throws Exception {
        return this.productServiceImpl.getAllProducts();
    }

    public ProductDTO getProductByCode(String productCode,boolean withStock) throws Exception{
        return this.productServiceImpl.getProductByCode(productCode,withStock);
    }

    public void saveProduct(ProductDTO product) throws Exception{
        this.productServiceImpl.saveProduct(product);
    }

    public void updateProduct(ProductDTO product) throws Exception {
        this.productServiceImpl.updateProduct(product);
    }

    public void deleteProduct(String productCode) throws Exception{
        this.productServiceImpl.deleteProduct(productCode);
    }
}
