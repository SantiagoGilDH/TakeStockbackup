package com.example.primerejemplodigitalhouse.takestock.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.primerejemplodigitalhouse.takestock.R;
import com.example.primerejemplodigitalhouse.takestock.controller.ItemsController;
import com.example.primerejemplodigitalhouse.takestock.model.pojos.Item;

/**
 * Created by digitalhouse on 29/11/16.
 */

public class FragmentItemDetail extends Fragment {

    public static FragmentItemDetail provideFragment(Item item){
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentItemDetail.ID, item.getID());
        bundle.putString(FragmentItemDetail.NAME, item.getName());
        bundle.putInt(FragmentItemDetail.STOCK, item.getStock());
        bundle.putInt(FragmentItemDetail.MINIMUM_PURCHASE_QUANTITY, item.getMinimumPurchaceQuantity());
        bundle.putInt(FragmentItemDetail.CONSUMPTION_RATE, item.getConsumptionRate());

        FragmentItemDetail fragmentItemDetail = new FragmentItemDetail();

        fragmentItemDetail.setArguments(bundle);

        return fragmentItemDetail;
    }

    private TextView textViewItemName;
    private TextView textViewItemStock;
    private TextView textViewMinimumPurchace;
    private TextView textViewConsumptionRate;
    private View fragmentView;
    private Button deleteButton;

    static final String ID = "id";
    static final String STOCK = "stock";
    static final String NAME = "name";
    static final String CONSUMPTION_RATE = "consumptionRate";
    static final String MINIMUM_PURCHASE_QUANTITY = "minimumPurchaceQuantity";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        final Bundle bundle = getArguments();

        textViewItemName = (TextView) fragmentView.findViewById(R.id.textViewItemName);
        textViewItemStock = (TextView) fragmentView.findViewById(R.id.textViewStock);
        textViewConsumptionRate = (TextView) fragmentView.findViewById(R.id.textViewConsumptionRate);
        textViewMinimumPurchace = (TextView) fragmentView.findViewById(R.id.textViewMinimumPurchaceAmmount);
        deleteButton = (Button) fragmentView.findViewById(R.id.buttonDeleteItem);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemsController itemsController = new ItemsController();
                itemsController.deleteItemFromDatabases(getContext(), bundle.getInt(ID));
                Toast.makeText(getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
                FragmentActivityCommunicator fragmentActivityCommunicator = (FragmentActivityCommunicator) getActivity();
                fragmentActivityCommunicator.refreshFragmentMainView();
            }
        });


        textViewItemName.setText(bundle.getString(NAME));
        textViewItemStock.setText(((Integer) bundle.getInt(STOCK)).toString());
        textViewMinimumPurchace.setText(((Integer) bundle.getInt(MINIMUM_PURCHASE_QUANTITY)).toString());
        textViewConsumptionRate.setText(((Integer) bundle.getInt(CONSUMPTION_RATE)).toString());

        return fragmentView;
    }

    public interface FragmentActivityCommunicator{
        void refreshFragmentMainView();
    }


}
