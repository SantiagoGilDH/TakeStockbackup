package com.example.primerejemplodigitalhouse.takestock.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

import java.util.List;

/**
 * Created by digitalhouse on 14/10/16.
 */
public class ListViewItemAdapter extends RecyclerView.Adapter{

    private List<Item> items;
    private Context context;

    public ListViewItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{

        public ItemsViewHolder(View itemView) {
            super(itemView);

        }
    }

}
