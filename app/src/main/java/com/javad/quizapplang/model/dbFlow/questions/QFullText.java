package com.javad.quizapplang.model.dbFlow.questions;

import com.javad.quizapplang.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by SalmanPC3 on 9/16/2018.
 */

@Table(database = MyDatabase.class, name = "tbl_full_text")
public class QFullText extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String fulltext;
    @Column
    private int questions_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(int questions_id) {
        this.questions_id = questions_id;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }
}
