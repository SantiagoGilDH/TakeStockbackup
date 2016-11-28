package com.example.primerejemplodigitalhouse.takestock.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.controller.ItemsController;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

/**
 * Created by lab on 27-Nov-16.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private List<Item> items;
    private Context context;
    private View.OnClickListener listener;

    public ItemRecyclerAdapter(List<Item> items, Context context, View.OnClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_card_view, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, context, listener);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.loadItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public void onClick(View view) {

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewItemName;
        private TextView textViewStock;
        private Button buttonSubtract;
        private Button buttonAdd;
        private Context context;
        private Item item;
        private View.OnClickListener listener;

        public ItemViewHolder(View itemView, Context context, View.OnClickListener listener) {
            super(itemView);
            textViewItemName = (TextView) itemView.findViewById(R.id.textViewItemName);
            textViewStock = (TextView) itemView.findViewById(R.id.textViewStock);
            buttonAdd = (Button) itemView.findViewById(R.id.buttonAdd);
            buttonSubtract = (Button) itemView.findViewById(R.id.buttonSubtract);
            this.context = context;
            this.listener = listener;
        }

        public void loadItem(final Item item){

            this.item = item;
            textViewItemName.setText(item.getName());
            textViewStock.setText(item.getStock().toString());
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addOneToItem(item);
                    listener.onClick(buttonAdd);
                }
            });
            buttonSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    substractOneFromItem(item);
                    listener.onClick(buttonSubtract);
                }
            });

        }

        public  void addOneToItem(Item item){

            ItemsController itemsController = new ItemsController();
            itemsController.addOneToItem(context, item);

        }

        public void substractOneFromItem(Item item){

            ItemsController itemsController = new ItemsController();
            itemsController.substractOneFromItem(context, item);
        }



    }

}
