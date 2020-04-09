package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.dbFlow.OPtsDet;
import com.javad.quizapplang.model.dbFlow.OPtsDet_Table;
import com.javad.quizapplang.model.dbFlow.OPtsGen;
import com.javad.quizapplang.service.OnClickOptsListener;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SalmanPC3 on 9/16/2018.
 */

public class PlacementAdapter extends RecyclerView.Adapter<PlacementAdapter.ViewHolder> {

    List<OPtsGen> oPtsGens = new ArrayList<>();
    List<OPtsDet> oPtsDets;
    LayoutInflater layoutInflater;
    Context context;
    com.javad.quizapplang.adapter.PlacementAdapterOpts placementAdapterOpts;
    int id_placement;
    OnclickListenerOptGen listenerOptGen;
    public PlacementAdapter(Context context, List<OPtsGen> oPtsGens, List<OPtsDet> oPtsDets, int id_placement, OnclickListenerOptGen listenerOptGen){

        this.listenerOptGen = listenerOptGen;
        this.id_placement = id_placement;
        this.oPtsGens = oPtsGens;
        this.oPtsDets = oPtsDets;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public interface OnclickListenerOptGen{
        void listener(int positionOptsGen, int positionOptsDec, RecyclerView.ViewHolder holder);
    }
    @NonNull
    @Override
    public PlacementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_placement,parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final PlacementAdapter.ViewHolder holder, final int position) {
        OPtsGen gen = oPtsGens.get(position);

        holder.question.setText(gen.getId() + " ) " + gen.getBody());
        Log.e("check question pos", oPtsGens.size()+"");


        oPtsDets = SQLite.select().from(OPtsDet.class)
                .where(OPtsDet_Table.id_opt_gen.is(position))
                .and(OPtsDet_Table.id_placement.is(id_placement))
                .queryList();

        if (oPtsDets.size() == 4){
            holder.rb4.setVisibility(View.VISIBLE);
        }else {
            holder.rb4.setVisibility(View.GONE);
        }

        holder.rb1.setText(oPtsDets.get(0).getOpts_name());
        holder.rb2.setText(oPtsDets.get(1).getOpts_name());
        holder.rb3.setText(oPtsDets.get(2).getOpts_name());
        if (oPtsDets.size() == 4){
            holder.rb4.setText(oPtsDets.get(3).getOpts_name());
        }

        holder.rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerOptGen.listener(position,0,holder);

            }
        });

        holder.rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerOptGen.listener(position,1,holder);

            }
        });

        holder.rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerOptGen.listener(position,2,holder);

            }
        });

        holder.rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerOptGen.listener(position,3,holder);

            }
        });

   }
    @Override
    public int getItemCount() {
        return oPtsGens.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView question;
        public RadioButton rb1,rb2,rb3,rb4;
        public RadioGroup rg_opt;

        public ViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question_det);

            rb1 = itemView.findViewById(R.id.rb_1);
            rb2 = itemView.findViewById(R.id.rb_2);
            rb3 = itemView.findViewById(R.id.rb_3);
            rb4 = itemView.findViewById(R.id.rb_4);

            rg_opt = itemView.findViewById(R.id.rg_opt);

        }

    }

    private void recyclerView(final Context context , final List<OPtsDet> opt, final int pos){

        placementAdapterOpts = new com.javad.quizapplang.adapter.PlacementAdapterOpts(context, opt, new OnClickOptsListener() {
            @Override
            public void onClickOpts(int optPosition, RecyclerView.ViewHolder viewHolder) {

                Toast.makeText(context, opt.get(optPosition).getId_placement()+" vs "+pos + "  vs  "+ optPosition, Toast.LENGTH_SHORT).show();

                listenerOptGen.listener(pos,optPosition,viewHolder);
//                List<OPtsGen> list = SQLite.select().from(OPtsGen.class).where(OPtsGen_Table.id_placement.is(pos).eq()).queryList();

//                if (list.get(pos).getAnswer() == optPosition){
//
//                    viewHolder.itemView.setBackgroundColor(Color.GREEN);
//
//                }else {
//
//                    viewHolder.itemView.setBackgroundColor(Color.RED);
//
//                }

            }
        });

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(placementAdapterOpts);
//        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);

    }
}
