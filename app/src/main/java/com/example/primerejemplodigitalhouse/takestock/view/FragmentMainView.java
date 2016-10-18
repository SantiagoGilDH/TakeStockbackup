package com.example.primerejemplodigitalhouse.takestock.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.controller.ItemsController;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

/**
 * Created by lab on 16-Oct-16.
 */
public class FragmentMainView extends Fragment{

    private List<Item> items;
    private ItemsController itemsController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        itemsController = new ItemsController();

        Button buttonNewItem = (Button) view.findViewById(R.id.buttonNewItem);

        buttonNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextItemName = (EditText) view.findViewById(R.id.editTextItem);
                if(editTextItemName.getText().equals("")){
                    Toast.makeText(view.getContext(), "Write an item name", Toast.LENGTH_SHORT).show();
                } else{
                    String itemName = editTextItemName.getText().toString();
                    editTextItemName.setText("");
                    Item item = new Item(itemName);
                    itemsController.addItemToDatabase(view.getContext(), item);
                    items.add(item);
                    Toast.makeText(view.getContext(), item.getName() + " has been added.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;

        }
    }

