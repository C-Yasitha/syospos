package com.brylix.derp.decorator;

import com.brylix.derp.dao.GrnDAO;
import com.brylix.derp.dto.GrnDTO;

import java.util.List;

public class GrnDecorator implements GrnDAO{
    GrnDAO grnDAO;

    public GrnDecorator(GrnDAO grnDAO) {
        this.grnDAO = grnDAO;
    }

    @Override
    public void saveGrn(GrnDTO grn) {
        this.grnDAO.saveGrn(grn);
    }

    @Override
    public List<GrnDTO> getAllGrns() {
        return this.grnDAO.getAllGrns();
    }

    @Override
    public void moveGrn(GrnDTO grn) {
        this.grnDAO.moveGrn(grn);
    }
}
