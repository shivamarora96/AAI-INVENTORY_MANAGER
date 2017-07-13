package com.aai.inventorymanagement.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.aai.inventorymanagement.Others.Constants;

public class SharedPreferenceManager {

    private SharedPreferences sharedPreferences;
    private Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        this.context = context;
        String preferenceName = Constants.PREF_NAME;
        sharedPreferences = context.getSharedPreferences(preferenceName,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setBoolean(String key , Boolean value){
        editor.putBoolean(key,value);
        editor.apply();

    }

    public Boolean getBoolean(String key , Boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public void setFloat(String key , Float value){
        editor.putFloat(key,value);
        editor.apply();

    }

    public Float getBoolean(String key , Float defaultValue){
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public void setInt(String key , int value){
        editor.putInt(key,value);
        editor.apply();

    }

    public int getInt(String key , int defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void setString(String key , String value){
        editor.putString(key,value);
        editor.apply();

    }

    public String getString(String key , String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    public void deleteKey(String key){
        editor.remove(key);
        editor.commit();
    }

    public void clearValues(){
        editor.clear();
        editor.commit();
    }

}

