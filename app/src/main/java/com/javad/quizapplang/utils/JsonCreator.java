package com.javad.quizapplang.utils;

import android.util.Log;

import org.json.JSONObject;


/**
 * Created by SalmanPC1 on 12/27/2017.
 */

public class JsonCreator {

    String[] keys;
    String[] values;

    public JsonCreator() {

    }

    public JsonCreator keys(String... keys) {
        this.keys = keys;
        return this;
    }

    public JsonCreator values(String... values) {
        this.values = values;
        return this;
    }

    public JSONObject create() {
        JSONObject json = new JSONObject();
        try {
            for (int i = 0; i < keys.length; i++)
                json.put(keys[i], values[i]);
        }
        catch (Exception e) {
            Log.e("error", "in create jsonObject");
            e.printStackTrace();
        }
        return json;
    }

}
