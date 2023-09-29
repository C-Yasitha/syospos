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

    public boolean authenticateUser(UserAuthDTO userAuthDTO) throws Exception {
        if(userAuthDTO.getUserName()!=null && userAuthDTO.getPassword()!=null){
            ApiClient apiClient = new ApiClient();
            String apiOutput = null;
            apiOutput = apiClient.callAPI("user", userAuthDTO.toString(),"POST");

            if(apiOutput != null && apiOutput.contains("data")){
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(apiOutput).getAsJsonObject();

                if(jsonObject.get("status").toString().replaceAll("\"","").equals("error")){
                    throw new Exception(jsonObject.get("data").toString().replaceAll("\"",""));
                }

                if(jsonObject.get("data").toString().replaceAll("\"","").equals("true")){
                    return true;
                }else{
                    return false;
                }
            }else{
                //show error
                throw new Exception("Server error");
            }
        }else{
            return false;
        }
    }
}
