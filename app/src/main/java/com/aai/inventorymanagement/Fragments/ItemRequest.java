package com.aai.inventorymanagement.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


public class ItemRequest extends Fragment {


    ListView listView ;
    ListviewcustomAdapter adapter;



    public ItemRequest() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_item_request, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final AlertHelper loading = new AlertHelper();
        loading.createProgressAlert(getActivity() , "Loading ...");
        loading.showAlert();

        final View currentView = view;
        RetrofitService service = RetrofitClient.getClient().create(RetrofitService.class);
        Call<ArrayList<Item>> call = service.getListOfItem();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                Log.i("tag", "Success");
                listView = (ListView)currentView.findViewById(R.id.itemRequested_lv);
                adapter = new ListviewcustomAdapter(getActivity() , Constants.ACTION_ITEMREQUEST, response.body());
                listView.setAdapter(adapter);


                loading.hideAlert();
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
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
