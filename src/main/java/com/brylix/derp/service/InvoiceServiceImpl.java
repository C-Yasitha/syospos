package com.brylix.derp.service;

import com.brylix.derp.client.ApiClient;
import com.brylix.derp.dao.InvoiceService;
import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.InvoiceDTO;
import com.brylix.derp.repository.GrnRepositoryImpl;
import com.brylix.derp.repository.InvoiceRepositoryImpl;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepositoryImpl invoiceRepositoryImpl;
    private GrnRepositoryImpl grnRepositoryImpl;

    public InvoiceServiceImpl() {
        this.invoiceRepositoryImpl = new InvoiceRepositoryImpl();
        this.grnRepositoryImpl = new GrnRepositoryImpl();
    }

    public void saveInvoice(InvoiceDTO invoice) throws Exception {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("invoice", invoice.toString(),"POST");

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }
        }
    }

    public List<InvoiceDTO> getAllInvoices() throws Exception {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("invoice", "","GET");

        List<InvoiceDTO> invList  = new ArrayList<>();
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
                invList.add(gson.fromJson(jsonArray.get(i),InvoiceDTO.class));
            }
        }

        return invList;
    }


}
