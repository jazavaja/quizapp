package com.javad.quizapplang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javad.quizapplang.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type2Adapter extends BaseAdapter {

    List<String> lessonModelsList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public LinearLayout ll;
    int state, answer;

    public Type2Adapter(List<String> lessonModelsList, Context context, int state, int answer) {
        this.lessonModelsList = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.state = state;
        this.answer = answer;
    }

    @Override
    public int getCount() {
        return lessonModelsList.size();
    }

    @Override
    public Object getItem(int position) {
        return lessonModelsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = inflater.inflate(R.layout.row_type2_8,parent,false);

        ll = view.findViewById(R.id.ll);
        TextView textView = view.findViewById(R.id.txt);
        ImageView imageView = view.findViewById(R.id.close);

        imageView.setVisibility(View.GONE);

        textView.setText(lessonModelsList.get(position));

        Log.e("gridTemp adapter=", lessonModelsList.get(position));
        Log.e("gridTemp size adapter=", lessonModelsList.size()+"");

        if (state == 1 && answer == position){

            ll.setBackgroundResource(R.color.color_blue);

        }

        return view;
    }
}
