package com.javad.quizapplang.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javad.quizapplang.App;
import com.javad.quizapplang.PaySuccess;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.modelgetlesson.Lesson;
import com.javad.quizapplang.service.CallBack;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AMIR on 9/28/2018.
 */
public class PayLessonAdapter extends RecyclerView.Adapter<PayLessonAdapter.ViewHolder>{
    private LayoutInflater layoutInflater;
    Context context;
    List<Lesson> lessonList;
    public PayLessonAdapter(Context context, List<Lesson> lessonList){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.lessonList=lessonList;
    }
    @NonNull
    @Override
    public PayLessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_pay,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.
        Lesson lesson=lessonList.get(position);
        holder.name.setText(lesson.getNameLesson());
        holder.desc.setText(lesson.getDiscription());
        holder.price.setText(lesson.getPrice());
        Picasso.with(context).load(App.BASE_URL+holder.imageView);

        final Intent intent = new Intent(context, PaySuccess.class);

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder
                        (new ContextThemeWrapper(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen));
                alertDialog.setTitle("تخفیف");
                alertDialog.setMessage("آیا تخفیف اعمال شود؟");
                alertDialog.setIcon(R.drawable.ic_check_circle);

                alertDialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        submitPay(lessonList.get(position).getId()+"",lessonList.get(position).getLevel()+"","true");
                        intent.putExtra("lessonId", lessonList.get(position).getId()+"");
                        intent.putExtra("levelId", lessonList.get(position).getLevel()+"");
                        App.context.startActivity(intent);


                    }
                });

                alertDialog.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        submitPay(lessonList.get(position).getId()+"",lessonList.get(position).getLevel()+"","false");
                        intent.putExtra("lessonId", lessonList.get(position).getId()+"");
                        intent.putExtra("levelId", lessonList.get(position).getLevel()+"");
                        App.context.startActivity(intent);

                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
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


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,desc,price,pay;
        CircleImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.name_les);
            price=itemView.findViewById(R.id.price);
            pay=itemView.findViewById(R.id.btn_pay);
            imageView=itemView.findViewById(R.id.img);
        }
    }
}
