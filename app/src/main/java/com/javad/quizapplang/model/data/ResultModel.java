package com.javad.quizapplang.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SalmanPC1 on 12/26/2017.
 * 3 general Method that get from server
 * 1. status
 * 2. result
 * 3. data
 */


public class ResultModel<Model> {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("result")
    @Expose
    private int result;

    @SerializedName("data")
    private Model data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Model getData() {
        return data;
    }

    public void setData(Model data) {
        this.data = data;
    }
}
