package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.User;
import com.nc.finalProject.repo.CartRepository;
import com.nc.finalProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAllByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public Cart findByUserAndTshirt_id(User user, Long id) {
        return cartRepository.findByUserAndTshirt_Id(user, id);
    }

    @Override
    public void deleteAll(List<Cart> cartList) {
        cartRepository.deleteAll(cartList);
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}
