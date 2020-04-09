package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.javad.quizapplang.model.Type12Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type15Adapter extends RecyclerView.Adapter<Type15Adapter.ViewHolder> {

    List<Type12Model> type1List = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    OnClickAdapterType2 adapterType2Listener;

    public static int pos_15;

    public Type15Adapter(Context context, List<Type12Model> lessonModelsList, OnClickAdapterType2 adapterType2Listener) {
        this.type1List = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.adapterType2Listener = adapterType2Listener;
    }

    public interface OnClickAdapterType2{
        void listener(int position, RecyclerView.ViewHolder viewHolder);
    }

    @Override
    public Type15Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_type15, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type15Adapter.ViewHolder holder, final int position) {

        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();

//                if (position == )

            }
        });

    }

    @Override
    public int getItemCount() {
        return type1List.size();
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
