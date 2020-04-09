package com.javad.quizapplang.adapter;

import android.content.Context;
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

import com.javad.quizapplang.MainFragment;
import com.javad.quizapplang.R;
import com.javad.quizapplang.Type9Model;
import com.javad.quizapplang.model.dbFlow.questions.QAnswers;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type18RecycleAdapter extends RecyclerView.Adapter<Type18RecycleAdapter.ViewHolder> {

    List<String> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    SelectItem selectItem;
    public int pos = -1, state;
    String[] answer = {""};
    public static String selected = "";


    public interface SelectItem{
        void selectListener(int position);
    }

    public Type18RecycleAdapter(Context context, List<String> list, int state, String[] answer, SelectItem selectItem) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.answer = answer;
        this.state = state;
        this.selectItem = selectItem;
    }


    @Override
    public Type18RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_type18, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type18RecycleAdapter.ViewHolder holder, final int position) {

        holder.txt_question.setText(list.get(position));

        if (state == 1) {
            holder.txt_answer.setText(answer[position]);
        }

        if (pos == position){

            holder.txt_answer.setText(selected);

        }

        holder.txt_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos = position;
                selectItem.selectListener(position);
                Log.e("pos = ", String.valueOf(pos));

                selected = "";
                holder.txt_answer.setText(selected);

                for (int i = 0; i < list.size(); i++) {
                    if (position == i){
                        holder.txt_answer.setBackgroundResource(R.drawable.shape_select);
                        return;
                    }else {
                        holder.txt_answer.setBackgroundResource(R.drawable.shape);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_question, txt_answer;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_answer = itemView.findViewById(R.id.txt_answer);
            txt_question = itemView.findViewById(R.id.txt_question);

        }
    }



}
