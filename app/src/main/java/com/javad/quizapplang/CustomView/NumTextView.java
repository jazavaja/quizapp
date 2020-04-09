package com.javad.quizapplang.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Reza on 09/10/2016.
 */
public class NumTextView extends TextView {

    public NumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public NumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSans_FaNum.ttf");
        setTypeface(tf ,0);
    }
}
