package com.example.primerejemplodigitalhouse.takestock.model.pojos;

public class Item {

    private Integer ID;
    private String name;
    private Integer stock;
    private Integer minimumPurchaceQuantity;
    private Integer image;


    public Item(){

    }

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

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinimumPurchaceQuantity(Integer minimumPurchaceQuantity) {
        this.minimumPurchaceQuantity = minimumPurchaceQuantity;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
