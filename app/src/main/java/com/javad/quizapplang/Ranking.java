package com.javad.quizapplang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.adapter.RankingRecycleAdapter;
import com.javad.quizapplang.model.CurrentUser;
import com.javad.quizapplang.model.RankModel;
import com.javad.quizapplang.model.TopUser;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Ranking extends AppCompatActivity {

    List<RankModel> listRv = new ArrayList<>();
    RecyclerView recyclerView;
    HashMap<String,List<RankModel>> hashMap = new HashMap<>();
    TextView coin_self, key_self, score_self, part_self, user_self, level_self,txt_space;
    LinearLayout ll_self;
    CircleImageView img_pic_self;
    PrefManager prefManager;

    private void setInfoUser(String coin, String key, String score, String part, String username, String img_pic, String level){
        coin_self.setText(coin);
        key_self.setText(key);
        score_self.setText(score);
        part_self.setText(part);
        user_self.setText(username);
        level_self.setText(level);
        Picasso.with(this).load(App.BASE_URL+img_pic)
                .placeholder(R.drawable.ic_account)
                .error(R.drawable.ic_account)
                .into(img_pic_self);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        prefManager = new PrefManager(this);
        recyclerView = findViewById(R.id.rv);
        coin_self = findViewById(R.id.coin_self);
        key_self = findViewById(R.id.key_self);
        score_self = findViewById(R.id.score_self);
        part_self = findViewById(R.id.part_self);
        user_self = findViewById(R.id.user_self);
        img_pic_self = findViewById(R.id.img_pic_self);
        level_self = findViewById(R.id.level_self);
        ll_self = findViewById(R.id.ll_self);
        txt_space = findViewById(R.id.txt_space);

        request();

    }

    private void request(){

            new Request<Data>(this).getRanking(new CallBack<Data>() {
                @Override
                public void onRequestSuccessful(Data data) {

                    List<TopUser> list = data.getTopUsers();

                    for (int i = 0; i < list.size(); i++) {

                        TopUser topUser = list.get(i);
                        String score = topUser.getTotalScore();
                        String rank = topUser.getRank();
                        String coin = String.valueOf(topUser.getCoin());
                        String key = String.valueOf(topUser.getKey());
                        String user = topUser.getUsername();
                        String photo = topUser.getPathPhoto();
                        String title = topUser.getTitle();
                        String sec = topUser.getSection();
                        String create = topUser.getCreatedAt();

                        listRv.add(new RankModel(rank, coin, score, create, user, photo,title,sec));

                        if (prefManager.getUsernameprofile().equals(user)){
                            ll_self.setVisibility(View.GONE);
                            txt_space.setVisibility(View.GONE);
                        }

                    }

                    CurrentUser currentUser = data.getCurrentUser();

                    String score = currentUser.getTotalScore();
                    String rank = currentUser.getRank();
                    String coin = currentUser.getCoin();
                    String key = currentUser.getKey();
                    String user = currentUser.getUsername();
                    String photo = currentUser.getPathPhotot();
//                    String creat = currentUser.c();
                    String sec = currentUser.getSection();

                    setInfoUser(coin,key,score,sec,user,photo,rank);

                    hashMap.put("rank",listRv);

                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager manager = new LinearLayoutManager(Ranking.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                    RankingRecycleAdapter rankingRecycleAdapter = new RankingRecycleAdapter(Ranking.this, hashMap.get("rank"));
                    recyclerView.setAdapter(rankingRecycleAdapter);

                }
            });

    }
}




