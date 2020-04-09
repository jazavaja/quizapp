package com.javad.quizapplang.model.dbFlow;

import com.javad.quizapplang.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by SalmanPC3 on 9/16/2018.
 */

@Table(database = MyDatabase.class, name = "sub_lesson")
public class SubLessonOf extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private int sec1;
    @Column
    private int sec2;
    @Column
    private int sec3;
    @Column
    private int sec4;
    @Column
    private int sec5;
    @Column
    private int sec6;
    @Column
    private int sec7;
    @Column
    private int lesson_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSec1() {
        return sec1;
    }

    public void setSec1(int sec1) {
        this.sec1 = sec1;
    }

    public int getSec2() {
        return sec2;
    }

    public void setSec2(int sec2) {
        this.sec2 = sec2;
    }

    public int getSec3() {
        return sec3;
    }

    public void setSec3(int sec3) {
        this.sec3 = sec3;
    }

    public int getSec4() {
        return sec4;
    }

    public void setSec4(int sec4) {
        this.sec4 = sec4;
    }

    public int getSec5() {
        return sec5;
    }

    public void setSec5(int sec5) {
        this.sec5 = sec5;
    }

    public int getSec6() {
        return sec6;
    }

    public void setSec6(int sec6) {
        this.sec6 = sec6;
    }

    public int getSec7() {
        return sec7;
    }

    public void setSec7(int sec7) {
        this.sec7 = sec7;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }
}
