package com.aai.inventorymanagement.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.aai.inventorymanagement.Fragments.AddNewItem;
import com.aai.inventorymanagement.Fragments.DeleteItem;
import com.aai.inventorymanagement.Fragments.Home;
import com.aai.inventorymanagement.Fragments.ItemAllocated;
import com.aai.inventorymanagement.Fragments.ItemRequest;
import com.aai.inventorymanagement.Fragments.Update;
import com.aai.inventorymanagement.Fragments.Vview;
import com.aai.inventorymanagement.Model.Requests.AddRequest;
import com.aai.inventorymanagement.Model.Response.AddResponse;
import com.aai.inventorymanagement.Model.Requests.Item;
import com.aai.inventorymanagement.Others.Constants;
import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.AlertHelper;
import com.aai.inventorymanagement.Utilities.Network.RetrofitClient;
import com.aai.inventorymanagement.Utilities.Network.RetrofitService;
import com.aai.inventorymanagement.Utilities.SharedPreferenceManager;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements Home.Listner , AddNewItem.Listner {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    android.support.v7.widget.Toolbar toolbar ;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.homeactivity_drawerlayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigaitonVIew);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.homeactivity_toolbar);
        setSupportActionBar(toolbar);


        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this , mDrawerLayout , toolbar ,R.string.navigation_drawer_open , R.string.navigation_drawer_close ) ;
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        Home fragment = new Home();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId() == R.id.menu_home){
                    startActivity(new Intent(HomeActivity.this , HomeActivity.class));
                    finish();

                }
                else if (item.getItemId() == R.id.menu_quit){
                    finish();
                    return true;
                }
                else if(item.getItemId() == R.id.menu_share){
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at:".concat(" ".concat(Constants.APPLINK)));
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                    return true;
                }
                else if (item.getItemId() == R.id.menu_logout){

                    Handler handler = new Handler();
                    final AlertHelper loading = new AlertHelper();
                    loading.createProgressAlert(HomeActivity.this , "PLEASE WAIT");
                    loading.showAlert();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.hideAlert();
                            SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(HomeActivity.this);
                            sharedPreferenceManager.setBoolean(Constants.LOGINTOKEN_KEY, Constants.USER_NOTLOGINED);
                            startActivity(new Intent(HomeActivity.this , LoginActivity.class));
                            finish();
                        }
                    },1500);
                    return true;

                }

                mDrawerLayout.closeDrawer(GravityCompat.START , true);

                return false;
            }
        });

    }

    @Override
    public void listen(int v) {

        SharedPreferenceManager manager = new SharedPreferenceManager(HomeActivity.this);
        int userType = manager.getInt(Constants.USERTYPE_KEY , Constants.USERTYPE_GENERAL);

        AlertHelper alertHelper = new AlertHelper();
        alertHelper.createErrorAlert(HomeActivity.this, "ACCESS DENIED !! ", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.hide();
            }
        });

        AlertHelper helper = new AlertHelper();
        helper.createProgressAlert(HomeActivity.this , "LOADING");
        //Add item fragment
        if(v == 1){
            helper.showAlert();

            if (userType == Constants.USERTYPE_ADMIN) {
                AddNewItem addNewItem = new AddNewItem();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, addNewItem);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            else {
                alertHelper.showAlert();
            }
        }
         if(v == 2){

            if (userType == Constants.USERTYPE_ADMIN) {
                helper.showAlert();
                DeleteItem fragment = new DeleteItem();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment, null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
             else {
                alertHelper.showAlert();
            }
        }
         if(v == 3){
            helper.showAlert();


             if (userType == Constants.USERTYPE_ADMIN) {
                 Update fragment = new Update();
                 FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                 fragmentTransaction.replace(R.id.container, fragment);
                 fragmentTransaction.addToBackStack(null);
                 fragmentTransaction.commit();
             }
             else {

                 alertHelper.showAlert();
             }
        }
        if(v == 4){
            helper.showAlert();
            Vview fragment = new Vview();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        helper.hideAlert();

        if (v == 5){

            SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(HomeActivity.this);
            int status = sharedPreferenceManager.getInt(Constants.USERTYPE_KEY , Constants.USERTYPE_GENERAL);
            if (status == Constants.USERTYPE_ADMIN){
                Vview fragment = new Vview();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            else {
                ItemAllocated fragment = new ItemAllocated();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }



        }

        if (v == 6){
            ItemRequest fragment = new ItemRequest();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

    }


    @Override
    public boolean addNewEntryInDb(final String name, int q) {
        Item item = new Item();

        final AlertHelper alertHelper = new AlertHelper();
        alertHelper.createProgressAlert(HomeActivity.this , "Please Wait ...");
        alertHelper.showAlert();

        RetrofitService service = RetrofitClient.getClient().create(RetrofitService.class);
        Call<AddResponse> responseCall = service.addItem(new AddRequest(name , q));
        responseCall.enqueue(new Callback<AddResponse>() {
            @Override
            public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {
                alertHelper.hideAlert();

                if (response.body().getCreated().equals(Constants.ADD_RESPONSE_SUCCESS)){
                    final AlertHelper alertHelper = new AlertHelper();
                    alertHelper.createSuccessAlert(HomeActivity.this, name.concat(" added to Inventory"), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            alertHelper.hideAlert();
                            Fragment home = new Home();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container, home );
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                        }
                    });
                    alertHelper.showAlert();
                }
             else {
                    AlertHelper error = new AlertHelper();
                    error.createErrorAlert(HomeActivity.this, response.body().getCreated(), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    });
                    error.showAlert();
                }
            }

            @Override
            public void onFailure(Call<AddResponse> call, Throwable t) {

                alertHelper.hideAlert();

                AlertHelper error = new AlertHelper();
                error.createErrorAlert(HomeActivity.this, "NETWORK ERROR", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
                error.showAlert();


            }
        });

        return true;
    }
}

/*

1 Add New Product
2. Remove Product
3. Update Item
4. View Item Details
5. User Having Items
6. User need items


*/
