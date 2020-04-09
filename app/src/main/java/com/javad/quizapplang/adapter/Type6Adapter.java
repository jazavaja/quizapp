package com.javad.quizapplang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.javad.quizapplang.helperClasses.OptionsSetSub;
import com.javad.quizapplang.R;
import com.javad.quizapplang.utils.Utils;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type6Adapter extends RecyclerView.Adapter<Type6Adapter.ViewHolder> {

    List<String> type1List = new ArrayList<>();
    RelativeLayout relativeLayout;
    Context context;
    LayoutInflater inflater;
    Utils utilss;
    String sex, subtitle,mainTitle;
    int paddingTop = 20;
    boolean first = true;
    final List<String> play1 = new ArrayList<>();
    int i = 0;


    public Type6Adapter(List<String> lessonModelsList, Context context, String sex, RelativeLayout relativeLayout
    ,String subtitle,String mainTitle) {
        this.type1List = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        utilss = new Utils(context);
        this.sex = sex;
        this.subtitle = subtitle;
        this.mainTitle = mainTitle;
        this.relativeLayout = relativeLayout;

    }

    @Override
    public Type6Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_type6_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type6Adapter.ViewHolder holder, final int position) {

        final String[] strings = type1List.get(position).split(":");


        holder.voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                utilss.tts(strings[1], 1,sex);

                int second = type1List.get(position).length();

                Log.e("textview = ", "blue"+"//size== "+ holder.flow_head.getChildCount());

                for (int j = 0; j < holder.flow_head.getChildCount(); j++) {
                    TextView view = (TextView) holder.flow_head.getChildAt(j);
                    view.setTextColor(Color.BLUE);
                }

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    for (int j = 0; j < holder.flow_head.getChildCount(); j++) {
                        TextView view = (TextView) holder.flow_head.getChildAt(j);
                        view.setTextColor(Color.BLACK);
                    }

                    }
                },second*80);

            }
        });

//        holder.txt_content.setText(type1List.get(position));

        //   گرفتن لیست
        changeColor(position, holder, Color.BLACK);

    }

    @Override
    public int getItemCount() {
        return type1List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView voice;
        TextView txt_content;
        FlowLayout payini, balayi, flow_head;

        public ViewHolder(View itemView) {
            super(itemView);

            voice  = itemView.findViewById(R.id.voice);
            txt_content = itemView.findViewById(R.id.txt_content);
            payini = itemView.findViewById(R.id.llMain);
            balayi = itemView.findViewById(R.id.balayi);
            flow_head = itemView.findViewById(R.id.flow_head);
        }
    }

    String[] sub2;
    private String addToZirnevisList(String[] subtitle,String[] title, int i){

        String addToListZirnevis="";

        for (int j = 0; j < subtitle.length; j++) {

            sub2 = subtitle[j].split("=");
            Log.e("sub == ", "sub0 == "+sub2[0]+" == title = "+title[i]);
            Log.e("index == ", "i == "+i+" == j"+j);
            if (sub2[0].contains(title[i].replace(",","").replace("،","").trim())) {

                addToListZirnevis = sub2[1].replace("/", "\n");
                Log.e("sub 1== ", addToListZirnevis);

                return addToListZirnevis;
            }

        }

        return "";
    }

    private void changeColor(int position, ViewHolder holder, final int color){

        List<String> zirnevis = new ArrayList<>();
        List<String> typeList = new ArrayList<>();
        List<String> surat_soal;

        String[] title = type1List.get(position).split(" ");

        String[] subtitles = subtitle.split("&");


        Log.e("subtitle == ", subtitles.length+"");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitles,title,i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }


        surat_soal = Arrays.asList(title);

        OptionsSetSub optionsSetSub = new OptionsSetSub();
        optionsSetSub.main(context,
                OptionsSetSub.ENGLISH ,OptionsSetSub.PERSIAN,
                typeList,
                zirnevis, surat_soal, holder.balayi, holder.payini, holder.flow_head,relativeLayout, 1,
                new OptionsSetSub.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(0, 0, 0, 0);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(color);
                    }

                    @Override
                    public void CustomTextViewBottom(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop,paddingTop,paddingTop,paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setBackgroundColor(Color.GRAY);
//                        String s = null;
//                        for (int i = 0; i < OptionsSetSub.getStrListTxtView(balayi, modeBottom).size(); i++) {
//                            s += OptionsSetSub.getStrListTxtView(balayi,modeBottom).get(i);
//                        }
//
//                        if (s.equals("")){
//                            disableCheck();
//                            Toast.makeText(activity, "disable", Toast.LENGTH_SHORT).show();
//                        }else {
//                            enableCheck();
//                            Toast.makeText(activity, "enable", Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );

    }
}
