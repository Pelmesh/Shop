package com.nc.finalProject.repo;

import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

}
