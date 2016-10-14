package com.example.primerejemplodigitalhouse.takestock.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.primerejemplodigitalhouse.takestock.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by digitalhouse on 14/10/16.
 */
public class ListViewItemAdapter extends BaseAdapter{

    private List<Item> items;
    private Context context;

    public ListViewItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.detalle_item, viewGroup, false);

        TextView textViewItemNombre = (TextView) view.findViewById(R.id.textViewItem);
        textViewItemNombre.setText(getItem(i).getName());

        return view;
    }
}
