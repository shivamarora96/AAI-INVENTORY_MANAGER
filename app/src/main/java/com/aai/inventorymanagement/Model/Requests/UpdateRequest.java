package com.aai.inventorymanagement.Model.Requests;

import com.aai.inventorymanagement.Others.Constants;

/**
 * Created by shivam on 13/7/17.
 */

public class UpdateRequest {

    private String email;
    private int bid;
    private int quantity;

    public UpdateRequest(int bid, int quantity) {
        this.bid = bid;
        this.email = Constants.ADMIN_ID;
        this.quantity = quantity;
    }

    public UpdateRequest(String email, int bid, int quantity) {
        this.email = email;
        this.bid = bid;
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
