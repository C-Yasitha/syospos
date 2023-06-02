package com.brylix.derp.service;

import com.brylix.derp.dao.ProductService;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.model.Product;
import com.brylix.derp.repository.GrnRepositoryImpl;
import com.brylix.derp.repository.ProductRepositoryImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepositoryImpl productRepositoryImpl;
    private GrnRepositoryImpl grnRepositoryImpl;

    public ProductServiceImpl() {
        this.productRepositoryImpl = new ProductRepositoryImpl();
        this.grnRepositoryImpl = new GrnRepositoryImpl();
    }

    public List<ProductDTO> getAllProducts() {
       return this.productRepositoryImpl.getAllProducts();
    }

    public ProductDTO getProductByCode(String productCode,boolean withStock){
        Product product = this.productRepositoryImpl.getProductByCode(productCode);
        if(product!=null){
            ProductDTO productDTO =  new ProductDTO(product.getProductCode(), product.getProductName(), product.getProductDescription(), product.getProductImage(), product.getLowLevel(), product.isService(),
                    product.getProductWeight(), product.getCreatedAt(), product.getUpdatedAt(), product.getCategory(), product.getBrand(), product.getPrice());
            productDTO.setId(product.getId());
            if(withStock){
                //get stock
                productDTO.setQty(this.grnRepositoryImpl.getStock(productDTO.getId()));
            }
            return productDTO;
        }else{
            return null;
        }
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
