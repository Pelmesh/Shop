package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.repo.SizeRepository;
import com.nc.finalProject.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Size create(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public List<Size> findByTshirt(Tshirt tshirt) {
        return sizeRepository.findByTshirt(tshirt);
    }

    @Override
    public Size findByTshirtAndSize(Tshirt tshirt, String size) {
        return sizeRepository.findByTshirtAndSize(tshirt, size);
    }
}
