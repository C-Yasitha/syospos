package com.brylix.derp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserAuthDTO {
    public String userName;
    public String password;

    public UserAuthDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-d H:mm:ss") // setting date format
                .create();
        return  gson.toJson(this);
    }
}
