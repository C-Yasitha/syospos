package com.brylix.derp.service;

import com.brylix.derp.dao.GrnRepository;
import com.brylix.derp.dao.GrnService;
import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.repository.GrnRepositoryImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class GrnServiceImpl implements GrnService {
    GrnRepositoryImpl grnRepositoryImpl;

    public GrnServiceImpl() {
        this.grnRepositoryImpl = new GrnRepositoryImpl();
    }

    public void saveGrn(GrnDTO grn) {
        Date addedDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        grn.setGrnDate(addedDate);
        this.grnRepositoryImpl.saveGrn(grn);
    }

    public List<GrnDTO> getAllGrns() {
        return this.grnRepositoryImpl.getAllGrns();
    }

    public void moveGrn(GrnDTO grn) {
        this.grnRepositoryImpl.moveGrn(grn.getId());
    }
}
