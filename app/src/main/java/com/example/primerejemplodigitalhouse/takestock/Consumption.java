package com.example.primerejemplodigitalhouse.takestock;

import java.util.Date;

/**
 * Created by digitalhouse on 13/10/16.
 */
public class Consumption {

    private Date date;
    private Item item;
    private Integer quantity;

    public Consumption(Date date, Item item, Integer quantity) {
        this.date = date;
        this.item = item;
        this.quantity = quantity;
    }

    public void reduceStock(){
        item.setStock(item.getStock() - quantity);
    }


}
