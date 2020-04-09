package com.javad.quizapplang.helperClasses;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class OptionsSet {
    private static final int UPPER = 4;
    private static final int BOTTOM = 5;
    public static int NORMAL = 0;
    public static int WHEN_VISIBLE = 1;
    public static int WHEN_HIDE_BOTTOM = -1;

    private Context context;
    private List<TextView> textViewBottom;
    private List<TextView> textViewUpper;
    private OptionInterface optionInterface;
//    FlowLayout.LayoutParams params;
//    List<String> stringList;

    public void main(final Context context, List<String> listGozineha, final FlowLayout upper,
                     FlowLayout bottom, int Max, final OptionInterface optionInterface) {
        this.optionInterface = optionInterface;
        this.context = context;
//        stringList = new ArrayList<>();
        setBottom(listGozineha, bottom, upper,Max);
    }

    public void setBottom(List<String> listGozineha, final FlowLayout  bottom, final FlowLayout upper, final int Max) {
        textViewBottom = new ArrayList<>();
        textViewUpper=new ArrayList<>();
        List<String> str = new ArrayList<>();
        for (int i = 0; i < listGozineha.size(); i++) {
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                    FlowLayout.LayoutParams.WRAP_CONTENT,
                    FlowLayout.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(context);
            textView.setLayoutParams(params);
            textView.setText(listGozineha.get(i));
            textView.setTag("0");
            textViewBottom.add(textView);
            bottom.addView(textView);
            optionInterface.CustomTextViewBottom(textViewBottom, i);

        }
        for (int i = 0; i < listGozineha.size(); i++) {
            final int finalI = i;
            textViewBottom.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkIfExitDoNotAdd(upper, textViewBottom.get(finalI).getText().toString())) {
                        if (checkCount(upper,Max)){
                            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,
                                    FlowLayout.LayoutParams.WRAP_CONTENT);
                            TextView textView = new TextView(context);
                            textView.setLayoutParams(params);
                            textView.setText(textViewBottom.get(finalI).getText().toString());
                            optionInterface.CustomTextViewTop(textView);
                            textView.setTag("0");
                            textViewUpper.add(textView);
                            upper.addView(textView);
                            optionInterface.SetOnClickBottomOption(textViewBottom.get(finalI));

                        }
                    }
                    for (int i=0;i<textViewUpper.size();i++){
                        final int finalI = i;
                        textViewUpper.get(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionInterface.SetOnClickUpperOption(textViewUpper.get(finalI));
                                Toast.makeText(context,textViewUpper.get(finalI).getText().toString(), Toast.LENGTH_SHORT).show();
                                upper.removeView(textViewUpper.get(finalI));
                                for (int i = 0; i < bottom.getChildCount(); i++) {
                                    TextView view = (TextView) bottom.getChildAt(i);
                                    if (view.getText().toString().equals(textViewUpper.get(finalI).getText().toString())) {
                                        optionInterface.EfectOnBottomOptionWhenUpperClick(view);
                                        view.setTag("0");
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }


    }
    public static List<String> getStrListTextView(FlowLayout upper){
        List<String> strings=new ArrayList<>();
//        if (!OptionsSetSub.ENGLISH == )
        for (int i=0;i<upper.getChildCount();i++){
           TextView textView= (TextView) upper.getChildAt(i);
           strings.add(textView.getText().toString());
        }
        return strings;
    }

    public boolean checkCount(FlowLayout upper, int MaxCount){
        if (upper.getChildCount()==MaxCount)
            return false;
        else return true;
    }

    public boolean checkIfExitDoNotAdd(FlowLayout upper, String newString){
        for (int i=0;i<upper.getChildCount();i++) {
            TextView view = (TextView) upper.getChildAt(i);
            if (view.getText().toString().equals(newString)) {
                return false;
            }
        }
        return true;
    }

    public interface OptionInterface {
        public void CustomTextViewBottom(List<TextView> textViews, int position);

        public void CustomTextViewTop(TextView textView);

        public void SetOnClickBottomOption(TextView textViews);

        public void SetOnClickUpperOption(TextView textView);

        public void EfectOnBottomOptionWhenUpperClick(TextView textView);
    }
}

