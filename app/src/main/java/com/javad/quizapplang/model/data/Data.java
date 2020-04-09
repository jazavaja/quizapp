package com.javad.quizapplang.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import com.javad.quizapplang.model.CurrentUser;
import com.javad.quizapplang.model.InfoSale;
import com.javad.quizapplang.model.modelgetlesson.Lesson;
import com.javad.quizapplang.model.Level;
import com.javad.quizapplang.model.Profile;
import com.javad.quizapplang.model.Progress;
import com.javad.quizapplang.model.TopUser;
import com.javad.quizapplang.model.placement.PlacementOnline;
import com.javad.quizapplang.model.questionsOnline.Question;

public class Data extends ResultData {

    @SerializedName("placements")
    @Expose
    private List<PlacementOnline> placements = null;

    public List<PlacementOnline> getPlacements() {
        return placements;
    }

    public void setPlacements(List<PlacementOnline> placements) {
        this.placements = placements;
    }

    // ===========================================

    @SerializedName("lessons")
    @Expose
    private List<Lesson> lessons = null;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    // ===============================================

    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @SerializedName("question_id")
    @Expose
    private String question_id;

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    @SerializedName("progress")
    @Expose
    private Progress progress;

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    //------------------------------------------------------------------

    @SerializedName("levels")
    @Expose
    private List<Level> levels = null;
    @SerializedName("level_user")
    @Expose
    private int levelUser;
    @SerializedName("total_score")
    @Expose
    private int totalScore;

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public int getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(int levelUser) {
        this.levelUser = levelUser;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    //------------------------------------------------------------------

    @SerializedName("profile")
    @Expose
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @SerializedName("topUsers")
    @Expose
    private List<TopUser> topUsers = null;
    @SerializedName("currentUser")
    @Expose
    private CurrentUser currentUser;

    public List<TopUser> getTopUsers() {
        return topUsers;
    }

    public void setTopUsers(List<TopUser> topUsers) {
        this.topUsers = topUsers;
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }


    //===============================================

    @SerializedName("infoSale")
    @Expose
    private InfoSale infoSale;

    public InfoSale getInfoSale() {
        return infoSale;
    }

    public void setInfoSale(InfoSale infoSale) {
        this.infoSale = infoSale;
    }
}