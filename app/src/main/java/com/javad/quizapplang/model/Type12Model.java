package com.javad.quizapplang.model;

/**
 * Created by SalmanPC3 on 10/10/2018.
 */

public class Type12Model {

    String num, card1, card2, str1, str2;
    String content;

    String persianAnswer;

    public Type12Model(String num, String card1, String card2, String str1, String str2, String content, String persianAnswer) {
        this.content = content;
        this.num = num;
        this.card1 = card1;
        this.card2 = card2;
        this.str1 = str1;
        this.str2 = str2;
        this.persianAnswer = persianAnswer;
    }

    public String getName() {
        return num;
    }

    public void setName(String name) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPersianAnswer() {
        return persianAnswer;
    }

    public void setPersianAnswer(String persianAnswer) {
        this.persianAnswer = persianAnswer;
    }
}
