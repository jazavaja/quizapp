package com.javad.quizapplang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.javad.quizapplang.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type2_8AnswerAdapter extends RecyclerView.Adapter<Type2_8AnswerAdapter.ViewHolder> {

    List<String> type1List = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    OnClickAdapterType2 adapterType2Listener;

    public Type2_8AnswerAdapter(List<String> lessonModelsList, Context context,OnClickAdapterType2 adapterType2Listener) {
        this.type1List = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.adapterType2Listener = adapterType2Listener;
    }

    public interface OnClickAdapterType2{
        void listener(int position, RecyclerView.ViewHolder viewHolder);
    }

    @Override
    public Type2_8AnswerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_type2_8, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Type2_8AnswerAdapter.ViewHolder holder, final int position) {

        holder.txt.setBackgroundColor(Color.WHITE);
        holder.txt.setText(type1List.get(position));

        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            adapterType2Listener.listener(position,holder);
            type1List.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, type1List.size());

            }
        });

    }

    @Override
    public int getItemCount() {
        return type1List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt;
        ImageView close;

        public ViewHolder(View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.txt);
            close = itemView.findViewById(R.id.close);

        }
    }

}
