package com.javad.quizapplang.model.tayinsath;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullQuestion {

    @SerializedName("head")
    @Expose
    private String head;
    @SerializedName("body")
    @Expose
    private List<String> body;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

}
