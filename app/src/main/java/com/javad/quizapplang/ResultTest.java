package com.javad.quizapplang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.javad.quizapplang.adapter.ResultAdapter;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.OPtsDet;
import com.javad.quizapplang.model.dbFlow.OPtsGen;
import com.javad.quizapplang.model.dbFlow.Placement;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.PrefManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ResultTest extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txt_score;
    String level = "";
    int score = 0;
    String str_level = "";
    PrefManager prefManager;

    @SuppressLint("Assert")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);

        txt_score = findViewById(R.id.txt_score);
        recyclerView = findViewById(R.id.recyclerView);
        List<OPtsGen> oPtsGenList = SQLite.select().from(OPtsGen.class).queryList();
        List<OPtsDet> oPtsDets = SQLite.select().from(OPtsDet.class).queryList();
        List<Placement> placementList = SQLite.select().from(Placement.class).queryList();

        assert getIntent().getExtras().getInt("correct") != 0;
        score = getIntent().getExtras().getInt("correct");


        prefManager=new PrefManager(this);
        prefManager.setPlacementShode();

        txt_score.setText("شما باید از سطح "+ getScore(score) + " شروع کنید");
//        prefManager.setLevel();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ResultAdapter resultAdapter = new ResultAdapter(this,placementList,oPtsGenList, oPtsDets);
        recyclerView.setAdapter(resultAdapter);

    }

    @Override
    public void onBackPressed() {

    }

    public void start(View view) {

        new Request<Data>(this).setLevel(str_level, new CallBack<Data>() {
            @Override
            public void onRequestSuccessful(Data data) {

                startActivity(new Intent(ResultTest.this, MainActivity.class));
                Toasty.success(ResultTest.this, "شما با موفقیت وارد شدید").show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        finish();

                    }
                }, 500);
            }
        });

    }

    private String getScore(int score){

        if (score >= 0 && score <= 17){

            level = "Beginner";
            str_level = "1";

        }else if (score >= 18 && score <= 29){

            level = "pre-intermediate";
            str_level = "2";

        }else if (score >= 30 && score <= 39){

            level = "intermediate";
            str_level = "3";

        }else if (score >= 40 && score <= 50){

            level = "Upper-intermediate";
            str_level = "4";

        }

        return level;
    }
}
