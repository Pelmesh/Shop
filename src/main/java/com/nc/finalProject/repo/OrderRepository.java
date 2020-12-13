package com.nc.finalProject.repo;

import com.nc.finalProject.model.Order;
import com.nc.finalProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByUser(User user, Pageable pageable);

}
