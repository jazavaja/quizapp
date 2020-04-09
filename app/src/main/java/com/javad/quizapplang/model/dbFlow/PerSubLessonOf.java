package com.javad.quizapplang.model.dbFlow;

import com.javad.quizapplang.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by SalmanPC3 on 9/16/2018.
 */

@Table(database = MyDatabase.class, name = "per_sub_lesson")
public class PerSubLessonOf extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private int per_sec1;
    @Column
    private int per_sec2;
    @Column
    private int per_sec3;
    @Column
    private int per_sec4;
    @Column
    private int per_sec5;
    @Column
    private int per_sec6;
    @Column
    private int per_sec7;
    @Column
    private int lesson_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPer_sec1() {
        return per_sec1;
    }

    public void setPer_sec1(int per_sec1) {
        this.per_sec1 = per_sec1;
    }

    public int getPer_sec2() {
        return per_sec2;
    }

    public void setPer_sec2(int per_sec2) {
        this.per_sec2 = per_sec2;
    }

    public int getPer_sec3() {
        return per_sec3;
    }

    public void setPer_sec3(int per_sec3) {
        this.per_sec3 = per_sec3;
    }

    public int getPer_sec4() {
        return per_sec4;
    }

    public void setPer_sec4(int per_sec4) {
        this.per_sec4 = per_sec4;
    }

    public int getPer_sec5() {
        return per_sec5;
    }

    public void setPer_sec5(int per_sec5) {
        this.per_sec5 = per_sec5;
    }

    public int getPer_sec6() {
        return per_sec6;
    }

    public void setPer_sec6(int per_sec6) {
        this.per_sec6 = per_sec6;
    }

    public int getPer_sec7() {
        return per_sec7;
    }

    public void setPer_sec7(int per_sec7) {
        this.per_sec7 = per_sec7;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }
}
