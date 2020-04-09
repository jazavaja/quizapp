package com.javad.quizapplang.helperClasses;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.javad.quizapplang.utils.General;
import com.nex3z.flowlayout.FlowLayout;
//import com.pollfish.main.PollFish;

import java.util.ArrayList;
import java.util.List;

import theoremreach.com.theoremreach.TheoremReach;

public class MainActivity8 extends AppCompatActivity {

    List<String> strings;
    FlowLayout flowLayout;
    FlowLayout fff;
    FlowLayout soratSoal;
    Button b;
    RelativeLayout relativeLayout;
    List<List<String>> listListGozineha;
    int paddingTop = 10;

    @Override
    protected void onResume() {
        super.onResume();
        TheoremReach.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TheoremReach.getInstance().onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        flowLayout = findViewById(R.id.llMain);
        b = findViewById(R.id.fff);
        soratSoal = findViewById(R.id.soratSoal);
        fff = findViewById(R.id.balayi);
        relativeLayout = findViewById(R.id.reeeeeee);
//        testType16();
//        testType6New();
        testType15();
    }

    public void testSub() {
//        String[] j;
//        j[0]="lpln";

        List<String> str12 = new ArrayList<>();
        str12.add("Javad");
        str12.add("is");
        str12.add("good");
        str12.add("man");
        final OptionsSetSub2 optionsSetSub2 = new OptionsSetSub2();
        optionsSetSub2.main(this, OptionsSetSub2.ENGLISH, OptionsSetSub2.ENGLISH, str12,
                str12, str12, soratSoal, flowLayout, fff, relativeLayout, str12.size(), new OptionsSetSub2.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(5, 5, 5, 5);
                    }

                    @Override
                    public void CustomTextViewGozineha(List<TextView> textViews, int position) {
                        textViews.get(position).setTextColor(Color.RED);
                        textViews.get(position).setPadding(5, 5, 5, 5);
                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setTextColor(Color.BLUE);
                        textView.setPadding(5, 5, 5, 5);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {

                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {

                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {

                    }
                });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity8.this, "Seak", Toast.LENGTH_SHORT).show();
                List<String> s = OptionsSetSub2.getStrListTxtView(fff, OptionsSetSub2.PERSIAN);
                String ssww = OptionsSetSub2.getStringOfListString(s);
                Toast.makeText(MainActivity8.this, ssww, Toast.LENGTH_SHORT).show();
//                OptionsSetSub2.getStrListTxtView();
            }
        });

    }

    public void testType16() {
//        Matn zirnevis koli bayad @@ dashte bashe     Jakhali bayad -- bashad
        List<List<String>> listList = new ArrayList<>();
        List<String> uuu = new ArrayList<>();
        uuu.add("Ali:");
        uuu.add("good");
        uuu.add("morning,");
        uuu.add("Sara.");
        uuu.add("How");
        uuu.add("are");
        uuu.add("you?");
        uuu.add("@@");
//        uuu.add("##");
        listList.add(uuu);
        List<String> uu = new ArrayList<>();
        uu.add("Sara:");
        uu.add("I’m");
        uu.add("good.");
        uu.add("--");
        uu.add("?");
        uu.add("@@");
//        uu.add("##");
        listList.add(uu);
        List<String> u = new ArrayList<>();
        u.add("Ali:");
        u.add("fine,");
        u.add("thanks.");
        u.add("@@");
//        uuu.add("@@اینجا متن زیر نویس تست است");
        listList.add(u);

        List<String> iii = new ArrayList<>();
        iii.add("Sara:");
        iii.add("I’m");
        iii.add("good.");
        iii.add("--");
        iii.add("?");
        iii.add("@@");
//        uu.add("##");
        listList.add(iii);
        List<String> oooo = new ArrayList<>();
        oooo.add("Ali:");
        oooo.add("fine,");
        oooo.add("thanks.");
        oooo.add("@@");
//        uuu.add("@@اینجا متن زیر نویس تست است");
        listList.add(oooo);

        List<String> yyy = new ArrayList<>();
        yyy.add("Sara:");
        yyy.add("I’m");
        yyy.add("good.");
        yyy.add("--");
        yyy.add("?");
        yyy.add("@@");


        List<List<String>> listList2 = new ArrayList<>();
        List<String> str12 = new ArrayList<>();
        str12.add("how are you");
        str12.add("are you javad");
        str12.add("yes i am javad");
        listList2.add(str12);
        List<String> str13 = new ArrayList<>();
        str13.add("i am reza");
        str13.add("are you javad");
        str13.add("good look");
        listList2.add(str13);
        List<String> eeee = new ArrayList<>();
        eeee.add("ali is good face");
        eeee.add("is face good");
        eeee.add("saaed is good");
        listList2.add(eeee);

        List<String> javab = new ArrayList<>();
        javab.add("how are you");
        javab.add(" are you javad");
        javab.add(" saaed is good");

//        Fun16 fun16=new Fun16();
//        fun16.main(this, soratSoal, flowLayout, listList, listList2, javab, javab, new Fun16.funInterface() {
//            @Override
//            public void CustomTextViewMokaleme(List<TextView> textView, int pos) {
//
//            }
//
//            @Override
//            public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception {
//
//            }
//
//            @Override
//            public void SetonclickGozine(TextView textView) {
//
//            }
//
//            @Override
//            public void WhenGozineTrue(TextView t) {
//
//            }
//
//            @Override
//            public void WhenGozineFalse(TextView t) {
//
//            }
//
//            @Override
//            public void CustomTxtViewGozineha(List<TextView> lstTxtViewGozine, int i) {
//
//            }
//
//            @Override
//            public void WhenDone() {
//
//            }
//
//            @Override
//            public void CustomVoice(TextView t) {
//
//            }
//
//            @Override
//            public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme) {
//
//            }
//
//            @Override
//            public void CannotAddNewGozine() {
//
//            }
//
//            @Override
//            public void CustomViewAdamak(List<ImageView> adamakHa) {
//
//            }
//
//            @Override
//            public void customFlowLayoutMokaleme(List<FlowLayout> flowLayoutList) {
//
//            }
//
//            @Override
//            public void CustomTxtViewMokaleme(TextView textView) {
//
//            }
//        });
    }

    public void testType10() {

        List<String> str1 = new ArrayList<>();
        str1.add("javad");
        str1.add("--");
        str1.add("javad");
        str1.add("##");
        str1.add("javad");
        str1.add("--");
        str1.add("ahmad");
        str1.add("##");
        str1.add("hello");
        str1.add("how");
        str1.add("are");
        str1.add("you");

        List<String> uuu = new ArrayList<>();
        uuu.add("javad sarlak javad");
        uuu.add("javad saeed ahmad");
        uuu.add("hello how are you");


        FunType10 funType10 = new FunType10();

        funType10.setSoratSoal(this, str1, soratSoal, uuu, new FunType10.fun10() {
            @Override
            public void SetEffectDisableJakhali(List<TextView> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(TextView textView) {

            }

            @Override
            public void WhenJakhaliClick(List<TextView> textView, int t) {
                Toast.makeText(MainActivity8.this, textView.get(t).getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void SetEffectFirstPositionJakhali(TextView textView) {

            }

            @Override
            public void CustomTextViewSoratSoal(List<TextView> listJaKhaliha, int i) {
                listJaKhaliha.get(i).setPadding(5, 4, 4, 5);
                boolean oi = listJaKhaliha.get(i).getTag().equals("1");
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
                for (int i = 0; i < everyMokaleme.size(); i++) {
                    Log.e("Tag", everyMokaleme.get(i));
                }
                Toast.makeText(MainActivity8.this, everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity8.this, everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void WhenPO(List<TextView> listJaKhaliha, int pppsss, Integer integer) {

            }

        });
    }

    public void testType11() {

        List<String> str1 = new ArrayList<>();
        str1.add("javad");
        str1.add("--");
        str1.add("javad");
        str1.add("##");
        str1.add("javad");
        str1.add("--");
        str1.add("ahmad");
        str1.add("##");
        str1.add("hello");
        str1.add("how");
        str1.add("are");
        str1.add("you");

        List<String> uuu = new ArrayList<>();
        uuu.add("javad sarlak javad");
        uuu.add("javad saeed ahmad");
        uuu.add("hello how are you");


        FunType11 funType10 = new FunType11();

        funType10.setSoratSoal(this, str1, soratSoal, uuu, new FunType11.fun11() {
            @Override
            public void SetEffectDisableJakhali(List<View> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(EditText textView) {

            }

            @Override
            public void WhenJakhaliClick(List<View> textView, int t) {
//                EditText editText= (EditText) textView.get(t);
//                Toast.makeText(MainActivity8.this, editText.getText().toString()
//                        , Toast.LENGTH_SHORT).show();


            }

            @Override
            public void SetEffectFirstPositionJakhali(View textView) {

            }

            @Override
            public void CustomTextViewSoratSoal(List<View> listJaKhaliha, int i) {

            }

            @Override
            public void CustomVoice(View t) {
                TextView textView = (TextView) t;
                textView.setText("   ");
                textView.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void WhenVoiceClick(List<View> listJaKhaliha, int position, List<String> everyMokaleme) {
                Toast.makeText(MainActivity8.this, everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnTextChanged(List<View> listJaKhaliha, int positionListJakhali, Integer integer, CharSequence s, int start, int before, int count) {

            }


        });
    }

    public void testType15() {
//        Matn zirnevis koli bayad @@ dashte bashe     Jakhali bayad -- bashad
        List<List<String>> listList = new ArrayList<>();
        final List<String> str1 = new ArrayList<>();
        str1.add("javad");
        str1.add("sarlak");
        str1.add("saaed");
        str1.add("mehdi");
        str1.add("ahmad");
        str1.add("@@اینجا متن زیر نویس تست است");
        listList.add(str1);

        List<String> wwww = new ArrayList<>();
        wwww.add("sssss");
        wwww.add("--");
        wwww.add("sssss");
        wwww.add("@@اینجا متن زیر نویس تست است");
        listList.add(wwww);

        List<String> uuu = new ArrayList<>();
        uuu.add("oooo");
        uuu.add("--");
        uuu.add("iiii");
        uuu.add("kkkk");
        uuu.add("@@اینجا متن زیر نویس تست است");
        listList.add(uuu);


        List<List<String>> listList2 = new ArrayList<>();
        List<String> str12 = new ArrayList<>();
        str12.add("g");
        str12.add("o");
        str12.add("d");
        listList2.add(str12);
        List<String> str13 = new ArrayList<>();
        str13.add("d");
        str13.add("o");
        str13.add("g");
        listList2.add(str13);

        List<String> javab = new ArrayList<>();
        javab.add("god");
        javab.add("dog");

        Fun15 fun15=new Fun15();
        fun15.main(this, soratSoal, flowLayout, listList, listList2, javab, javab, new Fun15.funInterface() {
            @Override
            public void WhenCorrectGozine(TextView textView) {

            }

            @Override
            public void CustomTextViewMokaleme(List<TextView> textView, int pos) {

            }

            @Override
            public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception {

            }

            @Override
            public void SetonclickGozine(TextView txtViewGozine, TextView txtViewJakhaliEnable) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(txtViewJakhaliEnable.getText().toString());
                stringBuilder.append(txtViewGozine.getText().toString());
                txtViewJakhaliEnable.setText(stringBuilder.toString());
            }

            @Override
            public void WhenGozineFalse(TextView t) {
                t.setTextColor(Color.RED);
            }

            @Override
            public void CustomTxtViewGozineha(List<TextView> lstTxtViewGozine, int i, ViewGroup.LayoutParams layoutParams) {

            }

            @Override
            public void WhenDone() {
                Toast.makeText(MainActivity8.this, "Done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CustomVoice(TextView t) {

            }

            @Override
            public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme) {

            }

            @Override
            public void CannotAddNewGozine() {

            }

            @Override
            public void CustomViewAdamak(List<ImageView> adamakHa) {
                Log.e("ADAMAKHa", adamakHa.size() + "");
                for (int i = 0; i < adamakHa.size(); i++) {
                    adamakHa.get(i).setBackgroundResource(R.drawable.ic_person_black_24dp);
                }
            }

            @Override
            public void customFlowLayoutMokaleme(List<FlowLayout> flowLayoutList) {
                for (int i = 0; i < flowLayoutList.size(); i++) {
                    if (i % 2 == 0) {
                        flowLayoutList.get(i).setPadding(45, 0, 0, 0);
                        flowLayoutList.get(i).setBackgroundResource(R.drawable.back_type16);
                    } else {
                        flowLayoutList.get(i).setPadding(45, 0, 0, 0);
                        flowLayoutList.get(i).setBackgroundResource(R.drawable.back_type16_2);
                    }
                }
            }

            @Override
            public void CustomTxtViewMokaleme(TextView textView) {
                textView.setTextColor(Color.BLACK);
                textView.setPadding(4, 4, 4, 4);
                if (textView.getTag().equals("1")) {
                    textView.setBackgroundResource(R.drawable.jakhali);
                }
            }

            @Override
            public void CustomLinearMainMokalemeHa(List<LinearLayout> linearLayoutList, ViewGroup.LayoutParams layoutParams) {
                for (int i = 0; i < linearLayoutList.size(); i++) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) linearLayoutList.get(i).getLayoutParams();
                    params.bottomMargin = 20;
                }
            }
        });
    }

    public void textType8A9() {
        List<String> str1 = new ArrayList<>();
        str1.add("javad");
        str1.add("--");
        str1.add("javad");
        str1.add("##");
        str1.add("javad");
        str1.add("javad");
        str1.add("##");
        str1.add("asasqedc");
        str1.add("--");
        str1.add("asasqedc");


        List<String> gozineha = new ArrayList<>();
        gozineha.add("ahmad");
        gozineha.add("javad");
        gozineha.add("ali");
        gozineha.add("javad");
        gozineha.add("mehdi");
        gozineha.add("aswwww");
        FunType8And9 funType8And9 = new FunType8And9();
        funType8And9.main(this, str1, gozineha, soratSoal, str1, flowLayout, new FunType8And9.funOpt8() {
            @Override
            public void CunstomTextViewBottom(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(4, paddingTop, 4, paddingTop);
                textViews.get(position).setTextSize(18f);
                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setTextColor(Color.BLACK);
            }

            @Override
            public void CustomTextViewSoratSoal(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(5, 5, 5, 5);
                textViews.get(position).setTextSize(18f);
                textViews.get(position).setGravity(Gravity.CENTER);
//                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setTextColor(Color.BLACK);
                if (textViews.get(position).getTag() == "1" && !
                        textViews.get(position).getText().toString().equals(""))
                    textViews.get(position).setLayoutParams(
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            @Override
            public void SetEffectDisableJakhali(List<TextView> textViews, int position) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(TextView textView) {
//                textView.setTextColor(Color.BLUE);

            }

            @Override
            public void SetEffectFirstPositionJakhali(TextView textView) {

            }

            @Override
            public void SetEffectWhenGozinehaClick(TextView textView, TextView jakhali) {
                jakhali.setTextColor(Color.RED);
                textView.setBackgroundColor(Color.GRAY);
                textView.setTextColor(Color.GRAY);

                for (int i = 0; i < FunType8And9.getStrJakhaliha(flowLayout).size(); i++) {
                    Log.e("size = ", FunType8And9.getStrJakhaliha(flowLayout).get(i));
                }
            }

            @Override
            public void WhenJakhaliClick(TextView textView, List<TextView> b, int i) {
//                textView.setBackgroundResource(R.drawable.shape);
                b.get(i).setBackgroundColor(Color.WHITE);
                b.get(i).setTextColor(Color.BLACK);
            }

            @Override
            public void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme) {
                String[] cont = everyMokaleme.get(position).split(":");
//                utils.tts(cont[1],1f, questions_of.getSex());
            }

            @Override
            public void CustomVoice(TextView t) {
                t.setPadding(15, 15, 15, 15);
                t.setBackgroundResource(General.iconSeda);
            }
        });
    }

    public void testType6New() {
        List<List<String>> listList = new ArrayList<>();
        List<String> uuu = new ArrayList<>();
        uuu.add("Ali:");
        uuu.add("good");
        uuu.add("morning,");
        uuu.add("Sara.");
        uuu.add("How");
        uuu.add("are");
        uuu.add("you?");
        uuu.add("@@");
        listList.add(uuu);
        List<String> uu = new ArrayList<>();
        uu.add("Sara:");
        uu.add("I’m");
        uu.add("good.");
        uu.add("--");
        uu.add("?");
        uu.add("@@");
        listList.add(uu);
        List<String> u = new ArrayList<>();
        u.add("Ali:");
        u.add("fine,");
        u.add("thanks.");
        u.add("@@");
        listList.add(u);

        List<String> b = new ArrayList<>();
        b.add("Milad:");
        b.add("Good,");
        b.add("--");
        b.add("@@");
        listList.add(b);

        List<String> op = new ArrayList<>();
        op.add("Javad:");
        op.add("Hi,");
        op.add("--");
        op.add("@@");
        listList.add(op);



        List<List<String>> listList2 = new ArrayList<>();
        List<String> str12 = new ArrayList<>();
        str12.add("how are you");
        str12.add("are you javad");
        str12.add("yes i am javad");
        listList2.add(str12);
        List<String> str13 = new ArrayList<>();
        str13.add("i am reza");
        str13.add("are you javad");
        str13.add("good look");
        listList2.add(str13);
        List<String> eeee = new ArrayList<>();
        eeee.add("ali is good face");
        eeee.add("is face good");
        eeee.add("saaed is good");
        listList2.add(eeee);

        List<String> javab = new ArrayList<>();
        javab.add("how are you");
        javab.add("are you javad");
        javab.add("saaed is good");


        Fun16 fun16 = new Fun16();
        fun16.main(this, soratSoal, flowLayout, listList, listList2, javab, javab, new Fun16.funInterface() {
            @Override
            public void WhenCorrectGozine(TextView textView) {
                textView.setTextColor(Color.GREEN);
            }

            @Override
            public void CustomTextViewMokaleme(List<TextView> textView, int pos) {

            }

            @Override
            public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception {

            }

            @Override
            public void SetonclickGozine(TextView txtViewGozine, TextView txtViewJakhaliEnable) {
//                txtViewGozine
                txtViewJakhaliEnable.setText(txtViewGozine.getText().toString());

            }
            @Override
            public void WhenGozineFalse(TextView t) {
                t.setTextColor(Color.RED);
            }

            @Override
            public void CustomTxtViewGozineha(List<TextView> lstTxtViewGozine, int i, ViewGroup.LayoutParams layoutParams) {
                lstTxtViewGozine.get(i).setPadding(9, 9, 9, 9);
                lstTxtViewGozine.get(i).setTextColor(Color.BLACK);
                lstTxtViewGozine.get(i).setBackgroundResource(R.drawable.shape_et);
                flowLayout.setRowSpacing(4.0f);
//                ViewGroup.MarginLayoutParams layoutParams= (ViewGroup.MarginLayoutParams) lstTxtViewGozine.get(i).getLayoutParams();
//                layoutParams.setMargins(5,5,5,5);

            }

            @Override
            public void WhenDone() {
//                flowLayout.setVisibility(View.GONE);
//                soratSoal.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"سوال با موفقیت پاسخ داده شد(بازخورد اینجا مثل قبل است)",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CustomVoice(TextView t) {

            }

            @Override
            public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme) {

            }

            @Override
            public void CannotAddNewGozine() {

            }

            @Override
            public void CustomViewAdamak(List<ImageView> adamakHa) {
                Log.e("ADAMAKHa", adamakHa.size() + "");
                for (int i = 0; i < adamakHa.size(); i++) {
                    adamakHa.get(i).setBackgroundResource(R.drawable.ic_person_black_24dp);
                }
            }

            @Override
            public void customFlowLayoutMokaleme(List<FlowLayout> flowLayoutList) {
                for (int i = 0; i < flowLayoutList.size(); i++) {
                    if (i % 2 == 0) {
                        flowLayoutList.get(i).setPadding(45, 0, 0, 0);
                        flowLayoutList.get(i).setBackgroundResource(R.drawable.back_type16);
                    } else {
                        flowLayoutList.get(i).setPadding(45, 0, 0, 0);
                        flowLayoutList.get(i).setBackgroundResource(R.drawable.back_type16_2);
                    }
                }
            }

            @Override
            public void CustomTxtViewMokaleme(TextView textView) {
                textView.setTextColor(Color.BLACK);
                textView.setPadding(4, 4, 4, 4);
                if (textView.getTag().equals("1")) {
                    textView.setBackgroundResource(R.drawable.jakhali);
                }
            }

            @Override
            public void CustomLinearMainMokalemeHa(List<LinearLayout> linearLayoutList, ViewGroup.LayoutParams layoutParams) {
                for (int i = 0; i < linearLayoutList.size(); i++) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) linearLayoutList.get(i).getLayoutParams();
                    params.bottomMargin = 20;
                }
            }
        });
    }
}
