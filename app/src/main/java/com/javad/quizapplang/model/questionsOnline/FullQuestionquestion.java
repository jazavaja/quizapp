package com.javad.quizapplang.model.questionsOnline;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullQuestionquestion {

    @SerializedName("bodyquestion")
    @Expose
    private List<String> bodyquestion = null;
    @SerializedName("titleConvquestion")
    @Expose
    private String titleConvquestion;
    @SerializedName("headquestion")
    @Expose
    private String headquestion;

    public List<String> getBodyquestion() {
        return bodyquestion;
    }

    public void setBodyquestion(List<String> bodyquestion) {
        this.bodyquestion = bodyquestion;
    }

    public String getTitleConvquestion() {
        return titleConvquestion;
    }

    public void setTitleConvquestion(String titleConvquestion) {
        this.titleConvquestion = titleConvquestion;
    }

    public String getHeadquestion() {
        return headquestion;
    }

    public void setHeadquestion(String headquestion) {
        this.headquestion = headquestion;
    }

}
