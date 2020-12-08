package com.nc.finalProject.service;

import com.nc.finalProject.model.User;

import java.util.Optional;

public interface UserService {

    User findByUsername(String username);

    Optional<User> findById(Long id);

    User create(User user);

}
