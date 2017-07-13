package com.aai.inventorymanagement.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aai.inventorymanagement.Activities.HomeActivity;
import com.aai.inventorymanagement.Fragments.Home;
import com.aai.inventorymanagement.Model.Item;
import com.aai.inventorymanagement.Model.UpdateRequest;
import com.aai.inventorymanagement.Model.UpdateResponse;
import com.aai.inventorymanagement.Others.Constants;
import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.AlertHelper;
import com.aai.inventorymanagement.Utilities.Network.RetrofitClient;
import com.aai.inventorymanagement.Utilities.Network.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shivam on 28/6/17.
 */

public class ListviewcustomAdapter extends ArrayAdapter<Item> {


    Context context;
    List<Item> mData;

    int currentAction;

    public ListviewcustomAdapter(Context context, int action, ArrayList<Item> data) {
        super(context, 0);
        this.context = context;
        mData = data;
        currentAction = action;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull ViewGroup parent) {
        TextView pidtv, bidTv, quantTv, nameTv;
        Button childButton;


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.childrecyclerview, parent, false);
        pidtv = (TextView) itemView.findViewById(R.id.rvchild_pid);
        bidTv = (TextView) itemView.findViewById(R.id.rvchild_bid);
        quantTv = (TextView) itemView.findViewById(R.id.rvchild_quantity);
        nameTv = (TextView) itemView.findViewById(R.id.rvchild_name);
        childButton = (Button) itemView.findViewById(R.id.rvchild_button);

        final Item i = mData.get(position);
        nameTv.setText("NAME : " + i.getName());
        pidtv.setText("PID : " + i.getId());
        bidTv.setText("BID : B" + i.getId());
        quantTv.setText("QUANT : " + i.getCount());


        if (currentAction == Constants.ACTION_VIEW) {
            childButton.setVisibility(View.GONE);
        }

        else if (currentAction == Constants.ACTION_ALLOCATED){
            childButton.setText("REQUEST RETURN");


            childButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertHelper loading = new AlertHelper();
                    loading.createProgressAlert(context , "WAIT A BIT .. ");
                    loading.showAlert();

                    android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.hideAlert();
                            AlertHelper alertHelper = new AlertHelper();
                            alertHelper.createSuccessAlert(context, "RETURN REQUEST INITIATED", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.hide();

                                    Activity activity = (Activity)context;

                                    activity.startActivity(new Intent(activity , HomeActivity.class));
                                    activity.finish();
                                }
                            });
                            alertHelper.showAlert();
                        }
                    } , 1500);



                }
            });


        }

       /*
        else{
            if(currentAction == Constants.ACTION_DELETE){
                childButton.setText("DELETE");
                childButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertHelper alertHelper = new AlertHelper();
                        alertHelper.createSuccessAlert(context, mData.get(position).getName() + "DELETED !!", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mData.get(position).delete();
                                notifyDataSetChanged();
                                alertHelper.hideAlert();

                                Activity activity = (Activity) context;
                                activity.startActivity(new Intent(activity , HomeActivity.class));
                                activity.finish();

                            }
                        });

                        alertHelper.showAlert();
                    }
                });
            }
*/
        else if (currentAction == Constants.ACTION_UPDATE) {

            final int count = i.getCount();

            View v = LayoutInflater.from(context).inflate(R.layout.updatedialogueview, null);

            ImageButton pos, neg;
            final TextView quantDisp;
            pos = (ImageButton) v.findViewById(R.id.dialog_addButton);
            neg = (ImageButton) v.findViewById(R.id.dialog_subtratButton);
            quantDisp = (TextView) v.findViewById(R.id.dialog_quntity);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("UPDATE")
                    .setIcon(R.drawable.products)
                    .setCancelable(false)
                    .setView(v)
                    .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RetrofitService service = RetrofitClient.getClient().create(RetrofitService.class);
                            Call<UpdateResponse> responseCall = service.getUpdateMessage(new UpdateRequest(i.getId() , i.getCount()));

                            final AlertHelper loading  = new AlertHelper();
                            loading.createProgressAlert(context , "JUST A SECOND");
                            loading.showAlert();
                            responseCall.enqueue(new Callback<UpdateResponse>() {
                                @Override
                                public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                                    loading.hideAlert();

                                    AlertHelper alertHelper = new AlertHelper();
                                    if (response.body().getMessage().equals(Constants.UPDATE_RESPONSE_SUCCESS)){

                                        alertHelper.createSuccessAlert(context, Constants.UPDATE_RESPONSE_SUCCESS, new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.hide();
                                                final Activity activity = (Activity) context;
                                                activity.startActivity(new Intent(activity, HomeActivity.class));
                                                activity.finish();

                                            }
                                        });
                                    }
                                    else{
                                        alertHelper.createErrorAlert(context, response.body().getMessage(), new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.hide();

                                            }
                                        });

                                    }
                                    alertHelper.showAlert();
                                }

                                @Override
                                public void onFailure(Call<UpdateResponse> call, Throwable t) {
                                    loading.hideAlert();
                                    AlertHelper alertHelper = new AlertHelper();
                                    alertHelper.createErrorAlert(context, "NETWORK ERROR", new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.hide();
                                        }
                                    });

                                }
                            });


                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            i.setCount(count);
                        }
                    });
            final AlertDialog alertDialog = builder.create();

            childButton.setText("UPDATE");
            childButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });


            quantDisp.setText(count + "");

            pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.setCount(changeCount(i.getCount(), true));
                    quantDisp.setText(i.getCount() + "");
                }
            });

            neg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.setCount(changeCount(i.getCount(), false));
                    quantDisp.setText(i.getCount() + "");
                }
            });


        }
        return itemView;
    }


    public int changeCount(int c, boolean increment) {
        if (increment && c >= 0)
            c++;

        else if (!increment && c > 0)
            c--;

        return c;
    }
}

