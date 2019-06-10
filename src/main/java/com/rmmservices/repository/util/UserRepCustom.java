package com.rmmservices.repository.util;

import com.rmmservices.model.User;

/**
 * Repository Custom interface to manage the user table
 *
 * @author Karla
 * @since 08-06-2019
 */
public interface UserRepCustom {
    Boolean verifyUser(String userName, String password);
    User getUserByLogin(String userName, String password);
}