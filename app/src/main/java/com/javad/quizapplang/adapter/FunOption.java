package com.javad.quizapplang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class  FunOption {

    public static List<TextView> setViews(final Context context, List<String> stringList, FlowLayout llMain, final FunClick funClick){

        final List<TextView> textViews=new ArrayList<>();
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,
                FlowLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < stringList.size(); i++) {
            TextView text = new TextView(context);
            text.setLayoutParams(params);
            text.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            text.setText(stringList.get(i));
            text.setPadding(5, 5, 5, 5);
            textViews.add(text);
            llMain.addView(text);
        }

        for (int i = 0; i <stringList.size(); i++) {
            final int finalI = i;
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    funClick.SetOnClickTextView(textViews.get(finalI));
                }
            });
        }
        return textViews;
    }
    public interface FunClick{
        public void SetOnClickTextView(TextView textView);
    }
}
