package com.javad.quizapplang.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.App;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.dbFlow.OPtsDet;
import com.javad.quizapplang.model.dbFlow.OPtsGen;
import com.javad.quizapplang.model.dbFlow.OPtsGen_Table;
import com.javad.quizapplang.model.dbFlow.Placement;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import com.javad.quizapplang.ResultTest;
/**
 * Created by SalmanPC3 on 9/16/2018.
 */

public class PlacementAdapterGeneral extends RecyclerView.Adapter<PlacementAdapterGeneral.ViewHolder> {

    List<Placement> placementList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    PlacementAdapter placementAdapter;
    List<OPtsGen> oPtsGens = new ArrayList<>();
    List<OPtsDet> oPtsDets = new ArrayList<>();
    Result result;

    Activity activity;

    public interface Result{
        void onClickListener();
    }

    public PlacementAdapterGeneral(Activity activity ,Context context, List<Placement> placementList,
                                   List<OPtsGen> oPtsGens, List<OPtsDet> oPtsDets,Result result){

        this.placementList = placementList;
        this.result = result;
//        this.oPtsGens = oPtsGens;
        this.oPtsDets = oPtsDets;
        this.context = context;
        this.activity = activity;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public PlacementAdapterGeneral.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.placement2,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlacementAdapterGeneral.ViewHolder holder, int position) {

        Placement placementOnline = placementList.get(position);
        oPtsGens = SQLite.select().from(OPtsGen.class)
                .where(OPtsGen_Table.id_placement.is(position))
                .queryList();

        // add and explode items of sub question in placement body
//        String[] s = Utils.explode(placementOnline.getBody());
//        placementBody = Arrays.asList(s);
//        List<List<String>> lists = Utils.explodeOpts(placementOnline.getOpts());

//        Log.e("body size = ", placementBody.size()+"  "+ placementBody.get(0) );
//        Log.e("opts size = ", lists.get(0).size() + "  " + lists.get(0).get(0) + "  "+ lists.get(0).get(1) );

        holder.question.setText(placementOnline.getHead());

        if (placementOnline.getType().equals("102") || placementOnline.getType().equals("103")) {

            holder.question_body.setVisibility(View.VISIBLE);
            holder.question_body.setText(placementOnline.getHead().replace("&","\n")
                    .replace("--","......."));

        }else {
            holder.question_body.setVisibility(View.GONE);
        }
//        if (placementList.size()-1 == position) {
//            holder.cv.setVisibility(View.VISIBLE);
//        }
//        subList = new ArrayList<>();
//        opt = new ArrayList<>();
//        List<String> stringList = new ArrayList<>();

//        for (int i = 0; i < placementBody.size(); i++) {
//
//            subList.add(placementBody.get(i));
//
//            subList_opts = new ArrayList<>();
//
//            for (int j = 0; j < lists.get(i).size(); j++) {
//
//                Log.e("opts size = ", lists.get(i).size() + "  " + lists.get(i).get(j));
//
//                subList_opts.add(lists.get(i).get(j));
////                Log.e("a1",subList_opts.get(j));
//
//                String[] s1 = subList_opts.get(i).split(",");
//                stringList = Arrays.asList(s1);
//
//            }
//
//            opt.add(stringList);

//        }



//        for (int i = 0; i < placementBody.size(); i++) {
//
//            subList.add(placementBody.get(i));
//
//            Log.e("quest1",subList.get(i));

//            subList_opts = new ArrayList<>();
//
//            for (int j = 0; j < lists.get(i).size(); j++) {
//
//                String s = lists.get(i).get(j);
//                subList_opts.add(s);
//
//                Log.e("a1",subList_opts.get(j));
//
//
//                String[] strings = s.split(",");
//                List<String> stringList = Arrays.asList(strings);
//
//                strOpts.addAll(stringList);
//
//            }
//
//            opt.add(subList_opts);
//            Log.e("a1",opt.toString());

//        }

        if ((placementList.size() - 1 )== position){
            holder.btn_end.setVisibility(View.VISIBLE);
        }else {
            holder.btn_end.setVisibility(View.GONE);
        }

        holder.btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context, ResultTest.class);
//                intent.putExtra("correct", answersCorrect);
//                context.startActivity(intent);
//
//                new android.os.Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        activity.finish();
//
//                    }
//                },500);

                result.onClickListener();

            }
        });

        recyclerView(holder.recyclerView, oPtsGens, oPtsDets, position);

    }

    @Override
    public int getItemCount() {
        return placementList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView question, question_body;
        CardView cv;
        Button btn_end;
        LinearLayout ll_place;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.rv_body);
            ll_place = itemView.findViewById(R.id.ll_place);
            question = (TextView) itemView.findViewById(R.id.question);
            question_body = (TextView) itemView.findViewById(R.id.question_body);
            cv = (CardView) itemView.findViewById(R.id.cv);
            btn_end = (Button) itemView.findViewById(R.id.btn_end);

        }
    }

    private void  recyclerView(RecyclerView recyclerView, List<OPtsGen> oPtsGens, List<OPtsDet> oPtsDets, final int id_placement){

        placementAdapter = new PlacementAdapter(context, oPtsGens, oPtsDets, id_placement,
                new PlacementAdapter.OnclickListenerOptGen() {
            @Override
            public void listener(int positionOptsGen, int positionOptsDec, RecyclerView.ViewHolder holder) {

                List<OPtsGen> list = SQLite.select().from(OPtsGen.class)
                        .where(OPtsGen_Table.id_placement.is(id_placement))
                        .queryList();

                SQLite.update(OPtsGen.class).set(OPtsGen_Table.user_answer.is(positionOptsDec))
                        .where(OPtsGen_Table.id_placement.eq(id_placement))
                        .and(OPtsGen_Table.id.eq(positionOptsGen))
                        .execute();

                list.get(positionOptsGen).setUser_answer(positionOptsDec);
                list.get(positionOptsGen).save();

                Log.e("generral", list.get(positionOptsGen).getUser_answer()+"");

//                Toast.makeText(context, "Click = " + positionOptsDec + ", gen" + positionOptsGen +", placement = " + id_placement, Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(placementAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

}
