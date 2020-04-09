package com.javad.quizapplang.model.questionsOnline;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("fullQuestionquestion")
    @Expose
    private FullQuestionquestion fullQuestionquestion;
    @SerializedName("optquestion")
    @Expose
    private List<String> optquestion = null;
    @SerializedName("answersquestion")
    @Expose
    private List<String> answersquestion = null;
    @SerializedName("path_photosquestion")
    @Expose
    private List<String> pathPhotosquestion = null;
    @SerializedName("scoresquestion")
    @Expose
    private String scoresquestion;
    @SerializedName("sectionquestion")
    @Expose
    private String sectionquestion;
    @SerializedName("typequestion")
    @Expose
    private String typequestion;
    @SerializedName("subtitlequestion")
    @Expose
    private String subtitlequestion;
    @SerializedName("fullText")
    @Expose
    private List<String> fullText = null;
    @SerializedName("sex")
    @Expose
    private String sex;

    public FullQuestionquestion getFullQuestionquestion() {
        return fullQuestionquestion;
    }

    public void setFullQuestionquestion(FullQuestionquestion fullQuestionquestion) {
        this.fullQuestionquestion = fullQuestionquestion;
    }

    public List<String> getOptquestion() {
        return optquestion;
    }

    public void setOptquestion(List<String> optquestion) {
        this.optquestion = optquestion;
    }

    public List<String> getAnswersquestion() {
        return answersquestion;
    }

    public void setAnswersquestion(List<String> answersquestion) {
        this.answersquestion = answersquestion;
    }

    public List<String> getPathPhotosquestion() {
        return pathPhotosquestion;
    }

    public void setPathPhotosquestion(List<String> pathPhotosquestion) {
        this.pathPhotosquestion = pathPhotosquestion;
    }

    public String getScoresquestion() {
        return scoresquestion;
    }

    public void setScoresquestion(String scoresquestion) {
        this.scoresquestion = scoresquestion;
    }

    public String getSectionquestion() {
        return sectionquestion;
    }

    public void setSectionquestion(String sectionquestion) {
        this.sectionquestion = sectionquestion;
    }

    public String getTypequestion() {
        return typequestion;
    }

    public void setTypequestion(String typequestion) {
        this.typequestion = typequestion;
    }

    public String getSubtitlequestion() {
        return subtitlequestion;
    }

    public void setSubtitlequestion(String subtitlequestion) {
        this.subtitlequestion = subtitlequestion;
    }

    public List<String> getFullText() {
        return fullText;
    }

    public void setFullText(List<String> fullText) {
        this.fullText = fullText;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}