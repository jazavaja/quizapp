package com.javad.quizapplang.helperClasses;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    List<String> strings;
    FlowLayout flowLayout;
    FlowLayout soratSoal;
    //    FlowLayout balayi;
    List<List<String>> listListGozineha;
//    FlowLayout flowGozine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type13a);

        flowLayout = findViewById(R.id.llMain);
        soratSoal = findViewById(R.id.flow_head);


        strings = new ArrayList<>();
//        strings.add("Ali" + "\n" +"____________" +"\n" + "علی");
//        strings.add("--" + "\n" +"____________" +"\n" + "مهدی");
//        strings.add("Amir" + "\n" +"____________" +"\n" + "امیر");
//        strings.add("--" + "\n" +"____________" +"\n" + "حسین");
//        strings.add("Javad" + "\n"+"____________" +"\n"  + "جواد");
//        strings.add("--" + "\n" +"____________" +"\n" + "جواد");
//        strings.add("Aboli" + "\n" +"____________" +"\n" + "ابولی");

        strings.add(" -- "+ "\n" +"____________" +"\n" +  "اسم کامل");
        strings.add(" -- "+ "\n" +"____________" +"\n" +  "اسم کوچک");
        strings.add(" -- "+ "\n" +"____________" +"\n" +  "اسم میانی");
        strings.add(" -- "+ "\n" +"____________" +"\n" +  "نام خانوادگی");


        List<String> javab = new ArrayList<>();
        javab.add("full name");
        javab.add("first name");
        javab.add("middle name");
        javab.add("last name");

        listListGozineha = new ArrayList<>();
        for (int i = 0; i < javab.size(); i++) {
            listListGozineha.add(wordToAlph(javab.get(i)));
        }

//        List<String> a1 = new ArrayList<>();
//        a1.add("y");
//        a1.add("e");
//        a1.add("s");
//        listListGozineha.add(a1);
//        List<String> a2 = new ArrayList<>();
//        a2.add("t");
//        a2.add("a");
//        a2.add("h");
//        a2.add("k");
//        a2.add("n");
//        a2.add("s");
//
//        listListGozineha.add(a2);
//
//        List<String> a3 = new ArrayList<>();
//        a3.add("d");
//        a3.add("o");
//        a3.add("g");
//        a3.add("o");
//
//        listListGozineha.add(a3);

        FunType14 funType14 = new FunType14();
        funType14.main(this, listListGozineha, javab, strings, soratSoal, flowLayout, new FunType14.fun14() {
            @Override
            public void CustomTextViewSoratSoal(List<TextView> textViews, int position, boolean isJakhali) {
                textViews.get(position).setPadding(9, 9, 9, 9);
                textViews.get(position).setTextSize(20f);
                if (isJakhali) {
                    textViews.get(position).setGravity(Gravity.CENTER);
                    textViews.get(position).setTextColor(Color.BLUE);
                }
            }

            @Override
            public void When_Jakhali_Click_Kard(TextView textView) {
                textView.setText(""+"\n"+textView.getText().toString().split("\n")[1]);
            }

            @Override
            public void CustomTextViewGozineha(List<TextView> listGozineha, int position) {
                listGozineha.get(position).setPadding(9, 9, 9, 9);
                listGozineha.get(position).setTextSize(20f);
            }

            @Override
            public void When_Gozineha_Click(TextView textViewGozine, TextView textviewJakhali) {

            }

            @Override
            public void When_Javab_True(TextView textView) {
                textView.setTextColor(Color.GREEN);
            }

            @Override
            public void When_JavabFalse(TextView textView) {
                textView.setTextColor(Color.RED);
            }

            @Override
            public void WhenDone() {
                Toast.makeText(MainActivity2.this, "اتمام سوال", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CustomTextViewGozineha2(List<TextView> listGozineha, int i) {
                listGozineha.get(i).setPadding(9, 9, 9, 9);
                listGozineha.get(i).setTextSize(20f);
            }
        });

//        funType14.main(this, listListGozineha, strings, soratSoal, flowLayout, new FunType14.fun14() {
//            @Override
//            public void CustomTextViewSoratSoal(List<TextView> textViews, int position, boolean isJakhali) {
//                textViews.get(position).setPadding(5,5,5,5);
//            }
//
//            @Override
//            public void When_Jakhali_Click_Kard(TextView textView) {
//                textView.setText("      "+"\n"+textView.getText().toString().split("\n")[1]);
//            }
//
//            @Override
//            public void CustomTextViewGozineha(List<TextView> listGozineha, int i) {
//                listGozineha.get(i).setPadding(4,4,4,4);
//            }
//
//            @Override
//            public void When_Gozineha_Click(TextView textViewGozine, TextView textviewJakhali) {
//
////                Log.e("EEE",textviewJakhali.getText().toString().split("\n")[1]);
//                Toast.makeText(MainActivity.this, textViewGozine.getText().toString()+"   "+textviewJakhali.getText().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void When_Javab_True() {
//
//            }
//
//            @Override
//            public void When_JavabFalse() {
//
//            }
//        });
//        funType14.main(this, strings, flowLayout, new FunType14.fun14() {
//            @Override
//            public void CustomTextViewSoratSoal(List<TextView> textViews, int position, boolean isJakhali) {
//                textViews.get(position).setPadding(0,0,20,0);
//                if (isJakhali){
//                    textViews.get(position).setBackgroundColor(Color.GREEN);
//                }
//                textViews.get(position).setBackgroundColor(Color.BLUE);
//            }
//
//            @Override
//            public void When_Jakhali_Click_Kard(TextView textView) {
//
//            }
//
//        });
//        List<String> zirNevis = new ArrayList<>();
//        zirNevis.add("Jazava");
//        zirNevis.add("Ahmad");
//        zirNevis.add("Milad");
//        zirNevis.add("Sobhan");
//        zirNevis.add("Vira");
//        zirNevis.add("Abbas");
//
//        final FunType8And9 funType8 = new FunType8And9();
//        funType8.main(this, strings, zirNevis, soratSoal, flowLayout, new FunType8And9.funOpt8() {
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
//            public void SetEffectWhenGozinehaClick() {
//
//            }
//        });
//
//        Button button=findViewById(R.id.fff);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("OOOOO",funType8.getStrJakhaliha(soratSoal).get(0));
//            }
//        });
    }


    private List<String> wordToAlph(String word){

        List<String> stringList = new ArrayList<>();

        for (int j = 0; j < word.length(); j++) {

//                int random = randoms.nextInt(alphabet.length());
            stringList.add(word.substring(j,j+1));

        }
        return stringList;
    }


}
