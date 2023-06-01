package com.brylix.derp.dao;

import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.dto.UserCreateDTO;

public interface UserDAO {
    boolean authenticateUser(UserAuthDTO userAuthDTO);
    void createUser(UserCreateDTO user);
}
