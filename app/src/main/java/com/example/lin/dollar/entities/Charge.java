package com.example.lin.dollar.entities;

import java.util.Date;

/**
 * Created by lin on 20/08/2017.
 */

public class Charge {
    private String name;
    private double price;
    private Date date;

    public Charge(String name, double price, Date date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
