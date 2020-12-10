package com.nc.finalProject.service;

import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.User;

import java.util.List;

public interface CartService {

    Cart create(Cart cart);

    List<Cart> findAllByUser(User user);

    Cart findByUserAndTshirt_id(User user, Long id);

    void deleteAll(List<Cart> cartList);

    void delete(Cart cart);
}
