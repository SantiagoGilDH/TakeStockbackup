package com.example.primerejemplodigitalhouse.takestock;

import java.util.Date;

/**
 * Created by digitalhouse on 13/10/16.
 */
public class Purchace {

    private Date dateOfPurchace;
    private Item item;
    private Integer quantity;

    public Purchace(Date dateOfPurchace, Item item, Integer quantity) {
        this.dateOfPurchace = dateOfPurchace;
        this.item = item;
        this.quantity = quantity;
        updateStock();
    }

    public void updateStock(){
        item.setStock(item.getStock() + quantity);
    }
}
