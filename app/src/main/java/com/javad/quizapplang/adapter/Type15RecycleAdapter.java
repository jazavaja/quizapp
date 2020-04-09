package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.javad.quizapplang.MainFragment;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.Type12Model;
import com.javad.quizapplang.model.Type15;
import com.javad.quizapplang.model.dbFlow.questions.QAnswers;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type15RecycleAdapter extends RecyclerView.Adapter<Type15RecycleAdapter.ViewHolder> {

    List<Type15> list = new ArrayList<>();
    List<QAnswers> qAnswers = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    Type91011RecycleAdapter.ListenerMic onClickMic;
    public static String card = "";
    public static String selected = "";
    public static String selected2 = "";
    public static int pos = -1;
    int state;
    public static int row = 0;

    public Type15RecycleAdapter(Context context, List<Type15> lessonModelsList, List<QAnswers> qAnswers, int state,
                                Type91011RecycleAdapter.ListenerMic onClickMic) {
        this.list = lessonModelsList;
        this.context = context;
        this.qAnswers = qAnswers;
        this.state = state;
        this.onClickMic = onClickMic;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public Type15RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_type15, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type15RecycleAdapter.ViewHolder holder, final int position) {

//        if (holder.txt_card1.getText().toString().equals("exist")
//                || holder.txt_card2.getText().toString().equals("exist")) {

            holder.txt1.setText(list.get(position).getName());
            holder.txt2.setText(list.get(position).getStr1());
            holder.txt3.setText(list.get(position).getStr2());

            Log.e("content adapter = ", list.get(position).getContent().trim());
            Log.e("name ======= = ", list.get(position).getName().trim());

            if (list.get(position).getContent().trim().startsWith("-")) {
                holder.txt1.setVisibility(View.GONE);
                Log.e("txt0 = ", "goneee");
            } else {
                Log.e("txt0 = ", "visible");
                holder.txt1.setVisibility(View.VISIBLE);
                holder.txt2.setVisibility(View.GONE);
                holder.txt1.setText(list.get(position).getStr1());
            }

//        Log.e("card ", card+"");
            Log.e("pos ", pos + "");


            /** types  ============================= */

//            MainFragment.selectType = 9;
//            Toasty.info(App.context,"ابتدا کادر مورد نظر را انتخاب کنید").show();

            if (pos == -1) {

                holder.txt_card1.setText(list.get(position).getCard1());
                holder.txt_card2.setText(list.get(position).getCard2());
                String[] answer = qAnswers.get(0).getAnswers().split(",");

                if (holder.txt_card1.getText().toString().equals("exist")) {

                    Log.e("txt card 1", "exiiiiiiiiiiist");
                    if (state == 1) {

                        holder.txt_card1.setText(answer[0]);

                    } else {

                        holder.txt_card1.setText("...");
                    }
                }

                if (holder.txt_card2.getText().toString().equals("exist")) {
                    Log.e("txt card 2", "exiiiiiiiiiiist");
                    if (state == 1) {

                        holder.txt_card2.setText(answer[1]);

                    } else {

                        holder.txt_card2.setText("...");
                    }
                }

                MainFragment.select9List.add("");

            }


            if (list.get(position).getCard1().equals("")) {
                holder.card1.setVisibility(View.GONE);
            } else {
                holder.card1.setVisibility(View.VISIBLE);
            }

            if (holder.txt_card2.getText().toString().trim().equals("")) {
                holder.card2.setVisibility(View.GONE);

                if (list.get(position).getStr2().equals("")){
                    holder.txt2.setVisibility(View.VISIBLE);
                    holder.txt2.setText(list.get(position).getStr2());
                    holder.txt3.setVisibility(View.GONE);
                }

            } else {
                holder.card2.setVisibility(View.VISIBLE);
            }

            if (pos == position) {

                if (card.equals("c1")) {

                    if (selected != null) {

                        holder.txt_card1.setText(selected.replace("/", " "));

                    }

                } else if (card.equals("c2")) {

                    if (selected2 != null) {

                        holder.txt_card2.setText(selected2.replace("/", " "));

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
                    Log.e("card ", card + "");
                    Log.e("pos ", pos + "");
//                    MainFragment.gridTempPos = position;
                    onClickMic.onClickAddTolist(holder, position, 1);

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
                    Log.e("card ", card + "");
                    Log.e("pos ", pos + "");
                    onClickMic.onClickAddTolist(holder, position, 2);

                }
            });

//        }else {
//
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt1,txt2, txt3, txt_card1, txt_card2;
        FrameLayout card1, card2;

        public ViewHolder(View itemView) {
            super(itemView);

            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
            txt_card1 = itemView.findViewById(R.id.txt_card1);
            txt_card2 = itemView.findViewById(R.id.txt_card2);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);

        }
    }

}
