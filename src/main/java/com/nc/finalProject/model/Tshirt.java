package com.nc.finalProject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "tshirts")
public class Tshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Max(value = 1000, message = "Max 1000")
    @Min(value = 0, message = "Min 0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER)
    private Template template;

    public Tshirt() {
    }

    public Tshirt(Template template, Size size, int count) {
        this.template = template;
        this.size = size;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
