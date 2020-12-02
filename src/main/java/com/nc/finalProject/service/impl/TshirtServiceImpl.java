package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.repo.TshirtRepository;
import com.nc.finalProject.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TshirtServiceImpl implements TshirtService {
    @Autowired
    private TshirtRepository tshirtRepository;

    @Override
    public Tshirt create(Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    @Override
    public List<Tshirt> findAll() {
        return tshirtRepository.findAll();
    }

    @Override
    public Optional<Tshirt> findById(Long id) {
        return tshirtRepository.findById(id);
    }
}
