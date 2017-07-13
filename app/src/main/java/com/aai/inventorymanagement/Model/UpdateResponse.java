package com.aai.inventorymanagement.Model;

/**
 * Created by shivam on 13/7/17.
 */

public class UpdateResponse {

    String message = " ";

    public UpdateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
