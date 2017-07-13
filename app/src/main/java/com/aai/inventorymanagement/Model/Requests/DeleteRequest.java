package com.aai.inventorymanagement.Model.Requests;

/**
 * Created by shivam on 13/7/17.
 */

public class DeleteRequest {

    String email;
    int bid;

    public DeleteRequest(String email, int bid) {
        this.email = email;
        this.bid = bid;
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
}
