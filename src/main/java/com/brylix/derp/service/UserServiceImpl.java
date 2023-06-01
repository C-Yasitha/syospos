package com.brylix.derp.service;

import com.brylix.derp.dao.UserService;
import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.model.User;
import com.brylix.derp.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
    private UserRepositoryImpl userRepositoryImpl;

    public UserServiceImpl() {
        this.userRepositoryImpl = new UserRepositoryImpl();
    }

    public boolean authenticateUser(UserAuthDTO userAuthDTO) {
        if(userAuthDTO.getUserName()!=null && userAuthDTO.getPassword()!=null){
            User user = new User();
            user.setUserName(userAuthDTO.getUserName());
            user.setPassword(userAuthDTO.getPassword());
            return this.userRepositoryImpl.authenticateUser(user);
        }else{
            return false;
        }
    }
}
