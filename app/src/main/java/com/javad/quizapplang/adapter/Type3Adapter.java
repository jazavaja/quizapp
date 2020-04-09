package com.javad.quizapplang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type3Adapter extends RecyclerView.Adapter<Type3Adapter.ViewHolder> {

    private List<String> type1List = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    private OnClickAdapterType2 adapterType2Listener;
    int itemPosition = -1;

    public Type3Adapter(List<String> lessonModelsList, Context context, OnClickAdapterType2 adapterType2Listener) {
        this.type1List = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.adapterType2Listener = adapterType2Listener;
    }

    public interface OnClickAdapterType2{
        void listener(int position, RecyclerView.ViewHolder viewHolder, RadioButton view);
    }

    @Override
    public Type3Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_type3, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type3Adapter.ViewHolder holder, final int position) {

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < type1List.size(); i++) {

                    holder.radioButton.setChecked(false);

                }
                adapterType2Listener.listener(position,holder, holder.radioButton);

            }
        });

    }

    @Override
    public int getItemCount() {
        return type1List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.rb3);

        }
    }

}
