package com.nc.finalProject.repo;

import com.nc.finalProject.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByAbbreviation(String abbreviation);

}
