package com.javad.quizapplang.adapter;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.javad.quizapplang.App;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.Type6;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.SubLessonOf;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIR on 9/28/2018.
 */

public class Type6GrideAdapter extends BaseAdapter {

    List<Type6> optsList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    int state, answer;

    public Type6GrideAdapter(List<Type6> optsList, Context context, int state, int answer) {
        this.optsList = optsList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.state = state;
        this.answer = answer;
    }

    @Override
    public int getCount() {
        return optsList.size();
    }

    @Override
    public Object getItem(int position) {
        return optsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = inflater.inflate(R.layout.row_type6,parent,false);

        TextView caption;
        final ImageView image;
        CardView cardView;

        cardView = view.findViewById(R.id.cv);
        image = view.findViewById(R.id.image);
        caption = view.findViewById(R.id.caption);

        caption.setText(optsList.get(position).getCaption());

        String path = optsList.get(position).getImage().replace("&","");
        String[] arrStr = path.split("\\.");


        Bitmap bitmap1 = BitmapFactory.decodeFile(arrStr[0]);
        Log.e("pathiiie =", arrStr[0]);
//        Picasso.with(context).load(path).into(image);
        image.setImageBitmap(bitmap1);


        if (state == 1 && position == answer){
            cardView.setBackgroundColor(Color.BLUE);
        }

        return view;
    }

}
