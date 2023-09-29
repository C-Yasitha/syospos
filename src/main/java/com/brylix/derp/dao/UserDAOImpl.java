package com.brylix.derp.dao;

import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.dto.UserCreateDTO;
import com.brylix.derp.facade.UserFacade;

public class UserDAOImpl implements UserDAO{
    private UserFacade registrationFacade;

    public UserDAOImpl() {
        registrationFacade = new UserFacade();
    }
    @Override
    public boolean authenticateUser(UserAuthDTO userAuthDTO) throws Exception {
        return this.registrationFacade.authenticateUser(userAuthDTO);
    }

    @Override
    public void createUser(UserCreateDTO user) {

    }
}
