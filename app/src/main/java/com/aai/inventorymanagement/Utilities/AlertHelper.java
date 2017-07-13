package com.aai.inventorymanagement.Utilities;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AlertHelper {

private SweetAlertDialog sweetAlertDialog;

//Create Dialogue Progressbar ...
    public void createProgressAlert(Context context, String title){
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(title.concat("\n\n"));
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#1A237E"));
        sweetAlertDialog.setCancelable(false);
    }

    //Create Success Dialogue Progressbar ...
    public void createSuccessAlert(Context context , String message , SweetAlertDialog.OnSweetClickListener listener){
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#1A237E"));
        sweetAlertDialog.setTitleText("SUCCESS");
        sweetAlertDialog.setContentText("\n".concat(message));
        sweetAlertDialog.setConfirmClickListener(listener);
        sweetAlertDialog.setConfirmText("OK");

    }

    //Create Error Dialogue Progressbar ...

    public void createErrorAlert(Context context, String message , SweetAlertDialog.OnSweetClickListener listener){
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#1A237E"));
        sweetAlertDialog.setTitleText("FAILED ");
        sweetAlertDialog.setContentText("\n".concat(message));
        sweetAlertDialog.setConfirmClickListener(listener);
        sweetAlertDialog.setConfirmText("OK");
    }


//    Show Dialogue

    public void showAlert(){
        if(sweetAlertDialog!=null && !sweetAlertDialog.isShowing()){
            sweetAlertDialog.show();
        }
    }

//    Hide Dialogue

    public void hideAlert(){
        if (sweetAlertDialog!=null && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.hide();
            sweetAlertDialog = null;
        }
    }



}
