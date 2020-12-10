package com.nc.finalProject.repo;

import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    Size findBySize(String size);

    Size findByTshirts(Tshirt tshirt);

}
