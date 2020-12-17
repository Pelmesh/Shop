package com.nc.finalProject.service;

import com.nc.finalProject.model.Currency;

import java.util.List;

public interface CurrencyService {

    Currency save(Currency currency);

    Currency findByAbbreviation(String abbreviation);

    List<Currency> findAll();
}
