package com.example.primerejemplodigitalhouse.takestock;

public class Item {

    private String name;
    private Integer stock;
    private Integer minimumPurchaceQuantity;
    private Integer image;

    public Item(String name) {
        this.name = name;
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

}
