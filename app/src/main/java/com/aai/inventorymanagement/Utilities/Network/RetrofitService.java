package com.aai.inventorymanagement.Utilities.Network;

import com.aai.inventorymanagement.Model.Requests.AddRequest;
import com.aai.inventorymanagement.Model.Requests.DeleteRequest;
import com.aai.inventorymanagement.Model.Response.AddResponse;
import com.aai.inventorymanagement.Model.Requests.Item;
import com.aai.inventorymanagement.Model.Response.DeleteResponse;
import com.aai.inventorymanagement.Model.Response.ItemAllocated_Response;
import com.aai.inventorymanagement.Model.Requests.UpdateRequest;
import com.aai.inventorymanagement.Model.Response.UpdateResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
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

    //DELETE ITEM
    @HTTP(method = "DELETE",path = "users/deleteitems",hasBody = true)
    Call<DeleteResponse> deleteItem(@Body DeleteRequest request );

    //itemRequest
    @POST("users/requestitems")
    Call<UpdateResponse> requestItem(@Body UpdateRequest request);
}
