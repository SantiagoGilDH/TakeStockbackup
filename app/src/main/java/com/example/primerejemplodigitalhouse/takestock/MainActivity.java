package com.example.primerejemplodigitalhouse.takestock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.primerejemplodigitalhouse.takestock.dao.ItemsDAO;
import com.example.primerejemplodigitalhouse.takestock.models.Item;
import com.example.primerejemplodigitalhouse.takestock.models.ListViewItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewItems);
        editText = (EditText) findViewById(R.id.editTextItem);

        ListViewItemAdapter adapter = new ListViewItemAdapter(items, this);

        listView.setAdapter(adapter);


    }

    public void createNewItem(View view){

        String itemName = editText.getText().toString();
        Item item = new Item(itemName);
        items.add(item);
        listView.deferNotifyDataSetChanged();
        ItemsDAO itemsDAO = new ItemsDAO(this);
        itemsDAO.addItem(item);

    }
}
