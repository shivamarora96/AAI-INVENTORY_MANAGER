package com.aai.inventorymanagement.Utilities.Network;

import com.aai.inventorymanagement.Model.AddRequest;
import com.aai.inventorymanagement.Model.AddResponse;
import com.aai.inventorymanagement.Model.Item;
import com.aai.inventorymanagement.Model.ItemAllocated_Response;
import com.aai.inventorymanagement.Model.UpdateRequest;
import com.aai.inventorymanagement.Model.UpdateResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by shivam on 12/7/17.
 */

public interface RetrofitService {

    //View Stocks
    @GET("items/stock_status/")
    Call<ArrayList<Item>> getListOfItem();

//    Update Stock
    @POST("users/updateitems")
    Call<UpdateResponse> getUpdateMessage(@Body UpdateRequest request);

    //ADD
    @POST("users/additems")
    Call<AddResponse> addItem(@Body AddRequest request);

    //view Allocated
    @GET("users/users/items/{email}")
    Call<ArrayList<ItemAllocated_Response> > getAllocatedItem(@Path("email") String emailId);
}
