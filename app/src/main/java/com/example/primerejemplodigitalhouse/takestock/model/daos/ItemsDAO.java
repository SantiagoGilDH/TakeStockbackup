package com.example.primerejemplodigitalhouse.takestock.model.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;
import com.example.primerejemplodigitalhouse.takestock.util.HTTPConnectionManager;
import com.example.primerejemplodigitalhouse.takestock.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 14/10/16.
 */
public class ItemsDAO extends SQLiteOpenHelper{

    private static final String DATABASENAME = "TakeStockDB";
    private static final Integer DATABASEVERSION = 1;

    private static final String TABLEITEMS = "Items";
    private static final String ID = "ID";
    private static final String NAME = "Name"  ;
    private static final String STOCK = "Stock" ;
    private static final String MINIMUMPURCHACEQUANTITY = "MinimumPurchaceQuantity" ;
    private static final String CONSUMPTIONRATE = "ConsumptionRate";
    private static final String IMAGE = "Image" ;

    private List<Item> items = new ArrayList<>();
    private Context context;
    private HTTPConnectionManager httpConnectionManager;

    public ItemsDAO (Context context){
        super(context, DATABASENAME, null, DATABASEVERSION);
        httpConnectionManager = new HTTPConnectionManager();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + TABLEITEMS + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + STOCK + " NUMBER,"
                + IMAGE + " TEXT, "
                + CONSUMPTIONRATE + " NUMBER, "
                + MINIMUMPURCHACEQUANTITY + " NUMBER "
                + ")";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addItemToDatabases(final Item item) {

        addItemToLocalDB(item);
        AddItemToFirebaseTask addItemToFirebaseTask = new AddItemToFirebaseTask(getItemFromLocalDB(item.getName()));
        addItemToFirebaseTask.execute();
    }

    public void addItemToFirebase(Item item){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();
        myRef.child("items").child(item.getName()).setValue(item);
    };

    public void addItemToLocalDB(Item item){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(NAME, item.getName());
        row.put(STOCK, item.getStock());
        row.put(IMAGE, item.getImage());
        row.put(MINIMUMPURCHACEQUANTITY, item.getMinimumPurchaceQuantity());
        row.put(CONSUMPTIONRATE, item.getConsumptionRate());

        database.insert(TABLEITEMS, null, row);

        database.close();
    }

    public Item getItemFromLocalDB(String itemName){

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLEITEMS + " WHERE " + NAME + " = " + '"'
                +  itemName + '"';
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToNext()){

            Item item = new Item();
            item.setID(cursor.getInt(cursor.getColumnIndex(ID)));
            item.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE)));
            item.setMinimumPurchaceQuantity(cursor.getInt(cursor.getColumnIndex(MINIMUMPURCHACEQUANTITY)));
            item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            item.setStock((Integer) cursor.getInt(cursor.getColumnIndex(STOCK)));
            item.setConsumptionRate((Integer) cursor.getInt(cursor.getColumnIndex(CONSUMPTIONRATE)));

            return item;

        }

        return null;
    }

    public void getItemsFromFirebase(final ResultListener<List<Item>> listenerFromController){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference().child("items");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener()   {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Item item = data.getValue(Item.class);
                    items.add(item);
                    listenerFromController.finish(items);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(context, "itemsDAO.getItemsFromFirebase FAILED", Toast.LENGTH_SHORT).show();
            }
        });

        //RetrieveItemsFromFirebaseAsync retrieveItemsFromFirebaseAsync = new RetrieveItemsFromFirebaseAsync(listenerFromController);
        //retrieveItemsFromFirebaseAsync.execute();

    }

    public void getItemsFromLocalDB(final ResultListener<List<Item>> litenerFromController) {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLEITEMS;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {

        Item item = new Item();
        item.setID(cursor.getInt(cursor.getColumnIndex(ID)));
        item.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE)));
        item.setMinimumPurchaceQuantity(cursor.getInt(cursor.getColumnIndex(MINIMUMPURCHACEQUANTITY)));
        item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        item.setStock((Integer) cursor.getInt(cursor.getColumnIndex(STOCK)));
        item.setConsumptionRate((Integer) cursor.getInt(cursor.getColumnIndex(CONSUMPTIONRATE)));

        items.add(item);

        }

        database.close();
        litenerFromController.finish(items);
    }

    public List<Item> getItemsFromLocalDB() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLEITEMS;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {

            Item item = new Item();
            item.setID(cursor.getInt(cursor.getColumnIndex(ID)));
            item.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE)));
            item.setMinimumPurchaceQuantity(cursor.getInt(cursor.getColumnIndex(MINIMUMPURCHACEQUANTITY)));
            item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            item.setStock((Integer) cursor.getInt(cursor.getColumnIndex(STOCK)));
            item.setConsumptionRate((Integer) cursor.getInt(cursor.getColumnIndex(CONSUMPTIONRATE)));

            items.add(item);

        }

        database.close();
        return items;
    }

    public void increaseItemStock(Item item){

        Integer newStock = item.getStock() + 1;

        updateItemStock(item, newStock);

    }

    public void decreaseItemStock(Item item){

        Integer newStock = item.getStock() - 1;

        updateItemStock(item, newStock);

    }

    public void updateItemStock(final Item item, final Integer newStock){


        UpdateItemStockFirebase updateItemStockFirebase = new UpdateItemStockFirebase(item, newStock);

        updateItemStockFirebase.execute();

        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STOCK, newStock);

        database.update(TABLEITEMS,  contentValues, ID + " = " + item.getID(), null);

        database.close();

    }

    private class RetrieveItemsFromFirebaseAsync extends AsyncTask<String, Void, List<Item>>{

        private ResultListener<List<Item>> listenerFromController;

        public RetrieveItemsFromFirebaseAsync(ResultListener<List<Item>> listenerFromController) {
            this.listenerFromController = listenerFromController;
        }

        @Override
        protected List<Item> doInBackground(String... strings) {

            final List<Item> items = new ArrayList<>();

            try{
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = firebaseDatabase.getReference().child("items");

                dbRef.addListenerForSingleValueEvent(new ValueEventListener()   {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            Item item = data.getValue(Item.class);
                            items.add(item);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast.makeText(context, "itemsDAO.getItemsFromFirebase FAILED", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e){
                e.printStackTrace();
            }

            return items;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            listenerFromController.finish(items);
        }
    }

    private class UpdateItemStockFirebase extends AsyncTask<String, Void, Void>{

        private Item item;
        private Integer newStock;

        public UpdateItemStockFirebase(Item item, Integer newStock) {
            this.item = item;
            this.newStock = newStock;
        }

        @Override
        protected Void doInBackground(String... strings) {

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.getReference().child("items").child(item.getName()).child("stock").setValue(newStock);
            return null;
        }
    }

    private class AddItemToFirebaseTask extends AsyncTask <String, Void, Void>{

        private Item item;

        public AddItemToFirebaseTask(Item item) {
            this.item = item;
        }

        @Override
        protected Void doInBackground(String... strings) {
            addItemToFirebase(item);

            return null;
        }
    }

    public void addItemsToLocalDatabase(List<Item> items){
        for (Item item : items){
            addItemToDatabases(item);
        }
    }

 }

// TODO : si no hay internet, buscar datos de base de datos
// TODO : si la base de datos está vacía, buscar datos de firebase
// TODO: actualizar firebase al incrementar o disminuir el stock de un item.

