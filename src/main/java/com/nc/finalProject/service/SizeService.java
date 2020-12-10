package com.nc.finalProject.service;

import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;

public interface SizeService {

    Size create(Size size);

    Size findBySize(String size);

    Size findByTshirts(Tshirt tshirt);

}
