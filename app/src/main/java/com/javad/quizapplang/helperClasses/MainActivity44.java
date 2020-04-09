package com.javad.quizapplang.helperClasses;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity44 extends AppCompatActivity {

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
//        testType15();
//        testType10();
//        testType11();
//        textType8A9();
    }

    public void testType10(){

        List<String> str1=new ArrayList<>();
        str1.add("javad");
        str1.add("--");
        str1.add("--");
        str1.add("javad");
        str1.add("##");
        str1.add("javad");
        str1.add("--");
        str1.add("ahmad");

        List<String> uuu=new ArrayList<>();
        uuu.add("javad sarlak javad");
        uuu.add("javad saeed ahmad");
        uuu.add("javad saeed ahmad");


        FunType10 funType10=new FunType10();

        funType10.setSoratSoal(this, str1, soratSoal,uuu, new FunType10.fun10() {
            @Override
            public void SetEffectDisableJakhali(List<TextView> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(TextView textView) {

            }

            @Override
            public void WhenJakhaliClick(List<TextView> textView, int t) {
                Toast.makeText(MainActivity44.this, textView.get(t).getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void SetEffectFirstPositionJakhali(TextView textView) {

            }

            @Override
            public void CustomTextViewSoratSoal(List<TextView> listJaKhaliha, int i) {
                listJaKhaliha.get(i).setPadding(5,4,4,5);
                boolean oi=listJaKhaliha.get(i).getTag().equals("1");
                if (oi)
                    listJaKhaliha.get(i).setBackgroundColor(Color.GREEN);
//                if (is){
//                    listJaKhaliha.get(i).setBackgroundColor(Color.RED);
//                }
            }

            @Override
            public void CustomVoice(TextView t) {
                t.setText("     ");
                t.setBackgroundColor(Color.RED);
            }

            @Override
            public void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme) {
                for (int i=0;i<everyMokaleme.size();i++){
                    Log.e("Tag",everyMokaleme.get(i));
                }
                Toast.makeText(MainActivity44.this, everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void WhenPO(List<TextView> listJaKhaliha, int pppsss, Integer integer) {

            }


        });
    }

    public void testType11(){

        List<String> str1=new ArrayList<>();
        str1.add("javad");
        str1.add("--");
        str1.add("javad");
        str1.add("##");
        str1.add("javad");
        str1.add("--");
        str1.add("ahmad");

        List<String> uuu=new ArrayList<>();
        uuu.add("javad sarlak javad");
        uuu.add("javad saeed ahmad");


        FunType11 funType10=new FunType11();

        funType10.setSoratSoal(this, str1, soratSoal,uuu, new FunType11.fun11() {
            @Override
            public void SetEffectDisableJakhali(List<View> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(EditText textView) {
                textView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Toast.makeText(MainActivity44.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
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
                TextView textView= (TextView) t;
                textView.setText("   ");
                textView.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void WhenVoiceClick(List<View> listJaKhaliha, int position, List<String> everyMokaleme) {
                Toast.makeText(MainActivity44.this, everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnTextChanged(List<View> listJaKhaliha, int positionListJakhali, Integer integer, CharSequence s, int start, int before, int count) {

            }

        });
    }

//    public void testType15(){
////        Matn zirnevis koli bayad @@ dashte bashe     Jakhali bayad -- bashad
//        List<List<String>> listList=new ArrayList<>();
//        List<String> str1=new ArrayList<>();
//        str1.add("javad");
//        str1.add("--");
//        str1.add("javad");
//        str1.add("javad");
//        str1.add("--");
//        str1.add("@@اینجا متن زیر نویس تست است");
//        listList.add(str1);
//
//        List<String> wwww=new ArrayList<>();
//        wwww.add("sssss");
//        wwww.add("--");
//        wwww.add("sssss");
//        wwww.add("sssss");
//        wwww.add("--");
//        wwww.add("@@اینجا متن زیر نویس تست است");
//        listList.add(wwww);
//
//        List<String> uuu=new ArrayList<>();
//        uuu.add("oooo");
//        uuu.add("--");
//        uuu.add("iiii");
//        uuu.add("kkkk");
//        uuu.add("--");
//        uuu.add("@@اینجا متن زیر نویس تست است");
//        listList.add(uuu);
//
//
//        List<List<String>> listList2=new ArrayList<>();
//        List<String> str12=new ArrayList<>();
//        str12.add("g");
//        str12.add("o");
//        str12.add("d");
//        listList2.add(str12);
//        List<String> str13=new ArrayList<>();
//        str13.add("d");
//        str13.add("o");
//        str13.add("g");
//        listList2.add(str13);
//        List<String> eeee=new ArrayList<>();
//        eeee.add("d");
//        eeee.add("a");
//        eeee.add("d");
//        listList2.add(eeee);
//        List<String> oooo=new ArrayList<>();
//        oooo.add("p");
//        oooo.add("o");
//        oooo.add("g");
//        listList2.add(oooo);
//        List<String> qqqqq=new ArrayList<>();
//        qqqqq.add("l");
//        qqqqq.add("o");
//        qqqqq.add("g");
//        listList2.add(qqqqq);
//
//        List<String> yyy=new ArrayList<>();
//        yyy.add("h");
//        yyy.add("o");
//        yyy.add("t");
//        listList2.add(yyy);
//
//
//        List<String> javab=new ArrayList<>();
//        javab.add("god");
//        javab.add("dog");
//        javab.add("dad");
//
//        javab.add("pog");
//        javab.add("log");
//        javab.add("hot");
//        FunType15 funType15=new FunType15();
//        funType15.main();
//    }

    public void textType8A9(){
        List<String> str1=new ArrayList<>();
        str1.add("javad");
        str1.add("--");
        str1.add("javad");
        str1.add("##");
        str1.add("javad");
        str1.add("javad");
        str1.add("##");
        str1.add("asasqedc");


        List<String> gozineha=new ArrayList<>();
        str1.add("ahmad");
        str1.add("javad");
        str1.add("ali");
        str1.add("javad");
        str1.add("mehdi");
        str1.add("aswwww");
//        FunType8And9 funType8And9=new FunType8And9();
//        funType8And9.main(this, str1, flowGozine, soratSoal, flowLayout, new FunType8And9.funOpt8() {
//            @Override
//            public void CunstomTextViewBottom(List<TextView> textViews, int position) {
//                textViews.get(position).setPadding(3,3,3,3);
//            }
//
//            @Override
//            public void CustomTextViewSoratSoal(List<TextView> textViews, int position) {
//
//            }
//
//            @Override
//            public void SetEffectDisableJakhali(List<TextView> textViews, int position) {
//
//            }
//
//            @Override
//            public void SetEffectWhenEnableJakhali(TextView textView) {
//
//            }
//
//            @Override
//            public void SetEffectFirstPositionJakhali(TextView textView) {
//
//            }
//
//            @Override
//            public void SetEffectWhenGozinehaClick(TextView textView) {
//
//            }
//
//            @Override
//            public void WhenJakhaliClick(TextView textView, TextView b) {
//
//            }
//
//            @Override
//            public void WhenVoiceClick(TextView textView) {
//
//            }
//
//            @Override
//            public void CustomVoice(TextView t) {
//                t.setPadding(5,5,5,5);
//                t.setBackgroundColor(Color.BLUE);
//            }
//        });
    }
}
