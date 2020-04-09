package com.javad.quizapplang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.javad.quizapplang.App;
import com.javad.quizapplang.MainFragment;
import com.javad.quizapplang.QuestionsActivity;
import com.javad.quizapplang.R;
import com.javad.quizapplang.Type9Model;
import com.javad.quizapplang.model.dbFlow.questions.QAnswers;
import com.javad.quizapplang.model.dbFlow.questions.QFullText;
import com.javad.quizapplang.utils.General;
import com.javad.quizapplang.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type91011RecycleAdapter extends RecyclerView.Adapter<Type91011RecycleAdapter.ViewHolder> {

    List<Type9Model> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    int layout;
    ListenerMic onClickMic;
    public static int pos = -1;
    public static String card = "";
    public static String selected = "";
    public static String selected2 = "";
    List<QAnswers> qAnswers = new ArrayList<>();
    List<QFullText> qFullTexts = new ArrayList<>();
    int state;
    Utils utils;
    String[] strings = {""};
    String etAns1 , etAns2;
    public static String[][] arrayAns;

    public interface ListenerMic {
        void onClickMic1(RecyclerView.ViewHolder viewHolder, int position);
        void onClickMic2(RecyclerView.ViewHolder viewHolder, int position);
        void onClickEditText(RecyclerView.ViewHolder viewHolder, int position, int ans);
        void onClickAddTolist(RecyclerView.ViewHolder viewHolder, int position, int hpos);
    }

    public Type91011RecycleAdapter(List<QAnswers> qAnswers, List<QFullText> qFullTexts, List<Type9Model> list, Context context, int state, int layout
            , ListenerMic onClickMic) {
        this.layout = layout;
        this.state = state;
        this.qAnswers = qAnswers;
        this.qFullTexts = qFullTexts;
        this.list = list;
        this.context = context;
        this.onClickMic = onClickMic;
        this.inflater = LayoutInflater.from(context);
        utils = new Utils(context);
    }

    @Override
    public Type91011RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(layout, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type91011RecycleAdapter.ViewHolder holder, final int position) {

        arrayAns = new String[list.size()][3];
        holder.txt_num.setText(list.get(position).getName());
        holder.txt1.setText(list.get(position).getStr1());
        holder.txt2.setText(list.get(position).getStr2());

        holder.volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = qFullTexts.get(position).getFulltext();
                String[] strings = s.split(":");
                utils.tts(strings[1],1,"m");

            }
        });

        Log.e("content adapter = ", list.get(position).getContent().trim());
        Log.e("name ======= = ", list.get(position).getName().trim());

        if (list.get(position).getContent().trim().startsWith("-")) {
            holder.txt0.setVisibility(View.GONE);
            Log.e("txt0 = ", "goneee");
        } else {
            Log.e("txt0 = ", "visible");
            holder.txt0.setVisibility(View.VISIBLE);
            holder.txt1.setVisibility(View.GONE);
            holder.txt0.setText(list.get(position).getStr1());
        }

        Log.e("card ", card+"");
        Log.e("pos ", pos+"");


        /** types  ============================= */

        if (layout == R.layout.row_type9_1) {

            MainFragment.selectType = 9;
//            Toasty.info(App.context,"ابتدا کادر مورد نظر را انتخاب کنید").show();

            if (pos == -1) {

                holder.txt_card1.setText(list.get(position).getCard1());
                holder.txt_card2.setText(list.get(position).getCard2());
                String[] answer = qAnswers.get(0).getAnswers().split(",");

                    if (holder.txt_card1.getText().toString().equals("exist")) {

                        if (state == 1){

                            holder.txt_card1.setText(answer[0]);

                        }else {

                            holder.txt_card1.setText("...");
                        }
                    }

                    if (holder.txt_card2.getText().toString().equals("exist")) {

                        if (state == 1){

                            holder.txt_card2.setText(answer[1]);

                        }else {

                            holder.txt_card2.setText("...");
                        }
                    }

                MainFragment.select9List.add("");

                }

                if (holder.txt_num.getText().length() > 2) {
                    holder.txt1.setText(list.get(position).getStr2());
                    holder.txt2.setVisibility(View.GONE);
                    holder.card2.setVisibility(View.GONE);
                }

                if (holder.txt_card1.getText().toString().trim().equals("")) {
                    holder.card1.setVisibility(View.GONE);
                } else {
                    holder.card1.setVisibility(View.VISIBLE);
                }

                if (holder.txt_card2.getText().toString().trim().equals("")) {
                    holder.card2.setVisibility(View.GONE);
                } else {
                    holder.card2.setVisibility(View.VISIBLE);
                }

                if (pos == position){

                    if (card.equals("c1")){

                        if (selected != null) {

                            holder.txt_card1.setText(selected.replace("/"," "));

                        }

                    }else if (card.equals("c2")){

                        if (selected2 != null) {

                            holder.txt_card2.setText(selected2.replace("/"," "));

                        }
                    }
                }

                holder.card1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        pos = position;
                        card = "c1";
                        MainFragment.posCard = 1;
                        selected = "...";
                        holder.txt_card1.setText(selected);
                        holder.txt_card1.setBackgroundResource(R.drawable.shape_select);
                        holder.txt_card2.setBackgroundResource(R.drawable.shape);
                        Log.e("card ", card+"");
                        Log.e("pos ", pos+"");
                        onClickMic.onClickAddTolist(holder,position, 1);

                    }
                });

                holder.card2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        pos = position;
                        card = "c2";
                        MainFragment.posCard = 2;
                        selected2 = "...";
                        holder.txt_card2.setText(selected2);
                        holder.txt_card1.setBackgroundResource(R.drawable.shape);
                        holder.txt_card2.setBackgroundResource(R.drawable.shape_select);
                        Log.e("card ", card+"");
                        Log.e("pos ", pos+"");
                        onClickMic.onClickAddTolist(holder,position, 2);

                    }
                });

        }else if (layout == R.layout.row_type10){

            type10(holder, position);

        }else if (layout == R.layout.row_type11) {

            MainFragment.selectType = 11;

            if (!(list.get(position).getContent().trim().contains("-"))) {
                holder.card1.setVisibility(View.GONE);
                holder.card2.setVisibility(View.GONE);
                holder.txt2.setText("");
            }else {
                strings = list.get(position).getContent().split("#");

                if (strings.length == 3){

                    etAns1 = strings[1];
                    arrayAns[position][1] = etAns1;
                    etAns2 = strings[2];
                    arrayAns[position][2] = etAns1;

                }else if (strings.length == 2){
                    etAns1 = strings[1];
                    arrayAns[position][1] = etAns1;
                }

                for (int i = 0; i < strings.length; i++) {
                    Log.e("strAnswersssssss = ", strings[i]);
                }
            }

            if (list.get(position).getCard1().equals("exist")) {
                holder.et1.setHint("کلمه مورد نظر را وارد کنید");
            } else {
                holder.et1.setVisibility(View.GONE);
            }

            if (list.get(position).getCard2().equals("exist")) {
                holder.et2.setHint("کلمه مورد نظر را وارد کنید");
            } else {
                holder.et2.setVisibility(View.GONE);
            }

            holder.et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (hasFocus){

                        pos = position;
                        card = "c1";
                        selected = "";
                        holder.et1.setText(selected);
                        holder.et1.setBackgroundResource(R.drawable.shape_select);
                        holder.et1.setBackgroundResource(R.drawable.shape);
                        Log.e("card ", card+"");
                        Log.e("pos ", pos+"");

                        Log.e("focus", "focus");
                        Log.e("focus", selected);

                    }
                }
            });

            holder.et1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                    selected = holder.et1.getText().toString();
                    Log.e("unFocus", "unFocus");
                    Log.e("unFocus", selected);
                    holder.et1.setBackgroundResource(R.drawable.shape_select);
                    onClickMic.onClickEditText(holder,0, 1);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            holder.et2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    selected = holder.et2.getText().toString();
                    Log.e("unFocus et", "unFocus");
                    Log.e("unFocus", selected);
                    holder.et2.setBackgroundResource(R.drawable.shape_select);
                    onClickMic.onClickEditText(holder,1, 2);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            holder.et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (hasFocus){

                        pos = position;
                        card = "c2";
                        selected = "";
                        holder.et2.setText(selected);
                        holder.et2.setBackgroundResource(R.drawable.shape_select);
                        holder.et2.setBackgroundResource(R.drawable.shape);
                        Log.e("card ", card+"");
                        Log.e("pos ", pos+"");

                    }
                }
            });

        }else if (layout == R.layout.row_type13){

            for (int i = 0; i < list.size(); i++) {
                Log.e("string list = ", list.get(i).getContent());
            }

            MainFragment.selectType = 13;
//            Toasty.info(App.context,"ابتدا کادر مورد نظر را انتخاب کنید").show();

            if (pos == -1) {

                holder.txt_card1.setText(list.get(position).getCard1());
                holder.txt_card2.setText(list.get(position).getCard2());
                String[] answer = qAnswers.get(0).getAnswers().split(",");

                if (holder.txt_card1.getText().toString().equals("exist")) {

                    if (state == 1){

                        holder.txt_card1.setText(answer[0]);

                    }else {

                        holder.txt_card1.setText("...");
                    }
                }

                if (holder.txt_card2.getText().toString().equals("exist")) {

                    if (state == 1){

                        holder.txt_card2.setText(answer[1]);

                    }else {

                        holder.txt_card2.setText("...");
                    }
                }

                MainFragment.select9List.add("");
                MainFragment.select9List.add("");

            }

            if (holder.txt_num.getText().length() > 2) {
                holder.txt1.setText(list.get(position).getStr2());
                holder.txt2.setVisibility(View.GONE);
                holder.card2.setVisibility(View.GONE);
            }

            if (holder.txt_card1.getText().toString().trim().equals("")) {
                holder.card1.setVisibility(View.GONE);
            } else {
                holder.card1.setVisibility(View.VISIBLE);
            }

            if (holder.txt_card2.getText().toString().trim().equals("")) {
                holder.card2.setVisibility(View.GONE);
            } else {
                holder.card2.setVisibility(View.VISIBLE);
            }

            if (pos == position){

                if (card.equals("c1")){

                    if (selected != null) {

                        holder.txt_card1.setText(selected);

                    }

                }else if (card.equals("c2")){

                    if (selected != null) {

                        holder.txt_card2.setText(selected);

                    }
                }
            }

            holder.card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pos = position;
                    card = "c1";
                    selected = "...";
                    holder.txt_card1.setText(selected);
                    holder.txt_card1.setBackgroundResource(R.drawable.shape_select);
                    holder.txt_card2.setBackgroundResource(R.drawable.shape);
                    Log.e("card ", card+"");
                    Log.e("pos ", pos+"");
//                    onClickMic.onClickAlphabetList(holder,1);

                }
            });

            holder.card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pos = position;
                    card = "c2";
                    selected = "...";
                    holder.txt_card2.setText(selected);
                    holder.txt_card1.setBackgroundResource(R.drawable.shape);
                    holder.txt_card2.setBackgroundResource(R.drawable.shape_select);
                    Log.e("card ", card+"");
                    Log.e("pos ", pos+"");
