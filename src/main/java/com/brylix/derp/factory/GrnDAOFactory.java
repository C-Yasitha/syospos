package com.brylix.derp.factory;

import com.brylix.derp.dao.GrnDAO;
import com.brylix.derp.dao.GrnDAOImpl;
import com.brylix.derp.dao.ProductDAO;
import com.brylix.derp.dao.ProductDAOImpl;
import com.brylix.derp.decorator.GrnDecorator;
import com.brylix.derp.decorator.ProductDecorator;

public class GrnDAOFactory {
    public static GrnDAO getGrnDAO(){
        GrnDAO grnDAO = new GrnDAOImpl();

        grnDAO = new GrnDecorator(grnDAO);

        return grnDAO;
    }
}
