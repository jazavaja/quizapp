package com.javad.quizapplang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javad.quizapplang.App;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.RankModel;
import com.javad.quizapplang.model.Type12Model;
import com.javad.quizapplang.utils.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AMIR on 9/28/2018.
 */

public class RankingRecycleAdapter extends RecyclerView.Adapter<RankingRecycleAdapter.ViewHolder> {

    List<RankModel> rankModelList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    PrefManager prefManager;


    public RankingRecycleAdapter(Context context, List<RankModel> lessonModelsList) {
        this.rankModelList = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        prefManager = new PrefManager(context);
    }

    @Override
    public RankingRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_ranking, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RankingRecycleAdapter.ViewHolder holder, int position) {

        RankModel rankModel = rankModelList.get(position);

        if (position%2 != 0) {
            holder.ll.setBackgroundResource(R.color.bgColor);
        }else {
            holder.ll.setBackgroundResource(R.color.colorWhite);
        }

        if (prefManager.getUsernameprofile().equals(rankModel.getUsername())){
            holder.ll.setBackgroundResource(R.color.color_green);
            holder.key.setTextColor(Color.WHITE);
            holder.coin.setTextColor(Color.WHITE);
            holder.score.setTextColor(Color.WHITE);
            holder.user.setTextColor(Color.WHITE);
            holder.level.setTextColor(Color.WHITE);
            holder.part.setTextColor(Color.WHITE);
        }
        Picasso.with(context).load(App.BASE_URL+rankModelList.get(position).getPhoto()).into(holder.imgUser);

        int time = ((int) ((System.currentTimeMillis()/1000) - Integer.valueOf(rankModel.getCreateAt()))) / (60*60*24);
        holder.key.setText(time + "");
        holder.coin.setText(rankModel.getCoin());
        holder.score.setText(rankModel.getScore());
        holder.user.setText(rankModel.getUsername());
        holder.level.setText(rankModel.getRank());
        holder.part.setText(rankModel.getSec()+"/"+rankModel.getTitle());

        Log.e("current time  == ", System.currentTimeMillis()+"");
        Log.e("create at == ", rankModel.getCreateAt()+"");
        Log.e("adapter items = ","key = "+holder.key.getText().toString() + "photo = "+
                App.BASE_URL+rankModelList.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return rankModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;
        TextView coin, score, key, part, user, level;
        CircleImageView imgUser;

        public ViewHolder(View itemView) {
            super(itemView);

            ll = itemView.findViewById(R.id.ll);
            coin = itemView.findViewById(R.id.coin);
            score = itemView.findViewById(R.id.score);
            key = itemView.findViewById(R.id.key);
            part = itemView.findViewById(R.id.part);
            user = itemView.findViewById(R.id.user);
            level = itemView.findViewById(R.id.level);
            imgUser = itemView.findViewById(R.id.img_pic);
        }
    }

}
