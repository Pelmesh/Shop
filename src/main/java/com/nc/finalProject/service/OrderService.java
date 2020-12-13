package com.nc.finalProject.service;

import com.nc.finalProject.model.Order;
import com.nc.finalProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order create(Order order);

    Page<Order> findByUser(User user, Pageable pageable);

}
