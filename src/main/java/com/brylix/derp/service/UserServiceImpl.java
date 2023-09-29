package com.brylix.derp.service;

import com.brylix.derp.client.ApiClient;
import com.brylix.derp.dao.UserService;
import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.model.User;
import com.brylix.derp.repository.UserRepositoryImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserServiceImpl implements UserService {
    private UserRepositoryImpl userRepositoryImpl;

    public UserServiceImpl() {
        this.userRepositoryImpl = new UserRepositoryImpl();
    }

    public boolean authenticateUser(UserAuthDTO userAuthDTO) {
        if(userAuthDTO.getUserName()!=null && userAuthDTO.getPassword()!=null){
            ApiClient apiClient = new ApiClient();
            String apiOutput = null;
            try {
                apiOutput = apiClient.callAPI("user", userAuthDTO.toString(),"POST");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }

            if(apiOutput != null && apiOutput.contains("data")){
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

                if(jsonObject.get("status").toString().equals("\"success\"") && jsonObject.get("data").toString().equals("\"true\"")){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
