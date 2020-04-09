package com.javad.quizapplang.helperClasses;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nex3z.flowlayout.FlowLayout;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;
import java.util.ArrayList;
import java.util.List;

public class OptionsSetSub2 {
    private static final int UPPER = 4;
    private static final int BOTTOM = 5;
    public static final int ENGLISH = 1;
    public static final int PERSIAN = 2;
    public static int NORMAL = 0;
    public static int WHEN_VISIBLE = 1;
    public static int WHEN_HIDE_BOTTOM = -1;

    private Context context;
    private List<TextView> soratSoal;
    private List<TextView> textViewBottom;
    private List<TextView> textViewUpper;
    private OptionInterface optionInterface;
    private List<String> newZirNevis;
    private List<ToolTip.Builder> builderList;
    List<ToolTip.Builder> newBuilderList;
    private int modeBottom;


    public static String getStringOfListString(List<String> strings) {
        StringBuilder s = new StringBuilder();
//        s.append("");
        for (int i = 0; i < strings.size(); i++) {
            s.append(strings.get(i));
//            s.append(strings.get(i));
        }
        return s.toString();
        //        return s != null ? s.toString() : null;
    }

    public void main(final Context context, int modeSoratSoal, int modeBottom, List<String> listGozineha, List<String> listZirNevisha, List<String> listSoratSoal, final FlowLayout upper, FlowLayout bottom, FlowLayout soratSoal, RelativeLayout mainRoot, int Max, final OptionInterface optionInterface) {
        this.optionInterface = optionInterface;
        this.context = context;
//        this.mode=modeSoratSoal;
        this.modeBottom = modeBottom;
        setSoratSoal(modeSoratSoal, listSoratSoal, listZirNevisha, soratSoal, mainRoot);
        setBottom(listGozineha, bottom, upper, Max);
//        quickSort(listSoratSoalHa);

    }

    private void setSoratSoal(int mode, List<String> soratSoal, final List<String> zirneVis, final FlowLayout layoutSoratSoal, RelativeLayout mainRoot) {
        List<TextView> textViewListSoalat = new ArrayList<>();
        builderList = new ArrayList<>();
        newZirNevis = new ArrayList<>();

        List<String> stringList = new ArrayList<>();
        if (mode == OptionsSetSub2.ENGLISH) {
            stringList = soratSoal;
//             stringList= quickSort(soratSoal);
        }
        if (mode == OptionsSetSub2.PERSIAN) {
            stringList = quickSort(soratSoal);
        }
        for (int i = 0; i < stringList.size(); i++) {
            FlowLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            TextView ttt = new TextView(context);
            ttt.setLayoutParams(layoutParams);
            ttt.setText(stringList.get(i));
            textViewListSoalat.add(ttt);
            layoutSoratSoal.addView(ttt);
            optionInterface.CustomTextViewSoal(textViewListSoalat, i);
        }
        final ToolTipsManager toolTipsManager = new ToolTipsManager();

        if (mode == PERSIAN) {
            newZirNevis = zirneVis;
//            newZirNevis = quickSort(zirneVis);
            textViewListSoalat = quickSortListViewSoalat(textViewListSoalat);
            builderList = quickSortZirNevisHa(builderList);
        }
        if (mode == ENGLISH) {
            newZirNevis = zirneVis;
        }
        for (int i = 0; i < newZirNevis.size(); i++) {
            ToolTip.Builder builder = new ToolTip.Builder(
                    context
                    , textViewListSoalat.get(i)
                    , mainRoot,
                    newZirNevis.get(i)
                    , ToolTip.POSITION_BELOW);
            builderList.add(builder);
            Log.e("ListZirnevis", newZirNevis.get(i));
        }
        for (int i = 0; i < builderList.size(); i++) {
            Log.e("sizeOfBulider", builderList.size() + "");
            final int finalL = i;
            textViewListSoalat.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toolTipsManager.dismissAll();
//                    if (!builderList.get(finalL).)
                    if (!newZirNevis.get(finalL).equals("")) {
                        toolTipsManager.show(builderList.get(finalL).build());
                    }
                }
            });
        }
        layoutSoratSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolTipsManager.dismissAll();
            }
        });
    }

    private void setBottom(List<String> listGozineha, final FlowLayout bottom, final FlowLayout upper, final int Max) {
        textViewBottom = new ArrayList<>();
        textViewUpper = new ArrayList<>();
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
            optionInterface.CustomTextViewGozineha(textViewBottom, i);


        }
        Log.e("Width", String.valueOf(upper.getMeasuredWidth()));
