//package com.javad.quizapplang.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.javad.quizapplang.MainFragment;
//import com.javad.quizapplang.QuestionsActivity;
//import com.javad.quizapplang.R;
//import com.javad.quizapplang.model.Type12Model;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by AMIR on 9/28/2018.
// */
//
//public class Type13AdapterOrg extends RecyclerView.Adapter<Type13AdapterOrg.ViewHolder> {
//
//    List<Type12Model> type1List = new ArrayList<>();
//    Context context;
//    LayoutInflater inflater;
//    OnClickAdapterType2 adapterType2Listener;
//
//    public static boolean one = true;
//    public static int pos;
//
//    public Type13AdapterOrg(Context context, List<Type12Model> lessonModelsList, OnClickAdapterType2 adapterType2Listener) {
//        this.type1List = lessonModelsList;
//        this.context = context;
//        this.inflater = LayoutInflater.from(context);
//        this.adapterType2Listener = adapterType2Listener;
//    }
//
//    public interface OnClickAdapterType2{
//        void listener(int position, RecyclerView.ViewHolder viewHolder);
//    }
//
//    @Override
//    public Type13AdapterOrg.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = inflater.inflate(R.layout.row_type13, parent, false);
//
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final Type13AdapterOrg.ViewHolder holder, final int position) {
//
//        String question = type1List.get(position).getQuestion();
//
//        String[] str_question = question.split("-");
//        String question1 = "";
//        String question2 = "";
//
//        if (question.startsWith("-")) {
//            holder.question1.setVisibility(View.GONE);
////            ask = str_question[0];
//            question2 = str_question[1];
//        }else {
//            holder.question2.setVisibility(View.GONE);
//            question1 = str_question[0];
////            ask = str_question[1];
//        }
//
//        Log.e("type13a list bind",one + "//" + pos+ "//"+ type1List.get(position).getId());
//
//        if (MainFragment.positionOfType12 < type1List.size()) {
//
//            if (position == type1List.get(MainFragment.positionOfType12).getId()) {
//
//                holder.card2.setBackgroundColor(context.getResources().getColor(R.color.light));
//                MainFragment.string += (MainFragment.alphabt);
//                holder.ask.setText(MainFragment.string);
//                Log.e("in if", "//" + position + "//" + type1List.get(position).getId());
//                one = true;
//
//            } else if (position > type1List.get(MainFragment.positionOfType12).getId()){
//
//                holder.ask.setText("");
//
//            }else {
//
//                holder.ask.setText(type1List.get((MainFragment.positionOfType12)).getAnswer());
//                holder.card2.setBackgroundColor(context.getResources().getColor(R.color.light_green));
//
//            }
//        }
//
//        holder.question1.setText(question1);
//        holder.question2.setText(question2);
//        holder.answer.setText(type1List.get(position).getPersianAnswer());
//
//
//        holder.ask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MainFragment.positionOfType12 = position;
//                holder.ask.setBackgroundResource(R.drawable.shape_select);
//                adapterType2Listener.listener(position,holder);
//
//            }
//        });
//
//
//        holder.img_clean.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MainFragment.string = "";
//                holder.ask.setText(MainFragment.string);
//                Log.e("in clean",holder.ask.getText().toString());
//                adapterType2Listener.listener(position,holder);
//
//            }
//        });
//
//
//        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (holder.ask.getText().toString().equals(type1List.get(position).getAnswer())){
//
//                    holder.layer.setVisibility(View.VISIBLE);
////                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
//                    MainFragment.positionOfType12 ++;
//
//                    MainFragment.string = "";
//                    Log.e("position type14a",MainFragment.positionOfType12 + "");
//                    adapterType2Listener.listener(position,holder);
//
//                }else {
//
//                    MainFragment.string = "";
//                    holder.ask.setText(MainFragment.string);
////                    Toast.makeText(context, "اشتباست", Toast.LENGTH_SHORT).show();
//                    adapterType2Listener.listener(position,holder);
//
//                }
//
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return type1List.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView question1,question2, ask, answer;
//        ImageView img_clean, img_check;
//        FrameLayout card2, layer;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
////            layer = itemView.findViewById(R.id.layer);
////            card2 = itemView.findViewById(R.id.card2);
////            img_clean = itemView.findViewById(R.id.img_clean);
////            img_check = itemView.findViewById(R.id.img_check);
////            question1 = itemView.findViewById(R.id.question1);
////            question2 = itemView.findViewById(R.id.question2);
////            ask = itemView.findViewById(R.id.ask);
////            answer = itemView.findViewById(R.id.persianAnswer);
//
//        }
//    }
//
//}
