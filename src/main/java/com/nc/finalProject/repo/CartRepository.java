package com.nc.finalProject.repo;

import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

    Cart findByUserAndTshirt_Id(User user, Long id);

}
