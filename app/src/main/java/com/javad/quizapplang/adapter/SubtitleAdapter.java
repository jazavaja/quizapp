package com.javad.quizapplang.adapter;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.App;
import com.javad.quizapplang.QuestionsActivity;
import com.javad.quizapplang.R;
import com.javad.quizapplang.utils.TooltipWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class SubtitleAdapter extends RecyclerView.Adapter<SubtitleAdapter.ViewHolder> {

    List<String> list = new ArrayList<>();
    List<String> list_sub = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    TooltipWindow tipWindow;
    String s1;

    public SubtitleAdapter(List<String> list,List<String> list_sub, Context context) {
        this.list = list;
        this.list_sub = list_sub;
        this.context = context;
        this.inflater = LayoutInflater.from(App.context);
        tipWindow = new TooltipWindow(context);

    }

    @Override
    public SubtitleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_sub, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubtitleAdapter.ViewHolder holder, final int position) {

        final String s = list.get(position);

        holder.txt_sub.setText(s);

        holder.txt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                for (int i = 0; i < list_sub.size(); i++) {
                    String[] strings = list_sub.get(i).split("=");

                    Log.e("string[0] = ", strings[0]);
                    Log.e("string[1] = ", strings[1]);
                    Log.e("s = ", holder.txt_sub.getText().toString());

                    if (strings[0].equals(holder.txt_sub.getText().toString())){
                        Log.e("string[1] = ", strings[1]);
                        s1 = strings[1];
                        showPopup(v,s1);
                        notifyDataSetChanged();
                    }
                }

//                if (s.equals(strings[0])){
//                        s1 = strings[1];
//
//                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                        showPopup(v,s1);
//
//                    }else {
//
//                        Toast.makeText(context, "do ntttt", Toast.LENGTH_SHORT).show();
//
//                    }
//                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_sub;
        FrameLayout root_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_sub = itemView.findViewById(R.id.txt_sub);
            root_layout = itemView.findViewById(R.id.root_layout);

        }
    }

    private void showPopup(View v,String content) {
        if(!tipWindow.isTooltipShown()) {
            tipWindow.showToolTip(v,content);
        }
    }

}

