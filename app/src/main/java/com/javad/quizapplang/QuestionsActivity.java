package com.javad.quizapplang;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.dbFlow.ProgressSec;
import com.javad.quizapplang.model.dbFlow.ProgressSec_Table;
import com.javad.quizapplang.model.dbFlow.StepSubLessonOf;
import com.javad.quizapplang.model.dbFlow.SubLessonOf;
import com.javad.quizapplang.model.dbFlow.questions.Questions_Of;
import com.javad.quizapplang.model.dbFlow.questions.Questions_Of_Table;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener{

    public static LinearLayout next, check, previous, footer, goOn;
    public static TextView point,seke,bakhsh,dars;
    FragmentTransaction fragmentTransaction;
    public static int questionNum = 0;
    public static FrameLayout layoutResult, blank,result_question, bgToast
            , sec_layou1, sec_layou2, sec_layou3, sec_layou4, sec_layou5, sec_layou6, sec_layou7;
    public static Button btnStart, btn_set_info;
    public static String question_id = "";
    String section, lessonId;
    public static ProgressBar progress;

    public static CircleImageView circle1,circle2,circle3,circle4,circle5,circle6,circle7;
    public static TextView txt7,txt6,txt5,txt4,txt3,txt2,txt1;

    public static TextView txtToast, txtCorrectAns, title_end, score_end, btn_go_main, txt_progress,nextpart;
    public static ImageView imgIcon, thumbIcon, tik1, tik2, tik3, tik4, tik5, tik6, tik7;

    public static CircleProgressBar progress_end;
    Utils utils;

    @SuppressLint("WrongViewCast")
    public void reference(){
        sec_layou1 = findViewById(R.id.sec1);
        sec_layou2 = findViewById(R.id.sec2);
        sec_layou3 = findViewById(R.id.sec3);
        sec_layou4 = findViewById(R.id.sec4);
        sec_layou5 = findViewById(R.id.sec5);
        sec_layou6 = findViewById(R.id.sec6);
        sec_layou7 = findViewById(R.id.sec7);
        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        circle3 = findViewById(R.id.circle3);
        circle4 = findViewById(R.id.circle4);
        circle5 = findViewById(R.id.circle5);
        circle6 = findViewById(R.id.circle6);
        circle7 = findViewById(R.id.circle7);
        tik1 = findViewById(R.id.tik1);
        tik2 = findViewById(R.id.tik2);
        tik3 = findViewById(R.id.tik3);
        tik4 = findViewById(R.id.tik4);
        tik5 = findViewById(R.id.tik5);
        tik6 = findViewById(R.id.tik6);
        tik7 = findViewById(R.id.tik7);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        txt7 = findViewById(R.id.txt7);
        title_end = findViewById(R.id.title_end);
        score_end = findViewById(R.id.score_end);
        progress_end = findViewById(R.id.progress_end);
        txt_progress = findViewById(R.id.txt_progress);
        nextpart = findViewById(R.id.nextpart);
        btn_go_main = findViewById(R.id.btn_go_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        point=findViewById(R.id.point);
        seke=findViewById(R.id.seke);
        bakhsh=findViewById(R.id.bakhsh);
        dars=findViewById(R.id.dars);
        bgToast = findViewById(R.id.custom_toast_root);
        txtToast = findViewById(R.id.toast_text);
        txtCorrectAns = findViewById(R.id.answer_correct);
        layoutResult = findViewById(R.id.layout_result);
        result_question = findViewById(R.id.result_question);
        blank = findViewById(R.id.blank);
        goOn = findViewById(R.id.go_next);
        next = findViewById(R.id.next);
        check = findViewById(R.id.check);
        previous = findViewById(R.id.previous);
        footer = findViewById(R.id.footer);
        progress = findViewById(R.id.progress);
        reference();

        utils = new Utils(this);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        section = bundle.getString("section");
        lessonId = bundle.getString("lesson_id");

        btnStart = findViewById(R.id.btnStart);
        btn_set_info = findViewById(R.id.btn_set_info);

        ProgressSec progressSecList = SQLite.select().from(ProgressSec.class)
                .where(ProgressSec_Table.lesson_id.eq(Integer.valueOf(lessonId)))
                .querySingle();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new MainFragment());
        fragmentTransaction.commit();

        next.setOnClickListener(this);
        check.setOnClickListener(this);
        previous.setOnClickListener(this);

        List<StepSubLessonOf> stepSubLessonOfList = SQLite.select().from(StepSubLessonOf.class)
                .queryList();

        for (int i = 0; i < stepSubLessonOfList.size(); i++) {

            Log.e("liniiiii","// ------------------------------------ "+i);
            Log.e("step1",stepSubLessonOfList.get(i).getStep1());
            Log.e("step2",stepSubLessonOfList.get(i).getStep2());
            Log.e("step3",stepSubLessonOfList.get(i).getStep3());
            Log.e("step4",stepSubLessonOfList.get(i).getStep4());
            Log.e("step5",stepSubLessonOfList.get(i).getStep5());
            Log.e("step6",stepSubLessonOfList.get(i).getStep6());
            Log.e("step7",stepSubLessonOfList.get(i).getStep7());

        }

    }

    public void changeQusetionPosition(){

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new MainFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.next:
                questionNum++;
                changeQusetionPosition();
                next.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setEnabled(true);
                    }
                }, 1000);
                break;

            case R.id.previous:
                if (questionNum != 0)
                {
                    questionNum--;
                    changeQusetionPosition();
                }

                next.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        next.setEnabled(true);

                    }
                }, 1000);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        try
        {
            utils.tts_en.stop();
            utils.tts_en.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

}
