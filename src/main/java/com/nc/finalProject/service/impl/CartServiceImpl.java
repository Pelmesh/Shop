package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Cart;
import com.nc.finalProject.repo.CartRepository;
import com.nc.finalProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }
}
