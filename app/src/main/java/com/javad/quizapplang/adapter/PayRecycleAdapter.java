package com.javad.quizapplang.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.App;
import com.javad.quizapplang.PaySuccess;
import com.javad.quizapplang.R;
import com.javad.quizapplang.Type9Model;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.service.CallBack;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AMIR on 9/28/2018.
 */

public class PayRecycleAdapter extends RecyclerView.Adapter<PayRecycleAdapter.ViewHolder> {

    List<LessonOf> lessonOfList = new ArrayList<>();
    List<LevelOf> levelOfList = new ArrayList<>();
    int flag;
    Context context;
    LayoutInflater inflater;
    LessonOf lessonOf;
    LevelOf levelOf;

    public PayRecycleAdapter(List<LessonOf> list, List<LevelOf> list1, Context context, int flag) {
        this.lessonOfList = list;
        this.levelOfList = list1;
        this.context = context;
        this.flag = flag;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_pay, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (flag == 1) {

            LessonOf lessonOf = lessonOfList.get(position);
            holder.num_lesson.setText(lessonOf.getTotal_num()+"");
            holder.name.setText(lessonOf.getName());
            holder.price.setText(lessonOf.getPrice()+"");
            Picasso.with(App.context).load(new File(lessonOf.getImgPath()))
                    .placeholder(R.drawable.ic_person)
                    .into(holder.img);

        }else if (flag == 2){

            LevelOf levelOf = levelOfList.get(position);
            holder.name.setText(levelOf.getNum_level());
            holder.price.setText(levelOf.getPrice()+" تومان ");
            holder.num_lesson.setText(levelOf.getNum_lesson()+" درس در این سطح قرار گرفته است ");

            switch (position){
                case 0:
                    holder.img.setImageResource(R.drawable.beginer);
                    break;
                case 1:
                    holder.img.setImageResource(R.drawable.elementry);
                    break;
                case 2:
                    holder.img.setImageResource(R.drawable.intermediate);
                    break;
                case 3:
                    holder.img.setImageResource(R.drawable.upperintermediate);
                    break;
            }

        }

        holder.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(context, PaySuccess.class);

                if (flag == 1)
                {
                    lessonOf = lessonOfList.get(position);
                }else
                    {
                    levelOf = levelOfList.get(position);
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context
                        ,android.R.style.Theme_Black_NoTitleBar_Fullscreen));
                alertDialog.setTitle("تخفیف");
                alertDialog.setMessage("آیا تخفیف اعمال شود؟");
                alertDialog.setIcon(R.drawable.ic_check_circle);
                alertDialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();

                        if (flag == 1) {
                            submitPay(lessonOf.getId()+"",lessonOf.getLevel()+"","true");

                            intent.putExtra("lessonId", lessonOf.getId()+"");
                            intent.putExtra("levelId", lessonOf.getLevel()+"");
                        }else {
                            submitPay( "all", levelOf.getId() + "", "true");

                            intent.putExtra("levelId", levelOf.getId()+"");
                            intent.putExtra("lessonId", "all");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }

                        App.context.startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(context, "no", Toast.LENGTH_SHORT).show();
                        if (flag == 1) {
                            submitPay(lessonOf.getId() + "", lessonOf.getLevel() + "", "false");

                            intent.putExtra("lessonId", lessonOf.getId()+"");
                            intent.putExtra("levelId", lessonOf.getLevel()+"");
                        }else {
                            submitPay( "all", levelOf.getId() + "", "false");
                            intent.putExtra("levelId", levelOf.getId()+"");
                            intent.putExtra("lessonId", "all");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }

                        App.context.startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        });
    }


    @Override
    public int getItemCount() {

        if (flag == 1){
            return lessonOfList.size();
        }else {
            return levelOfList.size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, num_lesson, price, btnPay;
        CircleImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            num_lesson = itemView.findViewById(R.id.num_les);
            price = itemView.findViewById(R.id.price);
            btnPay = itemView.findViewById(R.id.btn_pay);
            img = itemView.findViewById(R.id.img);

        }
    }

    private void submitPay(String lessonId, String levelId, String disCount){
        new com.javad.quizapplang.service.Request<Data>(context).submit_buy(lessonId, levelId, disCount, new CallBack<Data>() {
            @Override
            public void onRequestSuccessful(Data data) {

                Log.e("discount = ", data.getInfoSale().getPriceDiscount()+"");
                Log.e("pay = ", data.getInfoSale().getPriceForPay()+"");
                Log.e("coin = ", data.getInfoSale().getCountCoinUseForDiscount()+"");
                Log.e("WithDiscount = ", data.getInfoSale().getWithDiscount()+"");

            }
        });
    }
}
