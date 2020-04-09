package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.javad.quizapplang.R;
import com.javad.quizapplang.model.dbFlow.OPtsDet;
import com.javad.quizapplang.service.OnClickOptsListener;

/**
 * Created by SalmanPC3 on 9/16/2018.
 */

public class PlacementAdapterOpts extends RecyclerView.Adapter<PlacementAdapterOpts.ViewHolder> {

    List<OPtsDet> list_opts;
    LayoutInflater layoutInflater;
    Context context;
    OnClickOptsListener onClickOptsListener;

    public PlacementAdapterOpts(Context context, List<OPtsDet> list_opts, OnClickOptsListener onClickOptsListener){

        this.onClickOptsListener = onClickOptsListener;
        this.list_opts = list_opts;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public PlacementAdapterOpts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_opts,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlacementAdapterOpts.ViewHolder holder, final int position) {

        String str = list_opts.get(position).getOpts_name();
        holder.answer.setText(str);

        holder.answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(context, "ok=  "+ position, Toast.LENGTH_SHORT).show();
//                SQLite.select().from(OPtsGen.class).where(OPtsGen_Table.id_placement.is())
                onClickOptsListener.onClickOpts(position, holder);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list_opts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView answer;

        public ViewHolder(View itemView) {
            super(itemView);

            answer = itemView.findViewById(R.id.a1);

        }
    }
}
