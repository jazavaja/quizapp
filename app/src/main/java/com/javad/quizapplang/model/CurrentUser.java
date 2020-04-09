package com.javad.quizapplang.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentUser {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("path_photot")
    @Expose
    private String pathPhotot;
    @SerializedName("total_score")
    @Expose
    private String totalScore;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("key")
    @Expose
    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPathPhotot() {
        return pathPhotot;
    }

    public void setPathPhotot(String pathPhotot) {
        this.pathPhotot = pathPhotot;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}