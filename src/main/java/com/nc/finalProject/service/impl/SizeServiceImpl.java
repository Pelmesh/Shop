package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.repo.SizeRepository;
import com.nc.finalProject.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Size create(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public Size findBySize(String size) {
        return sizeRepository.findBySize(size);
    }

    @Override
    public Size findByTshirts(Tshirt tshirt) {
        return sizeRepository.findByTshirts(tshirt);
    }

}
