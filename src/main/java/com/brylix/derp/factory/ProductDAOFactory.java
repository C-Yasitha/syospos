package com.brylix.derp.factory;

import com.brylix.derp.dao.ProductDAO;
import com.brylix.derp.dao.ProductDAOImpl;
import com.brylix.derp.decorator.ProductDecorator;

public class ProductDAOFactory {
    public static ProductDAO getProductDAO(){
        ProductDAO productDAO = new ProductDAOImpl();

        productDAO = new ProductDecorator(productDAO);

        return productDAO;
    }
}
