package com.example.primerejemplodigitalhouse.takestock.controller;

import android.content.Context;

import com.example.primerejemplodigitalhouse.takestock.model.daos.ItemsDAO;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;
import com.example.primerejemplodigitalhouse.takestock.util.HTTPConnectionManager;
import com.example.primerejemplodigitalhouse.takestock.util.ResultListener;

import java.util.List;

/**
 * Created by digitalhouse on 18/10/16.
 */
public class ItemsController {

    public void getItems(final Context context, final ResultListener<List<Item>> listenerFromView) {
        final ItemsDAO itemsDao = new ItemsDAO(context);

        itemsDao.getItemsFromLocalDB(new ResultListener<List<Item>>() {
            @Override
            public void finish(List<Item> result) {

                if (result.size() > 0) {

                    listenerFromView.finish(result);

                } else {
                    itemsDao.getItemsFromFirebase(new ResultListener<List<Item>>() {
                        @Override
                        public void finish(List<Item> result) {
                            itemsDao.addItemsToLocalDatabase(result);
                            listenerFromView.finish(result);
                        }
                    });
                }
            }
        });
    }

    public void deleteItemFromDatabases(Context context, Integer ID){
        ItemsDAO itemsDAO = new ItemsDAO(context);
        itemsDAO.deleteItemFromDatabases(ID);

    }



    public void addItemToDatabases(Context context, Item item){
        ItemsDAO itemsDao = new ItemsDAO(context);
        itemsDao.addItemToDatabases(item);
    }

    public void increaseItemStock(Context context, Item item){

        ItemsDAO itemsDao = new ItemsDAO(context);
        itemsDao.increaseItemStock(item);

    }

    public void decreaseItemStock(Context context, Item item){

        ItemsDAO itemsDAO = new ItemsDAO(context);
        itemsDAO.decreaseItemStock(item);
    }

    public List<Item> getItemsFromLocalDatabase(Context context){
        ItemsDAO itemsDAO = new ItemsDAO(context);
        return itemsDAO.getItemsFromLocalDB();
    }

    public void addItemToLocalDatabase(Context context, Item item){
        ItemsDAO itemsDAO = new ItemsDAO(context);
        itemsDAO.addItemToLocalDB(item);
    }
}
