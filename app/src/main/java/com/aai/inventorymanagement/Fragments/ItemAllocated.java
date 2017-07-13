package com.aai.inventorymanagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aai.inventorymanagement.Adapter.ListviewcustomAdapter;
import com.aai.inventorymanagement.Model.Requests.Item;
import com.aai.inventorymanagement.Model.Response.ItemAllocated_Response;
import com.aai.inventorymanagement.Others.Constants;
import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.AlertHelper;
import com.aai.inventorymanagement.Utilities.Network.RetrofitClient;
import com.aai.inventorymanagement.Utilities.Network.RetrofitService;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAllocated extends Fragment {


    ListView listView;
    ListviewcustomAdapter customAdapter;
    ArrayList<Item> data;

    public ItemAllocated() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_allocated, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AlertHelper loading = new AlertHelper();
        loading.createProgressAlert(getActivity() , "Loading ...");
        loading.showAlert();

        final View currentView = view;
        RetrofitService service = RetrofitClient.getClient().create(RetrofitService.class);
        Call<ArrayList<ItemAllocated_Response>> call = service.getAllocatedItem("sid@gmail.com");
        call.enqueue(new Callback<ArrayList<ItemAllocated_Response>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemAllocated_Response>> call, Response<ArrayList<ItemAllocated_Response>> response) {
                Log.i("tag", "Success");
                listView = (ListView) currentView.findViewById(R.id.itemAllocated_lv);

                ArrayList<Item> data = new ArrayList<Item>();
                for (int i =0 ; i<response.body().size() ; i++){
                    ArrayList<ItemAllocated_Response> responses = response.body();
                    Item item = new Item(responses.get(i).getName() , " " , 1 , responses.get(i).getPid());
                    data.add(item);
                }
                customAdapter = new ListviewcustomAdapter(getActivity(), Constants.ACTION_ALLOCATED, data);
                listView.setAdapter(customAdapter);
                loading.hideAlert();
            }

            @Override
            public void onFailure(Call<ArrayList<ItemAllocated_Response>> call, Throwable t) {
                loading.hideAlert();
                AlertHelper failed = new AlertHelper();
                failed.createErrorAlert(getActivity(), "NEWTWORK ERROR !! ", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.hide();
                        startActivity(new Intent(getActivity() , getActivity().getClass()));
                        getActivity().finish();

                    }
                });

                failed.showAlert();
            }
        });



    }

}
