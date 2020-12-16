package com.nc.finalProject.model;

public class TemplateFilter {
    private String name;
    private double priceStart;
    private double priceEnd;
    private boolean genderMale;
    private boolean genderFemale;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(double priceStart) {
        this.priceStart = priceStart;
    }

    public double getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(double priceEnd) {
        this.priceEnd = priceEnd;
    }

    public boolean isGenderMale() {
        return genderMale;
    }

    public void setGenderMale(boolean genderMale) {
        this.genderMale = genderMale;
    }

    public boolean isGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(boolean genderFemale) {
        this.genderFemale = genderFemale;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
