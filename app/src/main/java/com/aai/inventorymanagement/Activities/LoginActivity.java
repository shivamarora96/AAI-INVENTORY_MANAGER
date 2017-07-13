package com.aai.inventorymanagement.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.aai.inventorymanagement.Others.Constants;
import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.AlertHelper;
import com.aai.inventorymanagement.Utilities.SharedPreferenceManager;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText emailTV , passwordTV;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(LoginActivity.this);
        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        emailTV = (TextInputEditText)findViewById(R.id.login_email);
        passwordTV = (TextInputEditText)findViewById(R.id.login_pass);

        loginButton = (Button)findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean loginStatus =  authenticate(emailTV.getText().toString() , passwordTV.getText().toString());

                if (loginStatus){
                    sharedPreferenceManager.setBoolean(Constants.LOGINTOKEN_KEY , Constants.USER_LOGINED);
                }

            }
        });

    }

    boolean authenticate(String email , String pass){

        if(email.equals(Constants.ADMIN_ID) && pass.equals(Constants.ADMIN_PASSWORD) ){
            SharedPreferenceManager preferenceManager = new SharedPreferenceManager(LoginActivity.this);
            preferenceManager.setInt(Constants.USERTYPE_KEY, Constants.USERTYPE_ADMIN);


            final AlertHelper alertHelper = new AlertHelper();
            alertHelper.createSuccessAlert(LoginActivity.this, "WELCOME ADMIN", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                    alertHelper.hideAlert();
                    finish();
                }
            });
            alertHelper.showAlert();
            return  true;
        }

        else if(email.equals(Constants.GENERAL_ID) && pass.equals(Constants.GENERAL_PASSWORD)){
            SharedPreferenceManager preferenceManager = new SharedPreferenceManager(LoginActivity.this);
            preferenceManager.setInt(Constants.USERTYPE_KEY, Constants.USERTYPE_GENERAL);
            final AlertHelper alertHelper = new AlertHelper();
            alertHelper.createSuccessAlert(LoginActivity.this, "WELCOME USER", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                    alertHelper.hideAlert();
                    finish();
                }
            });
            alertHelper.showAlert();

            return  true;
        }

        else {
            final AlertHelper alertHelper = new AlertHelper();
            alertHelper.createErrorAlert(LoginActivity.this, "INVALID DETAIL", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    alertHelper.hideAlert();
                }
            });
            alertHelper.showAlert();
        }
        return  false;
    }


}
