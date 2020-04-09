package com.javad.quizapplang;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type9Model {

    private String card1, card2, str1, str2;
    private String content, name;

    public Type9Model(String name, String card1, String card2, String str1, String str2, String content) {
        this.content = content;
        this.name = name;
        this.card1 = card1;
        this.card2 = card2;
        this.str1 = str1;
        this.str2 = str2;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
