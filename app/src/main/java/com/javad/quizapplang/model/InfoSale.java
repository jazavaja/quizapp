package com.javad.quizapplang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoSale {
    @SerializedName("lesson")
    @Expose
    private String lesson;
    @SerializedName("price_lesson")
    @Expose
    private String priceLesson;
    @SerializedName("with_discount")
    @Expose
    private String withDiscount;
    @SerializedName("price_discount")
    @Expose
    private Integer priceDiscount;
    @SerializedName("count_coin_use_for_discount")
    @Expose
    private Integer countCoinUseForDiscount;
    @SerializedName("price_for_pay")
    @Expose
    private String priceForPay;

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getPriceLesson() {
        return priceLesson;
    }

    public void setPriceLesson(String priceLesson) {
        this.priceLesson = priceLesson;
    }

    public String getWithDiscount() {
        return withDiscount;
    }

    public void setWithDiscount(String withDiscount) {
        this.withDiscount = withDiscount;
    }

    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Integer getCountCoinUseForDiscount() {
        return countCoinUseForDiscount;
    }

    public void setCountCoinUseForDiscount(Integer countCoinUseForDiscount) {
        this.countCoinUseForDiscount = countCoinUseForDiscount;
    }

    public String getPriceForPay() {
        return priceForPay;
    }

    public void setPriceForPay(String priceForPay) {
        this.priceForPay = priceForPay;
    }

}