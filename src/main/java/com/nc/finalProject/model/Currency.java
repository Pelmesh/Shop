package com.nc.finalProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String abbreviation;
    private double rate;
    private LocalDateTime dateBank;
    private LocalDateTime dateSave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getDateBank() {
        return dateBank;
    }

    public void setDateBank(LocalDateTime dateBank) {
        this.dateBank = dateBank;
    }

    public LocalDateTime getDateSave() {
        return dateSave;
    }

    public void setDateSave(LocalDateTime dateSave) {
        this.dateSave = dateSave;
    }
}
