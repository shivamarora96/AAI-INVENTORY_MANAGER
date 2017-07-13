package com.aai.inventorymanagement.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aai.inventorymanagement.Adapter.ListviewcustomAdapter;
import com.aai.inventorymanagement.Others.Constants;
import com.aai.inventorymanagement.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteItem extends Fragment {

    ListView listView;
    ListviewcustomAdapter customAdapter;

    public DeleteItem() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.deleteitem_lv);
//        customAdapter = new ListviewcustomAdapter(getActivity() , Constants.ACTION_DELETE);
        listView.setAdapter(customAdapter);



    }

}
