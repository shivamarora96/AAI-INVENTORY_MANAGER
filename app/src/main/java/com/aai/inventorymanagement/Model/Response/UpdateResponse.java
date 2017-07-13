package com.aai.inventorymanagement.Model.Response;

/**
 * Created by shivam on 13/7/17.
 */

public class UpdateResponse {

    String message = " ";
    String invalid = " ";

    public UpdateResponse(String message, String invalid) {
        this.message = message;
        this.invalid = invalid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}
