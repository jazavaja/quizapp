package com.javad.quizapplang.helperClasses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class FunType10 {
    private List<TextView> listJaKhaliha;
    private int positionEnable = 0;
    private fun10 fun10;
    private List<String> everyMokaleme;
    private int posEveryMokaleme = 0;

    List<Integer> integerList;
    int w = 0;


    private int pppsss=0;
    private void addSeda(final Context context, FlowLayout upper, boolean isFisrt) {
        TextView t = new TextView(context);
        FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        t.setLayoutParams(layoutParams);
        t.setTag("3");
        fun10.CustomVoice(t);
        upper.addView(t);
        listJaKhaliha.add(t);

    }

    public void setSoratSoal(final Context context, List<String> soratSoal, FlowLayout upper, List<String> javab, final fun10 fun10) {
        this.fun10 = fun10;
        listJaKhaliha = new ArrayList<>();
        integerList=new ArrayList<>();
        everyMokaleme = new ArrayList<>();
        addSeda(context, upper, true);
        for (int i = 0; i < soratSoal.size(); i++) {
            TextView textView = new TextView(context);
            if (soratSoal.get(i).equals("--")) {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setTag("1");
                textView.setText("    ");
                upper.addView(textView);
                listJaKhaliha.add(textView);
                everyMokaleme.add(javab.get(posEveryMokaleme));
//                fun11.CustomTextViewSoratSoal(listJaKhaliha,i);
            }
            else if (soratSoal.get(i).equals("##")) {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setText("");
                textView.setTag("0");
                upper.addView(textView);
                listJaKhaliha.add(textView);
                try {
                    everyMokaleme.add(javab.get(posEveryMokaleme));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                posEveryMokaleme++;
//                fun11.CustomTextViewSoratSoal(listJaKhaliha,i);
                addSeda(context, upper, false);

//                fun11.CustomTextViewSoratSoal(listJaKhaliha, i,false);

            } else {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setTag("0");
                textView.setText(soratSoal.get(i));
                upper.addView(textView);
                listJaKhaliha.add(textView);
                try {
                    everyMokaleme.add(javab.get(posEveryMokaleme));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                fun11.CustomTextViewSoratSoal(listJaKhaliha,i);

//                fun11.CustomTextViewSoratSoal(listJaKhaliha, i,false);
            }
        }


        for (int i=0;i<listJaKhaliha.size();i++){
            fun10.CustomTextViewSoratSoal(listJaKhaliha,i);
        }

        for (int i = 0; i < soratSoal.size(); i++) {
            if (soratSoal.get(i).equals("--")) {
                positionEnable = i;
                fun10.SetEffectFirstPositionJakhali(listJaKhaliha.get(i));
                break;
            }
        }
        for (int i=0;i<listJaKhaliha.size();i++){
            if (listJaKhaliha.get(i).getTag().equals("1")) {
                integerList.add(w);
                w++;
            }
            else
                integerList.add(-1);
        }


        for (int i = 0; i < listJaKhaliha.size(); i++) {
            final int finalI = i;
            listJaKhaliha.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listJaKhaliha.get(finalI).getTag().equals("1")) {
                        positionEnable = finalI;
                        fun10.WhenJakhaliClick(listJaKhaliha, finalI);

                        fun10.WhenPO(listJaKhaliha,finalI,integerList.get(finalI));
                        for (int i = 0; i < listJaKhaliha.size(); i++) {
                            fun10.SetEffectDisableJakhali(listJaKhaliha, i);
                        }
                        fun10.SetEffectWhenEnableJakhali(listJaKhaliha.get(positionEnable));
                    }
                    else if (listJaKhaliha.get(finalI).getTag().equals("3")) {
                        fun10.WhenVoiceClick(listJaKhaliha, finalI, everyMokaleme);
                    }
                }
            });
        }
    }
    public List<String> getJavab(){
        List<String> strings=new ArrayList<>();
        for (int i=0;i<listJaKhaliha.size();i++){
            if (listJaKhaliha.get(i).getTag().equals("1")){
                TextView editText= (EditText) listJaKhaliha.get(i);
                strings.add(editText.getText().toString());
            }
        }
        return strings;
    }

    public interface fun10 {
        void SetEffectDisableJakhali(List<TextView> textView, int p);

        void SetEffectWhenEnableJakhali(TextView textView);

        void WhenJakhaliClick(List<TextView> textView, int t);

        void SetEffectFirstPositionJakhali(TextView textView);

        void CustomTextViewSoratSoal(List<TextView> listJaKhaliha, int i);

        void CustomVoice(TextView t);

        void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme);

        void WhenPO(List<TextView> listJaKhaliha, int pppsss, Integer integer);
    }

}
