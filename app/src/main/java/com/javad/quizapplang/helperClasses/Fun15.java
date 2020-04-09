package com.javad.quizapplang.helperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fun15 {
    Context context;
    private FlowLayout flowSoratSoal;
    private FlowLayout flowGozine;
    private List<Integer> whichKhatHaveJakhali;
    private List<List<String>> listSoratSoalHa;
    private List<List<String>> listListGozineha;
    private List<String> listStringForVoice;
    private funInterface funInterface;
    private List<String> javabHAa;
    private TextToSpeech textToSpeech;
    private int height = 100;
    private int wight = 200;
    //        در اینجا ویو هایی که اضافه میکنیم در کد هستند و در لیستی از ویو ها قرار میدیم
    private List<ImageView> adamakHa;
    private List<FlowLayout> flowLayoutList;
    private List<TextView> listTxtviewmokaleme;
    private List<TextView> listTxtViewgozine;
//            متغییر برای سمت چپ بودن یا راست بودن ادمک در هر سطر مکالمه زوج یعنی چپ فرد یعنی راست
    private int gravity = 0;
    private boolean reapetAddMokaleme = true;
    //    متغیر ها برای اندیس ارایه ها
    private int positionSatrGozineha = 0;
    private int positionSatrMokaleme = 0;
    private List<LinearLayout> linearLayoutList;
    private List<Integer> listKolTxtViewMokaleme;
    private int positionEnableJakhali = 0;
    private List<Integer> listPosJakhali;

    enum shy {Adamak, FlowLayout}
    public void main(Context context, FlowLayout soratSoalVajaKhaliha, FlowLayout Gozineha, List<List<String>> listSoratSoal, List<List<String>> listListGozineha, List<String> listStringForVoice, List<String> javabHa, funInterface funInterface) {
//        اتصال ورودی های تابع به متغیر های کلاس
        this.context = context;
        this.flowSoratSoal = soratSoalVajaKhaliha;
        this.flowGozine = Gozineha;
        this.listSoratSoalHa = listSoratSoal;
        this.listListGozineha = listListGozineha;
        this.listStringForVoice = listStringForVoice;
        this.funInterface = funInterface;
        this.javabHAa = javabHa;
        listPosJakhali = new ArrayList<>();
        adamakHa = new ArrayList<>();
        listTxtViewgozine = new ArrayList<>();
        listTxtviewmokaleme = new ArrayList<>();
        linearLayoutList = new ArrayList<>();
        whichKhatHaveJakhali = new ArrayList<>();
        listKolTxtViewMokaleme = new ArrayList<>();
        flowLayoutList = new ArrayList<>();
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        setPerSatrMokaleme();
        setGozineha();
    }

    //    Add kardan Gozineha
    private void setGozineha() {
//        Create First List Gozineha
        if (positionSatrGozineha < listListGozineha.size()) {
            List<String> thisGozineha = listListGozineha.get(positionSatrGozineha);
            ViewGroup.LayoutParams layoutParams = flowLayoutWrapWidthAndHeidth();
            for (int i = 0; i < thisGozineha.size(); i++)
            {
                TextView textView = new TextView(context);
                textView.setText(thisGozineha.get(i));
                textView.setLayoutParams(layoutParams);
                flowGozine.addView(textView);
                listTxtViewgozine.add(textView);
            }

//        Custom TextView haye Gozineha
            for (int i = 0; i < listTxtViewgozine.size(); i++)
            {
                funInterface.CustomTxtViewGozineha(listTxtViewgozine, i, layoutParams);
            }
//        Click on gozine
            methodClickGozine();

        }
    }
    public void methodClickGozine(){
        for (int i = 0; i < listTxtViewgozine.size(); i++)
        {
            final int finalI = i;
            listTxtViewgozine.get(i).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    TextView txtViewJakhaliEnableMokaleme = listTxtviewmokaleme.get(listKolTxtViewMokaleme.get(listPosJakhali.get(positionEnableJakhali)));
                    disableGozine(listTxtViewgozine.get(finalI));
                    funInterface.SetonclickGozine(listTxtViewgozine.get(finalI), txtViewJakhaliEnableMokaleme);
                    baressiGozineTrueOrfalse(txtViewJakhaliEnableMokaleme,listTxtViewgozine);
                    Log.e("ss",txtViewJakhaliEnableMokaleme.getText().toString());



//                        if (listTxtViewgozine.get(finalI).getText().toString().equals(javabHAa.get(positionEnableJakhali))) {
//                            funInterface.WhenCorrectGozine(txtViewJakhaliEnableMokaleme);
////                        Shart barresi tedadPositionJakhali
//                            Log.e("JAKHAAALI", "GozineHaPositionKHast: " + listListGozineha.get(positionSatrGozineha) + "    %   GozinehaSize" + listListGozineha.size());
//                            if (positionSatrGozineha < listListGozineha.size()) {
//                                flowGozine.removeAllViews();
//                                positionSatrGozineha++;
//                                setGozineha();
//                                positionEnableJakhali++;
//                                positionSatrMokaleme++;
//                                setPerSatrMokaleme();
//                            }
//
//                        }
//                        else {
//                            funInterface.WhenGozineFalse(txtViewJakhaliEnableMokaleme);
//                        }
                }
            });
        }
    }
    private void baressiGozineTrueOrfalse(TextView jakhaliEnable,List<TextView> gozineha)
    {
//        Baresi tedad gozine barabar ba jakhali k bayad alan javab tst beshe
        Log.e("BARESI",jakhaliEnable.getText().toString().length()+"   **   "+gozineha.size());
        if (jakhaliEnable.getText().toString().length()==flowGozine.getChildCount())
        {
//            Shart doros ya ghalat bodan gozine entekhabi\
            if (jakhaliEnable.getText().toString().equals(javabHAa.get(positionEnableJakhali)))
            {
//                doros j bashe
                funInterface.WhenCorrectGozine(jakhaliEnable);
//                Shart barresi tedadPositionJakhali
                if (positionSatrGozineha < listListGozineha.size()) {
                    flowGozine.removeAllViews();
                    positionSatrGozineha++;
                    setGozineha();
                    positionEnableJakhali++;
                    positionSatrMokaleme++;
                    setPerSatrMokaleme();
                }
            }
            else
            {
                funInterface.WhenGozineFalse(jakhaliEnable);
            }

        }
    }

    public void disableGozine(TextView textView){
        textView.setEnabled(false);
    }

    private void setOnClickAdamak() {
        for (int i = 0; i < adamakHa.size(); i++) {
            final int finalI = i;
            adamakHa.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> strings = listSoratSoalHa.get(finalI);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).equals("--")) {
                            stringBuilder.append(javabHAa.get(findWhichMokalemeWannaVoice(i))).append(" ");
                        } else {
                            if (strings.get(i).equals("@@")) {
                                stringBuilder.append("");
                            } else {
                                stringBuilder.append(strings.get(i)).append(" ");
                            }
                        }
                    }
                    textToSpeech.speak(stringBuilder.toString(), TextToSpeech.QUEUE_ADD, null);
                    Log.e("VOICE", stringBuilder.toString());
                }
            });
        }
    }

    private int findWhichMokalemeWannaVoice(int ii) {
        for (int i = 0; i < whichKhatHaveJakhali.size(); i++) {
            if (whichKhatHaveJakhali.get(i) == ii) {
                return i;
            }
        }
//        return 0;
        return 0;
    }

    private void setOnclickJakhali() {
        for (int i = 0; i < listTxtviewmokaleme.size(); i++) {
            final int finalI = i;
            listTxtviewmokaleme.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("POPOPO", positionEnableJakhali + "");
                    if (finalI == listPosJakhali.get(positionEnableJakhali)) {
                        TextView textView = listTxtviewmokaleme.get(finalI);
                        if (textView.getTag().equals("1")) {
                            try
                            {
                                String txtTextView=textView.getText().toString();
                                enableGozine(txtTextView.substring(txtTextView.length()-1),listTxtViewgozine);
                                textView.setText(txtTextView.substring(0,txtTextView.length()-1));
                                funInterface.SetOnClickJakhali(textView, listTxtViewgozine);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public void enableGozine(String jakhali,List<TextView> ligozine){
        for (int i=0;i<ligozine.size();i++){
            if (ligozine.get(i).getText().toString().equals(jakhali)){
                ligozine.get(i).setEnabled(true);
            }
        }
    }

    //        اضافه کردن اولین سطر مکالمه
    @SuppressLint("RtlHardcoded")
    private void setPerSatrMokaleme() {
//        Check Age Az Tedad Balater Nazane
        if (positionSatrMokaleme < listSoratSoalHa.size()) {
            //       اضافه کردن یک لایه به فلو لی اوت برای اضافه کردن عکس و متن ها هر مکالمه
            FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            LinearLayout linearMain = new LinearLayout(context);
            linearMain.setLayoutParams(layoutParams);
            flowSoratSoal.addView(linearMain);
            doing(linearMain);
            linearLayoutList.add(linearMain);
            funInterface.CustomLinearMainMokalemeHa(linearLayoutList, layoutParams);
        }
        setOnclickJakhali();

        if (positionSatrMokaleme == listSoratSoalHa.size()) {
            funInterface.WhenDone();
            Log.e("POSSATRMOKALEME", positionSatrMokaleme + "     *   " + listSoratSoalHa.size());
            Log.e("j", "tamaaaaaaaaam");
        }
//        Shart Akharin Satr Mokaleme
    }

    //    set kardan Adamak o Satr mokaleme bar asase Mokaleme bodan(mesle chat...avali samt chap va dovomi rast)
    private void doing(LinearLayout linearMain) {
        if (gravity % 2 == 0) {
            addImgAdamak(linearMain);
//        در اینجا میخواهیم لایه متن ها را اضافه کنیم
            addTextViewFromListSoratSoal(linearMain);
        } else {
            addTextViewFromListSoratSoal(linearMain);
            addImgAdamak(linearMain);
//        در اینجا میخواهیم لایه متن ها را اضافه کنیم
        }
//        positionSatrMokaleme++;
    }

    private void addImgAdamak(LinearLayout frameLayout) {
        //        در اینجا ادمک رو اضافه میکنیم
        ImageView adamak = new ImageView(context);
        adamak.setLayoutParams(customLayoutParamFlow
                (100, 50, shy.Adamak));
        frameLayout.addView(adamak);
        adamakHa.add(adamak);

        funInterface.CustomViewAdamak(adamakHa);
        gravity++;

        setOnClickAdamak();
    }

    @SuppressLint("SetTextI18n")
    private void addTextViewFromListSoratSoal(LinearLayout main) {
        reapetAddMokaleme = true;
        FlowLayout flowLayout = new FlowLayout(context);
        ViewGroup.LayoutParams layoutParams = customLayoutParamFlow(FlowLayout.LayoutParams.MATCH_PARENT, FlowLayout.LayoutParams.WRAP_CONTENT, shy.FlowLayout);
        flowLayout.setLayoutParams(layoutParams);
        main.addView(flowLayout);

        for (int i = 0; i < listSoratSoalHa.get(positionSatrMokaleme).size(); i++) {
            TextView textView = new TextView(context);

            listKolTxtViewMokaleme.add(listKolTxtViewMokaleme.size());
            if (listSoratSoalHa.get(positionSatrMokaleme).get(i).equals("--")) {
                reapetAddMokaleme = false;
                whichKhatHaveJakhali.add(i);
                textView.setLayoutParams(flowLayoutCustom(wight, height));
                listPosJakhali.add(listKolTxtViewMokaleme.size() - 1);
                textView.setText("");
                textView.setTag("1");
            } else if (listSoratSoalHa.get(positionSatrMokaleme).get(i).equals("@@")) {
                textView.setLayoutParams(flowLayoutCustom(FlowLayout.LayoutParams.WRAP_CONTENT, height));
                textView.setText("\n" + listSoratSoalHa.get(positionSatrMokaleme).get(i).replace("@@", ""));
                textView.setTag("2");
            } else {
                textView.setLayoutParams(flowLayoutCustom(FlowLayout.LayoutParams.WRAP_CONTENT, height));
                textView.setText(listSoratSoalHa.get(positionSatrMokaleme).get(i));
                textView.setTag("0");
            }
            funInterface.CustomTxtViewMokaleme(textView);
            flowLayout.addView(textView);
            listTxtviewmokaleme.add(textView);
        }
        flowLayoutList.add(flowLayout);
        funInterface.customFlowLayoutMokaleme(flowLayoutList);

        playSatrMokaleme();

        if (reapetAddMokaleme) {
            reapetAddMokaleme = true;
            positionSatrMokaleme++;
            setPerSatrMokaleme();
        }

        for (int i = 0; i < listPosJakhali.size(); i++) {
            Log.e("EWEWE", listPosJakhali.get(i) + "");
        }

    }

    private void playSatrMokaleme() {
        List<String> soratSoal = listSoratSoalHa.get(positionSatrMokaleme);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < soratSoal.size(); i++) {
            stringBuilder.append(soratSoal.get(i)).append(" ");
        }
        Log.e("SORATSOAL", stringBuilder.toString());
        textToSpeech.speak(stringBuilder.toString(), TextToSpeech.QUEUE_ADD, null);
    }

    private ViewGroup.LayoutParams flowLayoutWrapWidthAndHeidth() {
        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return layoutParams;
    }

    private static ViewGroup.LayoutParams flowLayoutCustom(int width, int heidth) {
        return new FlowLayout.LayoutParams(width, heidth);
    }

    public ViewGroup.LayoutParams flowLayoutWrapWidthFillHeidth() {
        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return layoutParams;
    }

    public ViewGroup.LayoutParams wrapFrameLayout() {
        //        if (gravityAdamak % 2 == 0)
//            frameLayout.gravity = Gravity.LEFT;
//        else
//            frameLayout.gravity = Gravity.RIGHT;
//        gravityAdamak++;
        return new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private ViewGroup.LayoutParams customLayoutParamFlow(int width, int heidth, shy shy) {
        LinearLayout.LayoutParams frameLayout = new LinearLayout.LayoutParams(width, heidth, 0.9f);
        return frameLayout;
    }

    public interface funInterface {
        public void WhenCorrectGozine(TextView textView);

        void CustomTextViewMokaleme(List<TextView> textView, int pos);

        void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception;

        void SetonclickGozine(TextView txtViewGozine, TextView txtViewJakhaliEnable);

        void WhenGozineFalse(TextView t);

        void CustomTxtViewGozineha(List<TextView> lstTxtViewGozine, int i, ViewGroup.LayoutParams layoutParams);

        void WhenDone();

        void CustomVoice(TextView t);

        void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme);

        void CannotAddNewGozine();

        void CustomViewAdamak(List<ImageView> adamakHa);

        void customFlowLayoutMokaleme(List<FlowLayout> flowLayoutList);

        void CustomTxtViewMokaleme(TextView textView);

        void CustomLinearMainMokalemeHa(List<LinearLayout> linearLayoutList, ViewGroup.LayoutParams layoutParams);
    }
}