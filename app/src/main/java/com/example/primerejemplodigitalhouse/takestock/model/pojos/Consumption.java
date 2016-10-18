package com.example.primerejemplodigitalhouse.takestock.model.pojos;

import java.util.Date;

public class Consumption {

    private Date dateOfConsumption;
    private Item item;
    private Integer quantity;

    public Consumption(Date date, Item item, Integer quantity) {
        this.dateOfConsumption = new Date();
        this.item = item;
        this.quantity = quantity;
    }

    public void reduceStock(){
        item.setStock(item.getStock() - quantity);
    }


}
