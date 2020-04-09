package com.javad.quizapplang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class Result2Adapter extends RecyclerView.Adapter<Result2Adapter.ViewHolder> {

    List<OPtsGen> oPtsGens = new ArrayList<>();
    List<OPtsDet> oPtsDets = new ArrayList<>();
    int placementId;
    LayoutInflater layoutInflater;
    Context context;

    public Result2Adapter(Context context, int placementId, List<OPtsGen> oPtsGens, List<OPtsDet> oPtsDets){

        this.placementId = placementId;
        this.oPtsGens = oPtsGens;
        this.oPtsDets = oPtsDets;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public Result2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.result_row,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Result2Adapter.ViewHolder holder, int position) {

        OPtsGen oPtsGen = oPtsGens.get(position);

        holder.number.setText(oPtsGen.getId() + "");

            oPtsDets = SQLite.select().from(OPtsDet.class)
                    .where(OPtsDet_Table.id_opt_gen.is(position))
                    .and(OPtsDet_Table.id_placement.is(placementId))
                    .queryList();

            Log.e("lineeeeee", "------------------------------------");

            try {

                holder.first.setText("A: " + oPtsDets.get(0).getOpts_name());
                holder.second.setText("B: " + oPtsDets.get(1).getOpts_name());
                holder.third.setText("C: " + oPtsDets.get(2).getOpts_name());
                holder.fourth.setVisibility(View.GONE);

                Log.e("counter ---- 0", oPtsDets.get(0).getOpts_name() + "");
                Log.e("counter ---- 1", oPtsDets.get(1).getOpts_name() + "");
                Log.e("counter ---- 2", oPtsDets.get(2).getOpts_name() + "");

                if (oPtsDets.size() == 4) {
                    holder.fourth.setVisibility(View.VISIBLE);
                    holder.fourth.setText("D: " + oPtsDets.get(3).getOpts_name());
                    Log.e("counter ---- 3", oPtsDets.get(2).getOpts_name() + "");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            int answer = oPtsGens.get(position).getAnswer();
            int user_answer = oPtsGens.get(position).getUser_answer();

            if (user_answer == answer){

                switch (answer){
                    case 0:
                        holder.first.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img1.setVisibility(View.VISIBLE);
                        holder.img2.setVisibility(View.GONE);
                        holder.img3.setVisibility(View.GONE);
                        holder.img4.setVisibility(View.GONE);
                        Log.e("answer img1/"+(position+1)+"-------" , "img 1");
                        break;
                    case 1:
                        holder.second.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img2.setVisibility(View.VISIBLE);
                        holder.img1.setVisibility(View.GONE);
                        holder.img3.setVisibility(View.GONE);
                        holder.img4.setVisibility(View.GONE);
                        Log.e("answer img2/"+(position+1)+"-------" , "img 2");
                        break;
                    case 2:
                        holder.third.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img3.setVisibility(View.VISIBLE);
                        holder.img2.setVisibility(View.GONE);
                        holder.img1.setVisibility(View.GONE);
                        holder.img4.setVisibility(View.GONE);
                        Log.e("answer img3/"+(position+1)+"-------" , "img 3");
                        break;
                    case 3:
                        holder.fourth.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img4.setVisibility(View.VISIBLE);
                        holder.img2.setVisibility(View.GONE);
                        holder.img3.setVisibility(View.GONE);
                        holder.img1.setVisibility(View.GONE);
                        Log.e("answer img4/"+(position+1)+"-------" , "img 4");
                        break;
                }

            }else {

                switch (answer) {
                    case 0:
                        holder.img1.setVisibility(View.VISIBLE);
                        holder.img2.setVisibility(View.GONE);
                        holder.img3.setVisibility(View.GONE);
                        holder.img4.setVisibility(View.GONE);
                        Log.e("answer img1/" + (position + 1) + "-------", "img 1");
                        break;
                    case 1:
                        holder.img2.setVisibility(View.VISIBLE);
                        holder.img1.setVisibility(View.GONE);
                        holder.img3.setVisibility(View.GONE);
                        holder.img4.setVisibility(View.GONE);
                        Log.e("answer img2/" + (position + 1) + "-------", "img 2");
                        break;
                    case 2:
                        holder.img3.setVisibility(View.VISIBLE);
                        holder.img2.setVisibility(View.GONE);
                        holder.img1.setVisibility(View.GONE);
                        holder.img4.setVisibility(View.GONE);
                        Log.e("answer img3/" + (position + 1) + "-------", "img 3");
                        break;
                    case 3:
                        holder.img4.setVisibility(View.VISIBLE);
                        holder.img2.setVisibility(View.GONE);
                        holder.img3.setVisibility(View.GONE);
                        holder.img1.setVisibility(View.GONE);
                        Log.e("answer img4/" + (position + 1) + "-------", "img 4");
                        break;
                }

                switch (user_answer) {
                    case 0:
                        holder.first.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img1.setVisibility(View.VISIBLE);
                        holder.img1.setImageResource(R.drawable.ic_close);
                        holder.img1.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                        Log.e("user answer img1/" + (position + 1) + "-------", "img 1");
                        break;
                    case 1:
                        holder.second.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img2.setVisibility(View.VISIBLE);
                        holder.img2.setImageResource(R.drawable.ic_close);
                        holder.img2.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                        Log.e("user answer img2/" + (position + 1) + "-------", "img 2");
                        break;
                    case 2:
                        holder.third.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img3.setVisibility(View.VISIBLE);
                        holder.img3.setImageResource(R.drawable.ic_close);
                        Log.e("user answer img3/" + (position + 1) + "-------", "img 3");
                        holder.img3.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                        break;
                    case 3:
                        holder.fourth.setBackgroundColor(context.getResources().getColor(R.color.colorBlue));
                        holder.img4.setVisibility(View.VISIBLE);
                        holder.img4.setImageResource(R.drawable.ic_close);
                        holder.img4.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                        Log.e("user answer img4/" + (position + 1) + "-------", "img 4");
                        break;
                }

            }
            Log.e("Answer" + (position + 1) + "-------", answer + "");
            Log.e("UserAnswer" + (position + 1) + "-------", user_answer + "");

            if (answer == user_answer) {

                holder.tik.setText("Correct");
                holder.tik.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));

            } else {

                holder.tik.setText("Wrong");
                holder.tik.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

            }

    }

    @Override
    public int getItemCount() {
        return oPtsGens.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tik,number, first, second, third, fourth;
        ImageView img1,img2,img3,img4;

        public ViewHolder(View itemView) {
            super(itemView);

            tik = itemView.findViewById(R.id.tik);
            number = itemView.findViewById(R.id.number);
            first = itemView.findViewById(R.id.first);
            second = itemView.findViewById(R.id.second);
            third = itemView.findViewById(R.id.third);
            fourth = itemView.findViewById(R.id.fourth);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);

        }

    }

}
