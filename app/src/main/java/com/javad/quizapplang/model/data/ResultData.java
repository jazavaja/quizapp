package com.javad.quizapplang.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SalmanPC1 on 12/27/2017.
 */

public class ResultData {

    @SerializedName("msg")
    @Expose
    private String message;
    @SerializedName("level")
    @Expose
    private String level;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
