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
    public List<ProductDTO> getAllProducts() {
        return this.productDAO.getAllProducts();
    }

    @Override
    public ProductDTO getProductByCode(String productCode) {
        return this.productDAO.getProductByCode(productCode);
    }

    @Override
    public void saveProduct(ProductDTO product) {
        this.productDAO.saveProduct(product);
    }

    @Override
    public void updateProduct(ProductDTO product) {
        this.productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productCode) {
        this.productDAO.deleteProduct(productCode);
    }

}
