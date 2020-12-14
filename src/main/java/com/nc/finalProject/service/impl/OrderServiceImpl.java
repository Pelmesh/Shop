package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Order;
import com.nc.finalProject.model.User;
import com.nc.finalProject.repo.OrderRepository;
import com.nc.finalProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findByUser(User user, Pageable pageable) {
        return orderRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
