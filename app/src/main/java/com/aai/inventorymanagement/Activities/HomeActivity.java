package com.aai.inventorymanagement.Activities;

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
import com.aai.inventorymanagement.Fragments.Update;
import com.aai.inventorymanagement.Fragments.Vview;
import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.Helper;

public class HomeActivity extends AppCompatActivity implements Home.Listner {

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


    }

    @Override
    protected void onStart() {
        super.onStart();

        Home fragment = new Home();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu1){
                    //TODO SOMETHING
                    mDrawerLayout.closeDrawer(GravityCompat.START , true);
                    return true;

                }
                return false;
            }
        });
    }

    @Override
    public void listen(int v) {

        Helper helper = new Helper();
        helper.createProgressAlert(HomeActivity.this , "LOADING");
        //Add item fragment
        if(v == 1){
            helper.showAlert();
            AddNewItem addNewItem = new AddNewItem();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, addNewItem);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
         if(v == 2){
            helper.showAlert();
            DeleteItem fragment = new DeleteItem();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment , null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
         if(v == 3){
            helper.showAlert();
            Update fragment = new Update();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
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
