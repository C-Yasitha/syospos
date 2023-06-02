package com.brylix.derp.dao;

import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.facade.ProductFacade;
import com.brylix.derp.model.Product;

import java.util.List;

public class ProductDAOImpl implements ProductDAO{

    private ProductFacade productFacade;

    public ProductDAOImpl() {
        this.productFacade = new ProductFacade();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
       return productFacade.getAllProducts();
    }

    @Override
    public ProductDTO getProductByCode(String productCode) {
        return productFacade.getProductByCode(productCode);
    }

    @Override
    public void saveProduct(ProductDTO product) {
        productFacade.saveProduct(product);
    }

    @Override
    public void updateProduct(ProductDTO product) {
        productFacade.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productCode) {
        productFacade.deleteProduct(productCode);
    }
}
