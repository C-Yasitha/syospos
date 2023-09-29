package com.brylix.derp.service;

import com.brylix.derp.client.ApiClient;
import com.brylix.derp.dao.GrnService;
import com.brylix.derp.dto.GrnDTO;
import com.google.gson.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrnServiceImpl implements GrnService {
    ApiClient apiClient;

    public GrnServiceImpl() {
        this.apiClient = new ApiClient();
    }

    public void saveGrn(GrnDTO grn) throws Exception {
        Date addedDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        grn.setGrnDate(addedDate);

        String apiOutput = null;
        apiOutput = apiClient.callAPI("grn", grn.toString(),"POST");

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();
            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }
        }
    }

    public List<GrnDTO> getAllGrns() throws Exception {
        String apiOutput = null;
        apiOutput = apiClient.callAPI("grn", "","GET");

        List<GrnDTO> grnList  = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }

            String data = jsonObject.get("data").getAsString();
            JsonArray jsonArray = JsonParser.parseString(data).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                grnList.add(gson.fromJson(jsonArray.get(i),GrnDTO.class));
            }
        }

        return grnList;
    }

    public void moveGrn(GrnDTO grn) throws Exception{
        String apiOutput = null;
        apiOutput = apiClient.callAPI("grn?getId="+grn.getId(), "","PUT");

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }
        }
    }
}
