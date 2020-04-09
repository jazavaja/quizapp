package com.javad.quizapplang.model.modelgetlesson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.javad.quizapplang.model.modelgetlesson.Data;


public class ModelGetLesson {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}


