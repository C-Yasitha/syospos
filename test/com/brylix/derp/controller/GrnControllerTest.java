package com.brylix.derp.controller;

import com.brylix.derp.dao.GrnDAOImpl;
import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.GrnItemDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrnControllerTest {
    @Test
    public void testCreateGrn() {

        // Create a sample GrnDTO
        List<GrnItemDTO> grnItems = new ArrayList<>();
        GrnDTO grnDTO = new GrnDTO("Supplier A",100.00F,false,grnItems);

        // Create an instance of GrnController and set the mock GrnDAO
        GrnController grnController = new GrnController();

        // Call the CreateGrn method with the sample GrnDTO
        boolean result = grnController.CreateGrn(grnDTO);

        assertEquals(false,result);
    }
}