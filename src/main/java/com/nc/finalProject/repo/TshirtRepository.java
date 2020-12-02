package com.nc.finalProject.repo;

import com.nc.finalProject.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Long> {
}
