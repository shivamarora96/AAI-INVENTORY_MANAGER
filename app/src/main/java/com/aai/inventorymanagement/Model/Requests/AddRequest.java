package com.aai.inventorymanagement.Model.Requests;

import com.aai.inventorymanagement.Others.Constants;

/**
 * Created by shivam on 13/7/17.
 */

public class AddRequest {

    String email;
    String product_name;
    int quantity;

    public AddRequest(String product_name, int quantity) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.email = Constants.ADMIN_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
