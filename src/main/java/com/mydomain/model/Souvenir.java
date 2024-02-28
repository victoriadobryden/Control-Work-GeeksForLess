package com.mydomain.model;

import java.lang.*;

public class Souvenir {
    private String name;
    private int manufacturersCode;
    private String releaseDate;
    private double price;


    public Souvenir(String name, int manufacturersCode, String releaseDate, double price) {
        this.name = name;
        this.manufacturersCode = manufacturersCode;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManufacturersCode() {
        return manufacturersCode;
    }

    public void setManufacturer(int manufacturersCode) {
        this.manufacturersCode = manufacturersCode;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "com.yourdomain.model.Souvenir{" +
                "name='" + name + '\'' +
                ", manufacturersCode=" + manufacturersCode +
                ", releaseDate=" + releaseDate +
                ", price=" + price + " gryven'" +
                '}';
    }
}
