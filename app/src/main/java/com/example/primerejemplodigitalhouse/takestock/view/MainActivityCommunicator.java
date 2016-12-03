package com.example.primerejemplodigitalhouse.takestock.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.controller.ItemsController;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

public class MainActivityCommunicator extends AppCompatActivity implements FragmentMainView.FragmentActivityCommunicator {

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

    @Override
    public void onItemTouched(Item touchedItem) {

        Bundle bundle = new Bundle();
        bundle.putString(FragmentItemDetail.NAME, touchedItem.getName());
        bundle.putInt(FragmentItemDetail.STOCK, touchedItem.getStock());
        bundle.putInt(FragmentItemDetail.MINIMUM_PURCHASE_QUANTITY, touchedItem.getMinimumPurchaceQuantity());
        bundle.putInt(FragmentItemDetail.CONSUMPTION_RATE, touchedItem.getConsumptionRate());

        FragmentItemDetail fragmentItemDetail = new FragmentItemDetail();

        fragmentItemDetail.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragmentItemDetail);
        fragmentTransaction.commit();

    }

}
