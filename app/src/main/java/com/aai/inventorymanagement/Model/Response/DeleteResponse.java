package com.aai.inventorymanagement.Model.Response;

/**
 * Created by shivam on 13/7/17.
 */

public class DeleteResponse {
    boolean result;

    public DeleteResponse(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
