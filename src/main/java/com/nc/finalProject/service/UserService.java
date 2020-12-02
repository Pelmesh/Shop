package com.nc.finalProject.service;

import com.nc.finalProject.model.User;

public interface UserService {

    User findByUsername(String username);

    User create(User user);

}
