package com.example.primerejemplodigitalhouse.takestock.models;

public class Item {

    private Integer ID;
    private String name;
    private Integer stock;
    private Integer minimumPurchaceQuantity;
    private Integer image;


    public Item(String name) {
        this.name = name;
        minimumPurchaceQuantity = 1;
        image = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getMinimumPurchaceQuantity() {
        return minimumPurchaceQuantity;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getImage() {
        return image;
    }
}
