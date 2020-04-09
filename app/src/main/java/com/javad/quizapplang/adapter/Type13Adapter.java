package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.MainFragment;
import com.javad.quizapplang.QuestionsActivity;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.Type12Model;
import com.javad.quizapplang.model.dbFlow.questions.QAnswers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type13Adapter extends RecyclerView.Adapter<Type13Adapter.ViewHolder> {

    List<Type12Model> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    OnClickListener onClickListener;
    List<QAnswers> qAnswers = new ArrayList<>();


    public static int pos = -1;
    public static String card = "";
    public static String selected = "";
    int state;

    public interface OnClickListener {
        void onClickType13(RecyclerView.ViewHolder viewHolder, int position);
    }

    public Type13Adapter(Context context, List<QAnswers> qAnswers, List<Type12Model> lessonModelsList, int state,
                         OnClickListener clickListener) {
        this.list = lessonModelsList;
        this.qAnswers = qAnswers;
        this.state = state;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = clickListener;
    }

    public interface OnClickAdapterType2{
        void listener(int position, RecyclerView.ViewHolder viewHolder);
    }

    @Override
    public Type13Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_type13, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type13Adapter.ViewHolder holder, final int position) {

        holder.txt_num.setText(list.get(position).getNum());
        holder.txt1.setText(list.get(position).getStr1());
        holder.txt2.setText(list.get(position).getStr2());

        Log.e("content adapter = ", list.get(position).getContent().trim());

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
                    selected = ",,,";
                    holder.txt_card1.setText(selected);
                    holder.txt_card1.setBackgroundResource(R.drawable.shape_select);
                    holder.txt_card2.setBackgroundResource(R.drawable.shape);
                    Log.e("card ", card+"");
                    Log.e("pos ", pos+"");
                    selected = "";
                    onClickListener.onClickType13(holder,1);

                }
            });

            holder.card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pos = position;
                    card = "c2";
                    selected = ",,,";
                    holder.txt_card2.setText(selected);
                    holder.txt_card1.setBackgroundResource(R.drawable.shape);
                    holder.txt_card2.setBackgroundResource(R.drawable.shape_select);
                    Log.e("card ", card+"");
                    Log.e("pos ", pos+"");
                    selected = "";
                    onClickListener.onClickType13(holder,2);

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

        public ViewHolder(View itemView) {
            super(itemView);

            txt0 = itemView.findViewById(R.id.txt0);
            txt_num = itemView.findViewById(R.id.txt_num);
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
