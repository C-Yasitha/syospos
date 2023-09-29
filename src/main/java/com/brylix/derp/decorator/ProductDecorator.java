package com.brylix.derp.decorator;

import com.brylix.derp.dao.ProductDAO;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.model.Product;

import java.util.List;

public class ProductDecorator implements ProductDAO {
    ProductDAO productDAO;

    public ProductDecorator(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<ProductDTO> getAllProducts() throws Exception {
        return this.productDAO.getAllProducts();
    }

    @Override
    public ProductDTO getProductByCode(String productCode,boolean withStock) throws Exception {
        return this.productDAO.getProductByCode(productCode,withStock);
    }

    @Override
    public void saveProduct(ProductDTO product) throws Exception {
        this.productDAO.saveProduct(product);
    }

    @Override
    public void updateProduct(ProductDTO product) throws Exception {
        this.productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productCode) throws Exception {
        this.productDAO.deleteProduct(productCode);
    }

}
