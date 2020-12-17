package com.nc.finalProject.service;

import com.nc.finalProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    User findByUsername(String username);

    Optional<User> findById(Long id);

    User create(User user);

    User findByActivateCode(String code);

    User findByEmail(String email);

    Page<User> findAll(Pageable pageable);

    void delete(User user);

}
