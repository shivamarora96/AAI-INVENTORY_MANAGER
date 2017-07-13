package com.aai.inventorymanagement.Model.Response;

/**
 * Created by shivam on 13/7/17.
 */

public class ItemAllocated_Response {


    int pid, bid;
    String name, description;

    public ItemAllocated_Response(int pid, int bid, String name, String description) {
        this.pid = pid;
        this.bid = bid;
        this.name = name;
        this.description = description;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
