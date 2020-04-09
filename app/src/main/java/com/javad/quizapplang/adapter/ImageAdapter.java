package com.javad.quizapplang.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.QuestionsActivity;
import com.javad.quizapplang.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by luongvo on 14/05/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private LayoutInflater inflater;
    ImageView curView = null;
    TextView txtView = null;
    int answered = 0;
    public static int countPair = 0;
    List<Bitmap> bitmaps = new ArrayList<>();
    GoNextQuestion goNextQuestion;

    public interface GoNextQuestion{
        void next();
    }

    final int[] drawable = new int[] {
            R.drawable.ic_add_circle_black_24dp,
            R.drawable.ic_check_circle,
            R.drawable.ic_clear,
            R.drawable.ic_file_download,
            R.drawable.ic_toc,
            R.drawable.ic_pause,
            R.drawable.ic_mic,
            R.drawable.ic_refresh
    };
    String[] items = new String[] {
            "hello",
            "how",
            "are",
            "you",
            "please",
            "hot",
            "hand",
            "cat",
            "سلام",
            "چطور",
            "هستی",
            "تو",
            "لطفا",
            "گرم",
            "دست",
            "گربه"
    };

    int[] pos = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
    int currentPos = -1;


    public ImageAdapter(Context context, String[] items, int[] pos, int answered, List<Bitmap> bitmaps, GoNextQuestion goNextQuestion) {
        this.pos = pos;
        this.answered = answered;
        this.items = items;
        this.bitmaps = bitmaps;
        this.context = context;
        this.goNextQuestion = goNextQuestion;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder")
        final View view = inflater.inflate(R.layout.row_puzel,parent,false);

        final ImageView imageView = view.findViewById(R.id.img_puzzle);
        final TextView txt = view.findViewById(R.id.txt_puzzle);
        final FrameLayout fl_root = view.findViewById(R.id.fl_root);

        imageView.setImageBitmap(bitmaps.get(position));

        if (answered == 1){

            imageView.setImageResource(R.drawable.blueColor);
            txt.setText(items[position]);

        }else {

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
//                    playSound();
                    if (currentPos < 0) {
                        currentPos = position;
                        curView = (ImageView) v;
                        txtView = txt;

//                        Toast.makeText(context, currentPos+"/" + position, Toast.LENGTH_SHORT).show();
//                    imageView.setImageResource(drawable[pos[position]]);
                        imageView.setImageResource(R.drawable.blueColor);
                        txt.setText(items[position]);

                        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(fl_root, "scaleX", 1f, 0f);
                        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(fl_root, "scaleX", 0f, 1f);
                        oa1.setInterpolator(new DecelerateInterpolator());
                        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                        oa1.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

//                            imageView.setImageResource(drawable[pos[position]]);
                                imageView.setImageResource(R.drawable.blueColor);
                                txt.setText(items[position]);
                                oa2.start();

                            }
                        });
                        oa1.start();

                    } else {

                        if (currentPos == position) {

                            ((ImageView) v).setImageBitmap(bitmaps.get(position));

                        } else if (pos[currentPos] != pos[position]) {

                            QuestionsActivity.blank.setVisibility(View.VISIBLE);

                            final ObjectAnimator oa1 = ObjectAnimator.ofFloat(fl_root, "scaleX", 1f, 0f);
                            final ObjectAnimator oa2 = ObjectAnimator.ofFloat(fl_root, "scaleX", 0f, 1f);
                            oa1.setInterpolator(new DecelerateInterpolator());
                            oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                            oa1.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);

//                                imageView.setImageResource(drawable[pos[position]]);
                                    imageView.setImageResource(R.drawable.blueColor);
                                    txt.setText(items[position]);
                                    oa2.start();
                                }
                            });
                            oa1.start();

                            Log.e("current = ",position + "");
                            Log.e("position = ",pos[position] + "");

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    final ObjectAnimator oa1 = ObjectAnimator.ofFloat(fl_root, "scaleX", 1f, 0f);
                                    final ObjectAnimator oa2 = ObjectAnimator.ofFloat(fl_root, "scaleX", 0f, 1f);
                                    oa1.setInterpolator(new DecelerateInterpolator());
                                    oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                                    oa1.addListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);

//                                        ((ImageView) v).setImageResource(R.drawable.golzar);
                                            imageView.setImageBitmap(bitmaps.get(position));
                                            curView.setImageBitmap(bitmaps.get(currentPos+1));
                                            txtView.setText("");
                                            txt.setText("");
                                            oa2.start();
                                        }
                                    });
                                    oa1.start();

//                                    Toast.makeText(context, "Not Match!", Toast.LENGTH_LONG).show();
                                    QuestionsActivity.blank.setVisibility(View.GONE);

                                }
                            }, 500);

                        } else {
//                        imageView.setImageResource(drawable[pos[position]]);
                            txt.setText(items[position]);
                            imageView.setImageResource(R.drawable.blueColor);
                            curView.setImageResource(R.drawable.blueColor);
                            imageView.setEnabled(false);
                            curView.setEnabled(false);
                            countPair++;
                            Log.e("count pair = ", countPair + "");
                            if (countPair == (items.length)/2) {
//                                Toast.makeText(context, "You Win!", Toast.LENGTH_LONG).show();
                                goNextQuestion.next();
                            }
                        }
                        currentPos = -1;
                    }
                }
            });
        }

        return view;
    }

    private void playSound() {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.correct_s);
        mp.start();
    }
}
