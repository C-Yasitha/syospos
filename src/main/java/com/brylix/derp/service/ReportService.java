package com.brylix.derp.service;

import com.brylix.derp.client.ApiClient;
import com.brylix.derp.dto.InvoiceItemDTO;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.repository.InvoiceRepositoryImpl;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    ApiClient apiClient;

    public ReportService() {
        this.apiClient = new ApiClient();
    }

    public String TotalSale(String date) throws Exception {
        String apiOutput = null;
        String data = "";
        apiOutput = apiClient.callAPI("report?report=TotalSale&date="+date, "","GET");

        List<ProductDTO> prdList  = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }

            data = jsonObject.get("data").getAsString();
        }

        return data;
    }

    public String Reshelved(String date) {
        return null;
    }


    public String LowLevel(String date) {
        return null;
    }


    public String StockReport(String date) {
        return null;
    }


    public String BillReport(String date) {
        return null;
    }
}
