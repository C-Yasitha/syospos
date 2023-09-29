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
    public List<ProductDTO> getAllProducts() throws Exception {
       return productFacade.getAllProducts();
    }

    @Override
    public ProductDTO getProductByCode(String productCode,boolean withStock) throws Exception {
        return productFacade.getProductByCode(productCode,withStock);
    }

    @Override
    public void saveProduct(ProductDTO product) throws Exception {
        productFacade.saveProduct(product);
    }

    @Override
    public void updateProduct(ProductDTO product) throws Exception {
        productFacade.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productCode) throws Exception {
        productFacade.deleteProduct(productCode);
    }
}
