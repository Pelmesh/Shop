package com.nc.finalProject.repo;

import com.nc.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByActivateCode(String code);

    User findByEmail(String email);

}
