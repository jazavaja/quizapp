package com.javad.quizapplang.model.dbFlow.questions;

import com.javad.quizapplang.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by SalmanPC3 on 9/16/2018.
 */

@Table(database = MyDatabase.class, name = "questions_of")
public class Questions_Of extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String head;
    @Column
    private String score;
    @Column
    private String type;
    @Column
    private String sections;
    @Column
    private String lesson_id;
    @Column
    private int state;
    @Column
    private String subtitle;
    @Column
    private String titleConvquestion;
    @Column
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitleConvquestion() {
        return titleConvquestion;
    }

    public void setTitleConvquestion(String titleConvquestion) {
        this.titleConvquestion = titleConvquestion;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
