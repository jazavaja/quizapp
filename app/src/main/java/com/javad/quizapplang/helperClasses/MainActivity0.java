package com.javad.quizapplang.helperClasses;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity0 extends AppCompatActivity {

    List<String> strings;
    FlowLayout flowLayout;
    FlowLayout soratSoal;
    //    FlowLayout balayi;
    List<List<String>> listListGozineha;
//    FlowLayout flowGozine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        flowLayout = findViewById(R.id.llMain);
        soratSoal = findViewById(R.id.soratSoal);


        List<String> soratSoal=new ArrayList<>();
        soratSoal.add("Hello");
        soratSoal.add("Javad");
        soratSoal.add("--");
        soratSoal.add("How Are you");

        List<String> javab=new ArrayList<>();
        FunType11 funType11=new FunType11();
        funType11.setSoratSoal(this, soratSoal, flowLayout, soratSoal, new FunType11.fun11() {
            @Override
            public void SetEffectDisableJakhali(List<View> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(EditText textView) {

            }

            @Override
            public void WhenJakhaliClick(List<View> textView, int t) {

            }

            @Override
            public void SetEffectFirstPositionJakhali(View textView) {

            }

            @Override
            public void CustomTextViewSoratSoal(List<View> listJaKhaliha, int i) {

            }

            @Override
            public void CustomVoice(View t) {

            }

            @Override
            public void WhenVoiceClick(List<View> listJaKhaliha, int position, List<String> everyMokaleme) {

            }

            @Override
            public void OnTextChanged(List<View> listJaKhaliha, int positionListJakhali, Integer integer, CharSequence s, int start, int before, int count) {

            }
        });



    }


}
