package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javad.quizapplang.R;
import com.javad.quizapplang.model.dbFlow.OPtsDet;
import com.javad.quizapplang.model.dbFlow.OPtsDet_Table;
import com.javad.quizapplang.model.dbFlow.OPtsGen;
import com.javad.quizapplang.model.dbFlow.OPtsGen_Table;
import com.javad.quizapplang.model.dbFlow.Placement;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SalmanPC3 on 9/16/2018.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    List<OPtsGen> oPtsGens = new ArrayList<>();
    List<OPtsDet> oPtsDets = new ArrayList<>();
    List<Placement> placementList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    RecyclerView recyclerView;

    public ResultAdapter(Context context, List<Placement> placementList, List<OPtsGen> oPtsGens, List<OPtsDet> oPtsDets){

        this.placementList = placementList;
        this.oPtsGens = oPtsGens;
        this.oPtsDets = oPtsDets;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.result_placement,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.ViewHolder holder, int position) {

        oPtsGens = SQLite.select().from(OPtsGen.class)
                .where(OPtsGen_Table.id_placement.eq(position))
                .queryList();

        setRecyclerview(holder.recyclerView,context,position,oPtsGens,oPtsDets);

    }

    @Override
    public int getItemCount() {
        return placementList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recycler_view);

        }

    }

    private void setRecyclerview(RecyclerView recyclerView, Context context, int placementId, List<OPtsGen> oPtsGens, List<OPtsDet> oPtsDets){

        Result2Adapter result2Adapter = new Result2Adapter(context,placementId,oPtsGens,oPtsDets);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(result2Adapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }
}
