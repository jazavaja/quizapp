package com.javad.quizapplang.helperClasses;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> strings;
    FlowLayout flowLayout;
    FlowLayout soratSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type9);
        flowLayout = findViewById(R.id.llMain);
        soratSoal=findViewById(R.id.flow_head);
        strings = new ArrayList<>();
        strings.add("1");
        strings.add("--");
        strings.add("2");
        strings.add("--");
        strings.add(",");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("##");
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("--");
        strings.add("4");
        strings.add("5");
        strings.add("##");
        strings.add("1");
        strings.add("--");
        strings.add("2");
        strings.add("--");
        strings.add("3");
        strings.add("--");
        strings.add("4");
        strings.add("--");

        List<String> zirNevis = new ArrayList<>();
        zirNevis.add("Jazava");
        zirNevis.add("Ahmad");
        zirNevis.add("Milad");
        zirNevis.add("Sobhan");
        zirNevis.add("Vira");
        zirNevis.add("Abbas");

        List<String> javab = new ArrayList<>();
        javab.add("yes");
        javab.add("thanks you");
        javab.add("--");
        javab.add("good");
        javab.add("##");
        javab.add("good");
        javab.add("good");
        javab.add("--");
        javab.add("good");
        javab.add("##");
        javab.add("good");

//        List<String> qBodies = new ArrayList<>();
//        qBodies.add("Sara: Hello, I’m Sara Zabani.");
//        qBodies.add("Zahra: Hi,-- Zahra Salmani. Nice to meet you.");
//        qBodies.add("Sara: Nice to meet you, too.");
//
//        List<String> qOptions = new ArrayList<>();
//        qOptions.add("i’m");
//        qOptions.add("you are");
//        qOptions.add("we are");
//        qOptions.add("they are");


//        String string = "";
//
//        for (int i = 0; i < qBodies.size(); i++) {
//            string += qBodies.get(i).replace("--"," -- ") + " / ";
//        }
//        String[] title = string.split(" ");
//
//        for (int i = 0; i < title.length; i++) {
//            Log.e("list.add == ", title[i]);
//            strings.add(title[i]);
//        }

//        String ssss = questions_of.getSubtitle();

//        for (int i = 0; i < qOptions.size(); i++) {
//            typeList.add(qOptions.get(i));
//        }

        final FunType8And9 funType8 = new FunType8And9();
//        funType8.main(this, javab, zirNevis, soratSoal, flowLayout, new FunType8And9.funOpt8() {
//            @Override
//            public void CunstomTextViewBottom(List<TextView> textViews, int position) {
//                textViews.get(position).setPadding(5,5,5,5);
//                textViews.get(position).setTextSize(22f);
//            }
//
//            @Override
//            public void CustomTextViewSoratSoal(List<TextView> textViews, int position) {
//                textViews.get(position).setPadding(5,5,5,5);
//                textViews.get(position).setTextSize(22f);
//            }
//
//            @Override
//            public void SetEffectDisableJakhali(List<TextView> textViews, int position) {
//                textViews.get(position).setBackgroundColor(Color.WHITE);
//            }
//
//            @Override
//            public void SetEffectWhenEnableJakhali(TextView textView) {
//                textView.setBackgroundColor(Color.GREEN);
//            }
//
//            @Override
//            public void SetEffectFirstPositionJakhali(TextView textView) {
//                textView.setBackgroundColor(Color.GREEN);
//            }
//
//            @Override
//            public void SetEffectWhenGozinehaClick(TextView textView) {
//                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void WhenJakhaliClick(TextView textView, TextView b) {
//                textView.setBackgroundColor(Color.GREEN);
//                b.setBackgroundColor(Color.GREEN);
//            }
//
//            @Override
//            public void WhenVoiceClick(TextView textView) {
//
//            }
//
//            @Override
//            public void CustomVoice(TextView t) {
//
//            }
//        });

//        Button button=findViewById(R.id.fff);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("OOOOO",funType8.getStrJakhaliha(soratSoal).get(0));
//            }
//        });
    }


}
