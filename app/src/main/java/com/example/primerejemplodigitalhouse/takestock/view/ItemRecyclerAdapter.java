package com.example.primerejemplodigitalhouse.takestock.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

/**
 * Created by lab on 27-Nov-16.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter{

    private List<Item> items;
    private Context context;

    public ItemRecyclerAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detalle_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
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

    static class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDescripcion;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textViewDescripcion = (TextView) itemView.findViewById(R.id.textView);
        }

        public void loadItem(Item item){

            textViewDescripcion.setText(item.getName());

        }
    }
}
