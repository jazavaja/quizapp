package com.javad.quizapplang.model;

/**
 * Created by AMIR on 11/4/2018.
 */

public class RankModel {

    String rank;
    String coin;
    String score;
    String username;
    String photo;
    String title;
    String sec;
    String createAt;

    public RankModel(String rank, String coin, String score, String createAt, String username,
                     String photo, String title, String sec) {
        this.rank = rank;
        this.coin = coin;
        this.score = score;
        this.username = username;
        this.photo = photo;
        this.title = title;
        this.sec = sec;
        this.createAt = createAt;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
