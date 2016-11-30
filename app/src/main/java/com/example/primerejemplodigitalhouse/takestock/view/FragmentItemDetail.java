package com.example.primerejemplodigitalhouse.takestock.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.primerejemplodigitalhouse.takestock.R;

/**
 * Created by digitalhouse on 29/11/16.
 */
public class FragmentItemDetail extends Fragment {

    private TextView textViewItemName;
    private TextView textViewItemStock;
    private View view;

    static final String STOCK = "stock";
    static final String NAME = "name";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        Bundle bundle = getArguments();

        textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
        textViewItemStock = (TextView) view.findViewById(R.id.clickableTextViewStock);

        textViewItemName.setText(bundle.getString(NAME));
        textViewItemStock.setText(((Integer) bundle.getInt(STOCK)).toString());

        return view;
    }

    public void TextViewClicked() {
        ViewSwitcher switcher = (ViewSwitcher) view.findViewById(R.id.my_switcher);
        switcher.showNext(); //or switcher.showPrevious();
        TextView myTV = (TextView) switcher.findViewById(R.id.clickableTextViewStock);
        myTV.setText("value");
    }


}
