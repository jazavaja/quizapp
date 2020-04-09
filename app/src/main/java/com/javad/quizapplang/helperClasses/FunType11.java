package com.javad.quizapplang.helperClasses;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class FunType11 {
    private List<View> listJaKhaliha;
    private int positionEnable = 0;
    private fun11 fun11;
    private List<String> everyMokaleme;
    private int posEveryMokaleme = 0;

    List<Integer> integerList;
    int w = 0;

    private void addSeda(final Context context, FlowLayout upper, boolean isFisrt) {
        TextView t = new TextView(context);
        FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        t.setLayoutParams(layoutParams);
        t.setTag("3");
        fun11.CustomVoice(t);
        upper.addView(t);
        listJaKhaliha.add(t);
        integerList=new ArrayList<>();

    }
    public List<String> getJavab(){
        List<String> strings=new ArrayList<>();
        for (int i=0;i<listJaKhaliha.size();i++){
            if (listJaKhaliha.get(i).getTag().equals("1")){
                EditText editText= (EditText) listJaKhaliha.get(i);
                strings.add(editText.getText().toString());
            }
        }
        return strings;
    }

    public void setSoratSoal(final Context context, List<String> soratSoal, FlowLayout upper, List<String> javab, final fun11 fun11) {
        this.fun11 = fun11;
        listJaKhaliha = new ArrayList<>();
        everyMokaleme = new ArrayList<>();
        addSeda(context, upper, true);
        for (int i = 0; i < soratSoal.size(); i++) {
            TextView edittext = new TextView(context);
            if (soratSoal.get(i).equals("--")) {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                EditText editText=new EditText(context);
                editText.setLayoutParams(layoutParams);
                editText.setTag("1");
                editText.setText("    ");
                upper.addView(editText);
                listJaKhaliha.add(editText);
                everyMokaleme.add(javab.get(posEveryMokaleme));
            } else if (soratSoal.get(i).equals("##")) {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                edittext.setLayoutParams(layoutParams);
                edittext.setText("");
                edittext.setTag("0");
                upper.addView(edittext);
                listJaKhaliha.add(edittext);
                everyMokaleme.add(javab.get(posEveryMokaleme));
                posEveryMokaleme++;
                addSeda(context, upper, false);
            } else {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                edittext.setLayoutParams(layoutParams);
                edittext.setTag("0");
                edittext.setText(soratSoal.get(i));
                upper.addView(edittext);
                listJaKhaliha.add(edittext);
                everyMokaleme.add(javab.get(posEveryMokaleme));
//                fun11.CustomTextViewSoratSoal(listJaKhaliha,i);

//                fun11.CustomTextViewSoratSoal(listJaKhaliha, i,false);
            }
        }

        for (int i=0;i<listJaKhaliha.size();i++){
            fun11.CustomTextViewSoratSoal(listJaKhaliha,i);
        }

        for (int i = 0; i < soratSoal.size(); i++) {
            if (soratSoal.get(i).equals("--")) {
                positionEnable = i;
                fun11.SetEffectFirstPositionJakhali(listJaKhaliha.get(i));
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


        for (int i=0;i<listJaKhaliha.size();i++){
            if (listJaKhaliha.get(i).getTag().equals("1")){
                EditText editText= (EditText) listJaKhaliha.get(i);
                final int finalI = i;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        fun11.OnTextChanged(listJaKhaliha, finalI, integerList.get(finalI),s,start,before,count);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }

        for (int i = 0; i < listJaKhaliha.size(); i++) {
            final int finalI = i;
            listJaKhaliha.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listJaKhaliha.get(finalI).getTag().equals("1")) {
                        positionEnable = finalI;
                        fun11.WhenJakhaliClick(listJaKhaliha, finalI);
                        for (int i = 0; i < listJaKhaliha.size(); i++) {
                            fun11.SetEffectDisableJakhali(listJaKhaliha, i);
                        }
                        fun11.SetEffectWhenEnableJakhali((EditText) listJaKhaliha.get(positionEnable));
                    }
                    if (listJaKhaliha.get(finalI).getTag().equals("3")) {
                        fun11.WhenVoiceClick(listJaKhaliha, finalI, everyMokaleme);
                    }
                }
            });
        }

    }

    public interface fun11 {
        void SetEffectDisableJakhali(List<View> textView, int p);

        void SetEffectWhenEnableJakhali(EditText textView);

        void WhenJakhaliClick(List<View> textView, int t);

        void SetEffectFirstPositionJakhali(View textView);

        void CustomTextViewSoratSoal(List<View> listJaKhaliha, int i);

        void CustomVoice(View t);

        void WhenVoiceClick(List<View> listJaKhaliha, int position, List<String> everyMokaleme);

        void OnTextChanged(List<View> listJaKhaliha, int positionListJakhali, Integer integer, CharSequence s, int start, int before, int count);

    }

}
