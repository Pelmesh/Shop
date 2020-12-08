package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Order;
import com.nc.finalProject.repo.OrderRepository;
import com.nc.finalProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }
}
