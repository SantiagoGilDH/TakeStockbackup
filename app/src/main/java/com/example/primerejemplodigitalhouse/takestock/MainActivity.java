package com.example.primerejemplodigitalhouse.takestock;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.primerejemplodigitalhouse.takestock.dao.ItemsDAO;
import com.example.primerejemplodigitalhouse.takestock.models.Item;
import com.example.primerejemplodigitalhouse.takestock.models.ListViewItemAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editTextItemName;
    private ItemsDAO itemsDAO;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsDAO = new ItemsDAO(this);
        editTextItemName = (EditText) findViewById(R.id.editTextItem);
        items = itemsDAO.getItems();

        Fragment fragmentMainView = new FragmentMainView();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragmentMainView);
        fragmentTransaction.commit();


    }

    public void createNewItem(View view){

        if(editTextItemName.getText().equals("")){
            Toast.makeText(MainActivity.this, "Write an item name", Toast.LENGTH_SHORT).show();
        } else{

            String itemName = editTextItemName.getText().toString();
            editTextItemName.setText("");
            Item item = new Item(itemName);
            itemsDAO.addItemToDatabase(item);
            items.add(item);
            listView.deferNotifyDataSetChanged();
            Toast.makeText(MainActivity.this, item.getName() + " has been added.", Toast.LENGTH_SHORT).show();

        }
    }
}
