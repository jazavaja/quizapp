package com.javad.quizapplang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Level {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("totalScore")
    @Expose
    private Integer totalScore;
    @SerializedName("permision")
    @Expose
    private Integer permision;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("countLesson")
    @Expose
    private Integer countLesson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getPermision() {
        return permision;
    }

    public void setPermision(Integer permision) {
        this.permision = permision;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCountLesson() {
        return countLesson;
    }

    public void setCountLesson(Integer countLesson) {
        this.countLesson = countLesson;
    }
}