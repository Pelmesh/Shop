package com.nc.finalProject.repo;

import com.nc.finalProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
