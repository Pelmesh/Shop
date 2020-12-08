package com.nc.finalProject.service;

import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;

import java.util.List;

public interface SizeService {

    Size create(Size size);

    List<Size> findByTshirt(Tshirt tshirt);

    Size findByTshirtAndSize(Tshirt tshirt, String size);

}
