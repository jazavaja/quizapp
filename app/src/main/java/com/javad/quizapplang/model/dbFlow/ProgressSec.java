package com.javad.quizapplang.model.dbFlow;

import com.javad.quizapplang.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by SalmanPC3 on 9/16/2018.
 */

@Table(database = MyDatabase.class, name = "progress_sec")
public class ProgressSec extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private int progress1;
    @Column
    private int progress2;
    @Column
    private int progress3;
    @Column
    private int progress4;
    @Column
    private int progress5;
    @Column
    private int progress6;
    @Column
    private int progress7;
    @Column
    private int lesson_id;
    @Column
    private int sub_lesson_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress1() {
        return progress1;
    }

    public void setProgress1(int progress1) {
        this.progress1 = progress1;
    }

    public int getProgress2() {
        return progress2;
    }

    public void setProgress2(int progress2) {
        this.progress2 = progress2;
    }

    public int getProgress3() {
        return progress3;
    }

    public void setProgress3(int progress3) {
        this.progress3 = progress3;
    }

    public int getProgress4() {
        return progress4;
    }

    public void setProgress4(int progress4) {
        this.progress4 = progress4;
    }

    public int getProgress5() {
        return progress5;
    }

    public void setProgress5(int progress5) {
        this.progress5 = progress5;
    }

    public int getProgress6() {
        return progress6;
    }

    public void setProgress6(int progress6) {
        this.progress6 = progress6;
    }

    public int getProgress7() {
        return progress7;
    }

    public void setProgress7(int progress7) {
        this.progress7 = progress7;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getSub_lesson_id() {
        return sub_lesson_id;
    }

    public void setSub_lesson_id(int sub_lesson_id) {
        this.sub_lesson_id = sub_lesson_id;
    }
}
