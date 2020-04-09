package com.javad.quizapplang.model.placement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlacementOnline {

    @SerializedName("FullQuestion")
    @Expose
    private FullQuestion fullQuestion;
    @SerializedName("opts")
    @Expose
    private List<List<String>> opts = null;
    @SerializedName("scores")
    @Expose
    private List<Integer> scores = null;
    @SerializedName("answers")
    @Expose
    private List<Integer> answers = null;
    @SerializedName("type")
    @Expose
    private String type;

    public FullQuestion getFullQuestion() {
        return fullQuestion;
    }

    public void setFullQuestion(FullQuestion fullQuestion) {
        this.fullQuestion = fullQuestion;
    }

    public List<List<String>> getOpts() {
        return opts;
    }

    public void setOpts(List<List<String>> opts) {
        this.opts = opts;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