//        for (int j=0;j<)
//        for (int i=0;i<4;i++){
//        }

        for (int i = 0; i < listGozineha.size(); i++) {
            final int finalI = i;
            textViewBottom.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkIfExitDoNotAdd(upper, bottom, textViewBottom.get(finalI).getText().toString())) {
                        if (checkCount(upper, Max))
                        {
                            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                            TextView textView = new TextView(context);
                            textView.setLayoutParams(params);
                            textView.setText(textViewBottom.get(finalI).getText().toString());
                            optionInterface.CustomTextViewTop(textView);
                            textView.setTag("0");
                            textViewUpper.add(textViewUpper.size(), textView);
                            if (modeBottom == PERSIAN)
                            {
                                if (upper.getChildCount() > 0)
                                    upper.addView(textView, 0);
                                else
                                    upper.addView(textView);
                            }
                            if (modeBottom == ENGLISH)
                            {
                                upper.addView(textView);
                            }
                            optionInterface.SetOnClickBottomOption(textViewBottom.get(finalI));
                        }
                    }
                    for (int i = 0; i < textViewUpper.size(); i++) {
                        final int finalI = i;
                        textViewUpper.get(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionInterface.SetOnClickUpperOption(textViewUpper.get(finalI));
//                                Toast.makeText(context,textViewUpper.get(finalI).getText().toString(), Toast.LENGTH_SHORT).show();
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

    public void setZieLine(FlowLayout flowLayout) {
//        flowLayout.is
        
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(layoutParams);
        flowLayout.addView(linearLayout);

//        linearLayout.setLayoutParams();
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 0.4f;
        textView.setPadding(5, 5, 5, 5);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setMaxLines(3);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 400; i++) {
            stringBuilder.append("_");
        }
        textView.setText(stringBuilder.toString());
        textView.setTag("3");
//        ViewGroup viewGroup = (ViewGroup) linearLayout.getParent();
//        viewGroup.addView(textView);
        linearLayout.addView(textView);

        TextView textView1 = new TextView(context);
        textView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.4f));
        textView.setText("SSSS");
//        viewGroup.addView(textView1);
        linearLayout.addView(textView1);



    }

    public static List<String> getStrListTxtView(FlowLayout upper, int modeGozineha) {
        List<String> s = new ArrayList<>();
        if (modeGozineha == PERSIAN) {
            for (int i = upper.getChildCount() - 1; i >= 0; i--) {
                TextView textView = (TextView) upper.getChildAt(i);
                s.add(textView.getText().toString());
            }
//            Toast.makeText(App.context, "per", Toast.LENGTH_SHORT).show();
        }
        if (modeGozineha == ENGLISH) {
            for (int i = 0; i < upper.getChildCount(); i++) {
                TextView textView = (TextView) upper.getChildAt(i);
                s.add(textView.getText().toString());
            }
//            Toast.makeText(App.context, "en", Toast.LENGTH_SHORT).show();
        }
        return s;
    }

    private boolean checkCount(FlowLayout upper, int MaxCount) {
        int tedad = 0;
        for (int i = 0; i < upper.getChildCount(); i++) {
            TextView textView = (TextView) upper.getChildAt(i);
            if (textView.getTag() == "3") {
                tedad++;
            }
//            textView.getTag()
        }
        if (tedad == MaxCount)
            return false;
        else return true;
    }

    private boolean checkIfExitDoNotAdd(FlowLayout upper, FlowLayout bottom, String newString) {
        int countBottom = 0;
        int countTop = 0;
        for (int i = 0; i < bottom.getChildCount(); i++) {
            TextView textView = (TextView) bottom.getChildAt(i);
            if (textView.getText().toString().equals(newString))
                countBottom++;
        }
        for (int i = 0; i < upper.getChildCount(); i++) {
            TextView textView = (TextView) upper.getChildAt(i);
            if (textView.getText().toString().equals(newString))
                countTop++;
        }
        if (countTop == countBottom) {
            return false;
        }
//        }
        return true;
    }

    public interface OptionInterface {

        public void CustomTextViewSoal(List<TextView> textViews, int position);

        public void CustomTextViewGozineha(List<TextView> textViews, int position);

        public void CustomTextViewTop(TextView textView);

        public void SetOnClickBottomOption(TextView textViews);

        public void SetOnClickUpperOption(TextView textView);

        public void EfectOnBottomOptionWhenUpperClick(TextView textView);
    }

    private List<String> quickSort(List<String> list) {
        List<String> jadid = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Log.e("SORT", list.get(i));
            jadid.add(list.get(i));
//            jadid.add(list.get(i));
        }
        return jadid;
    }

    private List<ToolTip.Builder> quickSortZirNevisHa(List<ToolTip.Builder> newBuilder) {
        List<ToolTip.Builder> builderList = new ArrayList<>();
        for (int i = newBuilder.size() - 1; i >= 0; i--) {
            builderList.add(newBuilder.get(i));
        }
        return builderList;
    }

    private List<TextView> quickSortListViewSoalat(List<TextView> textViews) {
        List<TextView> list = new ArrayList<>();
        for (int i = textViews.size() - 1; i >= 0; i--) {
            list.add(textViews.get(i));
        }
        return list;

    }
}