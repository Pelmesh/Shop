package com.nc.finalProject.service;

import com.nc.finalProject.model.Tshirt;

import java.util.List;
import java.util.Optional;

public interface TshirtService {

    Tshirt create(Tshirt tshirt);

    List<Tshirt> findAll();

    Optional<Tshirt> findById(Long id);

}
