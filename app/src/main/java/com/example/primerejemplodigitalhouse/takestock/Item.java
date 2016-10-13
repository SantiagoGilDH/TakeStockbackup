package com.example.primerejemplodigitalhouse.takestock;

/**
 * Created by digitalhouse on 13/10/16.
 */
public class Item {

    private String name;
    private Integer stock;
    private Integer minimumPurchace;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getMinimumPurchace() {
        return minimumPurchace;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
