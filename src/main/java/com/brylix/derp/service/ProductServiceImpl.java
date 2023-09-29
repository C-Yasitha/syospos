package com.brylix.derp.service;

import com.brylix.derp.client.ApiClient;
import com.brylix.derp.dao.ProductService;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.model.Product;
import com.brylix.derp.repository.GrnRepositoryImpl;
import com.brylix.derp.repository.ProductRepositoryImpl;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepositoryImpl productRepositoryImpl;
    private GrnRepositoryImpl grnRepositoryImpl;

    public ProductServiceImpl() {
        this.productRepositoryImpl = new ProductRepositoryImpl();
        this.grnRepositoryImpl = new GrnRepositoryImpl();
    }

    public List<ProductDTO> getAllProducts() throws Exception {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("allProducts", "","GET");

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

            String data = jsonObject.get("data").getAsString();
            JsonArray jsonArray = JsonParser.parseString(data).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                prdList.add( gson.fromJson(jsonArray.get(i),ProductDTO.class));
            }
        }

        return prdList;
    }

    public ProductDTO getProductByCode(String productCode,boolean withStock) throws Exception{
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("product?productCode="+productCode+"&withStock="+(withStock?"true":"false"), "","GET");

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

            ProductDTO prd = gson.fromJson(data, ProductDTO.class);
            return prd;
        }else{
            return null;
        }
    }

    public void saveProduct(ProductDTO product) throws Exception {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("product", product.toString(),"POST");

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }
        }
    }

    public void updateProduct(ProductDTO product) throws Exception {
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("product", product.toString(),"PUT");

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }
        }
    }

    public void deleteProduct(String productCode) throws Exception{
        ApiClient apiClient = new ApiClient();
        String apiOutput = null;

        apiOutput = apiClient.callAPI("product?productCode="+productCode, "","DELETE");

        if(apiOutput != null && apiOutput.contains("data")){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

            if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
            }
        }
    }


}
