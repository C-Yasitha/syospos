package com.brylix.derp.facade;

import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.service.GrnServiceImpl;

import java.util.List;

public class GrnFacade {
    private GrnServiceImpl grnService;

    public GrnFacade() {
        grnService = new GrnServiceImpl();
    }

    public void saveGrn(GrnDTO grn) throws Exception{
        this.grnService.saveGrn(grn);
    }


    public List<GrnDTO> getAllGrns() throws Exception {
        return this.grnService.getAllGrns();
    }

    public void moveGrn(GrnDTO grn) throws Exception {
        this.grnService.moveGrn(grn);
    }
}
