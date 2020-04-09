package com.javad.quizapplang.model.dbFlow;

import com.javad.quizapplang.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by SalmanPC3 on 9/16/2018.
 */

@Table(database = MyDatabase.class, name = "tbl_opts")
public class OPtsDet extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private int id_placement;
    @Column
    private int id_opt_gen;
    @Column
    private String opts_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpts_name() {
        return opts_name;
    }

    public void setOpts_name(String opts_name) {
        this.opts_name = opts_name;
    }

    public int getId_opt_gen() {
        return id_opt_gen;
    }

    public void setId_opt_gen(int id_opt_gen) {
        this.id_opt_gen = id_opt_gen;
    }

    public int getId_placement() {
        return id_placement;
    }

    public void setId_placement(int id_placement) {
        this.id_placement = id_placement;
    }
}
