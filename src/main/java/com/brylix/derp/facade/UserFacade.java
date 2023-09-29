package com.brylix.derp.facade;

import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.service.UserServiceImpl;

//simplified and unified interface to a complex subsystem
public class UserFacade {
    private UserServiceImpl userServiceImpl;

    public UserFacade() {
        this.userServiceImpl = new UserServiceImpl();
    }

    public boolean authenticateUser(UserAuthDTO userAuthDTO) throws Exception{
       return userServiceImpl.authenticateUser(userAuthDTO);
    }
}
