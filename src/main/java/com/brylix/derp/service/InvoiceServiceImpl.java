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

    public void saveInvoice(InvoiceDTO invoice) {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;
        try {
            apiOutput = apiClient.callAPI("invoice", invoice.toString(),"POST");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        if(apiOutput != null && apiOutput.contains("data")){
            System.out.println(apiOutput);
        }
    }

    public List<InvoiceDTO> getAllInvoices() {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;
        try {
            apiOutput = apiClient.callAPI("invoice", "","GET");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        List<InvoiceDTO> invList  = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            String data = jsonObject.get("data").getAsString();
            JsonArray jsonArray = JsonParser.parseString(data).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                invList.add(gson.fromJson(jsonArray.get(i),InvoiceDTO.class));
            }
        }

        return invList;
    }


}
