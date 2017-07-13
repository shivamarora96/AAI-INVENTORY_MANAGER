package com.aai.inventorymanagement.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aai.inventorymanagement.R;

public class AddNewItem extends Fragment {

    public TextInputEditText productId , batchId , productName ;
    public ImageButton increaseQuantity , decreaseQuantitiy;
    public TextView quantityTV ;
    Listner listner;

    public Button save;
    public int quantity = 0;

    public AddNewItem() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_new_item, container, false);
//        productId = (TextInputEditText)view.findViewById(R.id.additem_serialID);
//        batchId  = (TextInputEditText)view.findViewById(R.id.additem_bactchID);
        productName  = (TextInputEditText)view.findViewById(R.id.additem_name);
        increaseQuantity = (ImageButton)view.findViewById(R.id.additem_addButton);
        decreaseQuantitiy = (ImageButton)view.findViewById(R.id.additem_subtratButton);
        quantityTV = (TextView) view.findViewById(R.id.additem_quntity);
        save = (Button)view.findViewById(R.id.additem_saveButton);


        return view;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            listner = (AddNewItem.Listner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityTV.setText(quantity + "");
            }
        });

        decreaseQuantitiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity>0) {
                    quantity--;
                    quantityTV.setText(quantity + "");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name ;
                name = productName.getText().toString().toUpperCase();
                listner.addNewEntryInDb(name , quantity);
            }
        });

    }

    public interface Listner{
        public boolean addNewEntryInDb(String name , int q);
    };

}
