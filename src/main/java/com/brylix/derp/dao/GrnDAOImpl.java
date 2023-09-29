package com.brylix.derp.dao;

import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.facade.GrnFacade;

import java.util.List;

public class GrnDAOImpl implements GrnDAO{
    GrnFacade grnFacade;

    public GrnDAOImpl() {
        this.grnFacade = new GrnFacade();
    }

    @Override
    public void saveGrn(GrnDTO grn) throws Exception {
        this.grnFacade.saveGrn(grn);
    }

    @Override
    public List<GrnDTO> getAllGrns() throws Exception{
        return this.grnFacade.getAllGrns();
    }

    @Override
    public void moveGrn(GrnDTO grn) throws Exception{
        this.grnFacade.moveGrn(grn);
    }
}
