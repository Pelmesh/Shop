package com.nc.finalProject.repo;

import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    List<Size> findByTshirt(Tshirt tshirt);

}
