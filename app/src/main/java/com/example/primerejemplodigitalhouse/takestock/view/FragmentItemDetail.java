package com.example.primerejemplodigitalhouse.takestock.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.primerejemplodigitalhouse.takestock.R;

/**
 * Created by digitalhouse on 29/11/16.
 */
public class FragmentItemDetail extends Fragment {

    private TextView textViewItemName;
    private TextView textViewItemStock;
    private View fragmentView;

    static final String STOCK = "stock";
    static final String NAME = "name";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        Bundle bundle = getArguments();

        textViewItemName = (TextView) fragmentView.findViewById(R.id.textViewItemName);
        textViewItemStock = (TextView) fragmentView.findViewById(R.id.clickableTextViewStock);

        textViewItemName.setText(bundle.getString(NAME));
        textViewItemStock.setText(((Integer) bundle.getInt(STOCK)).toString());

        textViewItemStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewSwitcher switcher = (ViewSwitcher) fragmentView.findViewById(R.id.my_switcher);
                switcher.showNext(); //or switcher.showPrevious();
            }
        });

        return fragmentView;
    }


}
