package com.aai.inventorymanagement.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aai.inventorymanagement.R;

import java.util.List;


public class Home extends Fragment {
    public LinearLayout mAdditem , mDeleteItem , mUpdateStock , mViewStock, mItemReq, mItemAllocated ;
    Listner mListner ;


    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            mListner = (Listner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdditem = (LinearLayout)view.findViewById(R.id.additem);
        mDeleteItem = (LinearLayout)view.findViewById(R.id.deleteitem);
        mUpdateStock = (LinearLayout)view.findViewById(R.id.updatestock);
        mViewStock = (LinearLayout)view.findViewById(R.id.viewstock);
        mItemAllocated = (LinearLayout)view.findViewById(R.id.itemAllocated);
        mItemReq = (LinearLayout)view.findViewById(R.id.itemRequested);




        mAdditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.listen(1);
            }
        });

        mDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.listen(2);
            }
        });

        mUpdateStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.listen(3);
            }
        });

        mViewStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.listen(4);
            }
        });

        mItemAllocated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.listen(5);
            }
        });

        mItemReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.listen(6);
            }
        });



    }




    public interface Listner{
        public void listen(int id);
    }
}
