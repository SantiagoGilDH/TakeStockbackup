package com.example.primerejemplodigitalhouse.takestock.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class FragmentMainView extends Fragment implements View.OnClickListener{

    private ItemsController itemsController;
    private RecyclerView recyclerView;
    private EditText editText;
    private ItemRecyclerAdapter itemRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        itemsController = new ItemsController();

        Button buttonNewItem = (Button) view.findViewById(R.id.buttonNewItem);
        editText = (EditText) view.findViewById(R.id.editText);

        buttonNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equals("")){
                    Toast.makeText(view.getContext(), "Write an item name", Toast.LENGTH_SHORT).show();
                } else{
                    String itemName = editText.getText().toString();
                    editText.setText("");
                    Item item = new Item(itemName);
                    itemsController.addItemToDatabase(view.getContext(), item);
                    Toast.makeText(view.getContext(), item.getName() + " has been added.", Toast.LENGTH_SHORT).show();

                    itemRecyclerAdapter.setItems(itemsController.getItems(getContext()));
                    itemRecyclerAdapter.notifyDataSetChanged();

                }
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewItems);
        itemRecyclerAdapter = new ItemRecyclerAdapter(itemsController.getItems(getContext()), getContext(), this, new ItemListener());
        recyclerView.setAdapter(itemRecyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;

        }

    @Override
    public void onClick(View view) {
        itemRecyclerAdapter.setItems(itemsController.getItems(getContext()));
        itemRecyclerAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Se ha hecho Click", Toast.LENGTH_SHORT).show();
    }

    public interface FragmentActivityCommunicator {
        void onItemTouched(Item touchedItem);
    }

    public class ItemListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Integer touchedPosition = recyclerView.getChildAdapterPosition(view);
            Item touchedItem = itemRecyclerAdapter.getItemAtPosition(touchedPosition);

            FragmentActivityCommunicator fragmentActivityCommunicator = (FragmentActivityCommunicator) getActivity();
            fragmentActivityCommunicator.onItemTouched(touchedItem);

        }
    }
}

//TODO  quitar Appbar


