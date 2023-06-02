package com.brylix.derp.dao;

import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.ProductDTO;

import java.util.List;

public interface GrnDAO {
    public void saveGrn(GrnDTO grn);
    public List<GrnDTO> getAllGrns();
    public void moveGrn(GrnDTO grn);
}
