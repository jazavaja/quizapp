package com.javad.quizapplang.helperClasses;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javad.quizapplang.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class FunType8And9 {
    private static final int WIDTH = 100;
    private funOpt8 funOpt8;
    private List<TextView> bottomList;
    private List<TextView> listTextViewSoratSoal;
    private List<String> everyMokaleme;
    private int positionEnable = -1;
    int id = 0;
    private int posEveryMokaleme = 0;

    public void main(Context context, List<String> soratSoal, List<String> gozineha, FlowLayout flowLayoutSoratSoal, List<String> listVoiceSpeak, FlowLayout flowLayoutBottom, funOpt8 fuuuun) {
        this.funOpt8 = fuuuun;
        setSoratSoal(context, soratSoal, flowLayoutSoratSoal, listVoiceSpeak, flowLayoutBottom);
        setGozineha(context, flowLayoutBottom, flowLayoutSoratSoal, gozineha, 0, fuuuun);
    }

    private void addSeda(final Context context, FlowLayout upper, boolean isFisrt) {
        TextView t = new TextView(context);
        FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        t.setLayoutParams(layoutParams);
        t.setTag("3");
        funOpt8.CustomVoice(t);
        upper.addView(t);
        listTextViewSoratSoal.add(t);

    }

    private void setSoratSoal(final Context context, List<String> soratSoal, FlowLayout upper, List<String> listVoiceSpeak, final FlowLayout bottom) {
        listTextViewSoratSoal = new ArrayList<>();
        everyMokaleme = new ArrayList<>();

        addSeda(context, upper, true);
//        int sec = -1;
        for (int i = 0; i < soratSoal.size(); i++) {
            TextView textView = new TextView(context);
            if (soratSoal.get(i).equals("--")) {
//                sec++;
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setBackgroundResource(R.drawable.shape);
                textView.setTag("1");
                upper.addView(textView);
                listTextViewSoratSoal.add(textView);
                everyMokaleme.add(listVoiceSpeak.get(posEveryMokaleme));
            } else if (soratSoal.get(i).equals("##")) {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setText("___________________________________________________________________________");
                textView.setMaxLines(1);
                textView.setTag("0");
                upper.addView(textView);
                listTextViewSoratSoal.add(textView);
                everyMokaleme.add(listVoiceSpeak.get(posEveryMokaleme));
                posEveryMokaleme++;

                addSeda(context, upper, false);

            } else {
                FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setTag("0");
                textView.setText(soratSoal.get(i));

                upper.addView(textView);
                listTextViewSoratSoal.add(textView);
                everyMokaleme.add(listVoiceSpeak.get(posEveryMokaleme));

//                funOpt8.CustomTextViewSoratSoal(listTextViewSoratSoal, i);
            }

        }
        for (int i = 0; i < listTextViewSoratSoal.size(); i++) {
            funOpt8.CustomTextViewSoratSoal(listTextViewSoratSoal, i);
        }


        for (int i = 0; i< listTextViewSoratSoal.size(); i++){
            if (listTextViewSoratSoal.get(i).getTag()=="1"){
                positionEnable=i;
                funOpt8.SetEffectFirstPositionJakhali(listTextViewSoratSoal.get(i));
                i= listTextViewSoratSoal.size()+2;
                break;
            }
        }
        for (int i = 0; i < listTextViewSoratSoal.size(); i++) {
            final int finalI = i;
            listTextViewSoratSoal.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.e("SS",listTextViewSoratSoal.get(finalI).getTag().toString());
                    if (listTextViewSoratSoal.get(finalI).getTag().equals("1")) {
                        positionEnable = finalI;
                        listTextViewSoratSoal.get(positionEnable).setLayoutParams(new LinearLayout.LayoutParams(WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
                        List<TextView> b = new ArrayList<>();
                        for (int i = 0; i < bottom.getChildCount(); i++) {
                            TextView t = (TextView) bottom.getChildAt(i);
                            if (t.getText().toString().equals(listTextViewSoratSoal.get(positionEnable).getText().toString())) {
                                b.add(t);
                            }
//                                b = t;
                        }
                        for (int i = 0; i < b.size(); i++) {
                            funOpt8.WhenJakhaliClick(listTextViewSoratSoal.get(positionEnable), b, i);
                        }

//                        if (b != null)
                        listTextViewSoratSoal.get(finalI).setText("");
                        for (int i = 0; i < listTextViewSoratSoal.size(); i++) {
                            funOpt8.SetEffectDisableJakhali(listTextViewSoratSoal, i);
                        }
                        funOpt8.SetEffectWhenEnableJakhali(listTextViewSoratSoal.get(positionEnable));
                    }
                    if (listTextViewSoratSoal.get(finalI).getTag().equals("3")) {
                        funOpt8.WhenVoiceClick(listTextViewSoratSoal, finalI, everyMokaleme);
                    }

                }
            });
        }
    }

    private void setGozineha(final Context context, final FlowLayout bottom, final FlowLayout flowSoratSoal, final List<String> gozineha, final int MAX, final funOpt8 funOpt8) {
        this.bottomList = new ArrayList<>();
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < gozineha.size(); i++) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(params);
            textView.setText(gozineha.get(i));
            textView.setTag("0");
            bottom.addView(textView);
            this.bottomList.add(textView);
            funOpt8.CunstomTextViewBottom(this.bottomList, i);
        }
        for (int i = 0; i < bottomList.size(); i++) {
            final int finalI = i;
            this.bottomList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i=0;i<bottomList.size();i++){
                        bottomList.get(i).setBackgroundColor(Color.WHITE);
                        bottomList.get(i).setTextColor(Color.BLACK);
                    }
                    if (!listTextViewSoratSoal.get(positionEnable).getText().toString().equals(""))
                        listTextViewSoratSoal.get(positionEnable).setText(bottomList.get(finalI).getText().toString());
                    else
                        listTextViewSoratSoal.get(positionEnable).setText(bottomList.get(finalI).getText().toString());
                    funOpt8.SetEffectWhenGozinehaClick(bottomList.get(finalI),listTextViewSoratSoal.get(positionEnable));

                    listTextViewSoratSoal.get(positionEnable).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
            });

        }

    }

    private boolean checkCount(FlowLayout upper, int MaxCount) {
        if (upper.getChildCount() == MaxCount)
            return false;
        else return true;
    }

    private boolean checkIf(FlowLayout bottom, FlowLayout upper, String s) {
        return false;
    }

    public interface funOpt8 {
        void CunstomTextViewBottom(List<TextView> textViews, int position);

        void CustomTextViewSoratSoal(List<TextView> textViews, int position);

        void SetEffectDisableJakhali(List<TextView> textViews, int position);

        void SetEffectWhenEnableJakhali(TextView textView);

        void SetEffectFirstPositionJakhali(TextView textView);

        void SetEffectWhenGozinehaClick(TextView gozine,TextView jakhali);

        void WhenJakhaliClick(TextView textView, List<TextView> b, int position);

        void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme);

//        void WhenVoiceClick(TextView textView);

        void CustomVoice(TextView t);
    }

    public static List<String> getStrJakhaliha(FlowLayout flowLayout) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            TextView textView = (TextView) flowLayout.getChildAt(i);
            if (textView.getTag().equals("1")) {
                list.add(textView.getText().toString());
            }
        }
        return list;
    }
}