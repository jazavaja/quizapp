package com.javad.quizapplang.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Reza on 01/10/2017.
 */

public class MyButton extends Button
{

    public MyButton(Context context)
    {
        super(context);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    public void init(Context context)
    {
        try {
            Typeface myFont = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans_FaNum.ttf");
            setTypeface(myFont ,0);
        }
        catch (Exception e) {
        }
    }
}
