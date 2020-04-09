package com.javad.quizapplang;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.helperClasses.OptionsSet;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class TakePic extends AppCompatActivity {

    FlowLayout payini;
    private int len = 70;
    FlowLayout balayi;
    List<String> strings;
    int paddingTop = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pic);
        payini = findViewById(R.id.llMain);
        balayi = findViewById(R.id.balayi);
        strings = new ArrayList<>();
        strings.add("javad");
        strings.add("saeed");
        strings.add("mehdi");
        strings.add("ali");
        strings.add("hossein");
        strings.add("abas");
        OptionsSet optionsSet=new OptionsSet();
        optionsSet.main(this, strings, balayi, payini,strings.size(), new OptionsSet.OptionInterface() {
            @Override
            public void CustomTextViewBottom(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                textViews.get(position).setTextSize(22f);
            }

            @Override
            public void CustomTextViewTop(TextView textView) {
                textView.setPadding(paddingTop,paddingTop,paddingTop,paddingTop);
                textView.setTextSize(22f);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void SetOnClickBottomOption(TextView textViews) {
                textViews.setTextColor(Color.GRAY);
                textViews.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void SetOnClickUpperOption(TextView textView) {
                textView.setTextColor(Color.RED);
                textView.setBackgroundColor(Color.BLACK);
                textView.setBackgroundResource(R.color.colorWhite);
            }

            @Override
            public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                textView.setBackgroundColor(Color.GRAY);
                textView.setTextColor(Color.BLACK);
            }
        });

        Button button=findViewById(R.id.fff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(TakePic.this,OptionsSet.getStrListTextView(balayi).get(0)+""
                            , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
