package com.aai.inventorymanagement.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.Helper;

public class HomeActivity extends AppCompatActivity {

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
}
