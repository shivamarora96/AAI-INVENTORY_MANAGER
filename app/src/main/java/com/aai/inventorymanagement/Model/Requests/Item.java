package com.aai.inventorymanagement.Model.Requests;

import android.content.Context;
import android.util.Log;

import com.aai.inventorymanagement.Others.Constants;
import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {


    @SerializedName("name")
    private String name = " ";
    @SerializedName("description")
    private String description = " ";
    @SerializedName("count")
    private int count = 0;
    @SerializedName("id")
    private int id = -1;


    public Item(){}


    public Item( String name, String description, int count, int id) {

        this.name = name;
        this.description = description;
        this.count = count;
        this.id = id;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

