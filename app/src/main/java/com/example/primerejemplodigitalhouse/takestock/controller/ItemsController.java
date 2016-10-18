package com.example.primerejemplodigitalhouse.takestock.controller;

import android.content.Context;

import com.example.primerejemplodigitalhouse.takestock.model.daos.ItemsDAO;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

/**
 * Created by digitalhouse on 18/10/16.
 */
public class ItemsController {

    public List<Item> getItems(Context context){
        ItemsDAO itemsDao = new ItemsDAO(context);
        return itemsDao.getItems();
    }

    public void addItemToDatabase (Context context, Item item){
        ItemsDAO itemsDao = new ItemsDAO(context);
        itemsDao.addItemToDatabase(item);
    }
}