//                    onClickMic.onClickAlphabetList(holder,1);

                }
            });

        }

//            QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    final String str1 = holder.et1.getText().toString();
//                    final String str2 = holder.et2.getText().toString();
//
//                    posList.add(str1);
//                    posList.add(str2);
//                    String content = "", answer = "";
//
//                    for (int i = 0; i < posList.size(); i++) {
//                        content = " "+posList.get(i).trim();
//                        answer = " "+qAnswers.get(i).getAnswers().trim();
//                        String[] str = answer.split("/");
//
//                        if (str.length > 1){
//
//                            Log.e("content 0= ", "answer =" + answer +"// content = "+ content);
//
//                            for (int j = 0; j < str.length; j++) {
//                                if (answer.contains(str[j])) {
//                                    answer = str[j];
//
//                                }else {
//
//                                    General.wrongCToasty(context);
//
//                                }
//                            }
//                        }else {
//
//                            Log.e("content 1= ", "answer =" + answer +"// content = "+ content);
//
//                            if (answer.equals(content)){
//
//                                General.correctCToasty(context);
//
//                            }else {
//
//                                General.wrongCToasty(context);
//
//                            }
//                        }
//                    }
//                }
//            });

    }

    private void type10(final ViewHolder holder, final int position){
        MainFragment.selectType = 10;

        if (pos == -1) {

            holder.txt_card1.setText(list.get(position).getCard1());
            holder.txt_card2.setText(list.get(position).getCard2());
            String[] answer = qAnswers.get(0).getAnswers().split(",");

            MainFragment.select9List.add("");
            MainFragment.select9List.add("");

        }

        if (holder.txt_num.getText().length() > 2) {
            holder.txt1.setText(list.get(position).getStr2());
            holder.txt2.setVisibility(View.GONE);
            holder.card2.setVisibility(View.GONE);
        }


        if (pos == position){

            if (card.equals("c1")){

                if (selected != null) {

                    holder.txt_card1.setText(selected);

                }

            }else if (card.equals("c2")){

                if (selected != null) {

                    holder.txt_card2.setText(selected);

                }

            }

        }

        if (list.get(position).getCard1().equals("exist")) {

            holder.mic1.setVisibility(View.VISIBLE);

        }else {

            holder.mic1.setVisibility(View.GONE);

        }

        if (list.get(position).getCard2().equals("exist")) {

            holder.mic2.setVisibility(View.VISIBLE);

        }else {

            holder.mic2.setVisibility(View.GONE);

        }

        MainFragment.select9List.add("");
        MainFragment.select9List.add("");

        holder.mic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                card = "c1";
                selected = "...";
                holder.txt_card1.setText(selected);
                holder.txt_card1.setBackgroundResource(R.drawable.shape_select);
                holder.txt_card2.setBackgroundResource(R.drawable.shape);
                Log.e("card ", card+"");
                Log.e("pos ", pos+"");

                onClickMic.onClickMic1(holder,position);

            }
        });

        holder.mic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                card = "c2";
                selected = "...";
                holder.txt_card2.setText(selected);
                holder.txt_card1.setBackgroundResource(R.drawable.shape);
                holder.txt_card2.setBackgroundResource(R.drawable.shape_select);
                Log.e("card ", card+"");
                Log.e("pos ", pos+"");

                onClickMic.onClickMic2(holder,position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_num, txt_card1, txt2, txt_card2, txt1, txt0;
        FrameLayout card1, card2, mic1, mic2;
        EditText et1, et2;
        FrameLayout volume;

        public ViewHolder(View itemView) {
            super(itemView);

            volume = itemView.findViewById(R.id.volume);
            txt0 = itemView.findViewById(R.id.txt0);
            txt_num = itemView.findViewById(R.id.txt_name);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt_card1 = itemView.findViewById(R.id.txt_card1);
            txt_card2 = itemView.findViewById(R.id.txt_card2);
            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
            mic1 = itemView.findViewById(R.id.mic1);
            mic2 = itemView.findViewById(R.id.mic2);
            et1 = itemView.findViewById(R.id.et1);
            et2 = itemView.findViewById(R.id.et2);

        }
    }



}
