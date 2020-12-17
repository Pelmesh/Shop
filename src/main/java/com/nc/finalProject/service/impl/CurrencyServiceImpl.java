package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Currency;
import com.nc.finalProject.repo.CurrencyRepository;
import com.nc.finalProject.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency findByAbbreviation(String abbreviation) {
        return currencyRepository.findByAbbreviation(abbreviation);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }
}
