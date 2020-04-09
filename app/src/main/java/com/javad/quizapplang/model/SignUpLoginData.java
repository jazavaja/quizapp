package com.javad.quizapplang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.javad.quizapplang.model.data.ResultData;

/**
 * Created by SalmanPC1 on 12/26/2017.
 */
public class SignUpLoginData extends ResultData {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("stateuser")
    @Expose
    private String stateUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStateUser() {
        return stateUser;
    }

    public void setStateUser(String stateUser) {
        this.stateUser = stateUser;
    }
}
