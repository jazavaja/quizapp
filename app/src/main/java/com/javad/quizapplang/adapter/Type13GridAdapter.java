package com.javad.quizapplang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javad.quizapplang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type13GridAdapter extends BaseAdapter {

    List<String> lessonModelsList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public LinearLayout ll;

    public Type13GridAdapter(List<String> lessonModelsList, Context context) {
        this.lessonModelsList = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = inflater.inflate(R.layout.row_type2_8,parent,false);

        ll = view.findViewById(R.id.ll);
        final TextView textView = view.findViewById(R.id.txt);
        ImageView imageView = view.findViewById(R.id.close);

        imageView.setVisibility(View.GONE);

        try {
            textView.setText(lessonModelsList.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MainFragment.alphabt = lessonModelsList.get(position);
//
//            }
//        });

        return view;
    }
}
