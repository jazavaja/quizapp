package com.javad.quizapplang.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Reza on 09/10/2016.
 */
public class EnTextView extends TextView {

    public EnTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EnTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EnTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf");
        setTypeface(tf ,0);
    }
}
