package com.example.primerejemplodigitalhouse.takestock.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.controller.ItemsController;
import com.example.primerejemplodigitalhouse.takestock.model.daos.ItemsDAO;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemsController itemsController;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragmentMainView = new FragmentMainView();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragmentMainView);
        fragmentTransaction.commit();

    }

    public void createNewItem(View view){


    }
}
