package com.example.primerejemplodigitalhouse.takestock.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.primerejemplodigitalhouse.takestock.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 14/10/16.
 */
public class ItemsDAO extends SQLiteOpenHelper{

    private static final String DATABASENAME = "TakeStockDB";
    private static final Integer DATABASEVERSION = 1;

    private static final String ITEMSTABLE = "Items";
    private static final String ID = "ID";
    private static final String NAME = "Name"  ;
    private static final String STOCK = "Stock" ;
    private static final String MINIMUMPURCHACEQUANTITY = "MinimumPurchaceQuantity" ;
    private static final String IMAGE = "Image" ;

    private static List<Item> items = new ArrayList<>();
    private static Context context;

    public ItemsDAO (Context context){
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + ITEMSTABLE + "("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + STOCK + " NUMBER,"
                + IMAGE + " TEXT, "
                + MINIMUMPURCHACEQUANTITY + " NUMBER "
                + ")";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addItem(Item item) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(NAME, item.getName());
        row.put(STOCK, 0);
        row.put(IMAGE, item.getImage());
        row.put(MINIMUMPURCHACEQUANTITY, item.getMinimumPurchaceQuantity());

        database.insert(ITEMSTABLE, null, row);
        database.close();
    }
}
