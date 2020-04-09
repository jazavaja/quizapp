package com.javad.quizapplang.model;

/**
 * Created by AMIR on 10/12/2018.
 */

public class PuzzleModel {

    String name;
    int image;
    int ids;

    public PuzzleModel(String name, int ids) {
        this.name = name;
        this.image = image;
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
}
