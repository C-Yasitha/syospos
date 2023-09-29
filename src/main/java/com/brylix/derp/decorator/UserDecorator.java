package com.brylix.derp.decorator;

import com.brylix.derp.dao.UserDAO;
import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.dto.UserCreateDTO;

//adds additional functionality to an existing object dynamically
public class UserDecorator implements UserDAO {
    private UserDAO userDAO;

    public UserDecorator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authenticateUser(UserAuthDTO userAuthDTO) throws Exception {
        //password encription here
       return this.userDAO.authenticateUser(userAuthDTO);
    }

    @Override
    public void createUser(UserCreateDTO user) {

    }
}
