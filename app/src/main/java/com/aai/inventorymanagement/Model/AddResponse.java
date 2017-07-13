package com.aai.inventorymanagement.Model;

/**
 * Created by shivam on 13/7/17.
 */

public class AddResponse {
    String created;

    public AddResponse(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
