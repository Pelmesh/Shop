package com.nc.finalProject.service;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.Tshirt;

import java.util.Optional;

public interface TshirtService {

    Tshirt create(Tshirt tshirt);

    Tshirt findByTemplateAndSize_Size(Template template, String size);

    Optional<Tshirt> findById(Long id);

}
