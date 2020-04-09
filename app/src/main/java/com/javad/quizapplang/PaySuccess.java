package com.javad.quizapplang;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.model.dbFlow.LevelOf_Table;
import com.javad.quizapplang.utils.PrefManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import es.dmoral.toasty.Toasty;

public class PaySuccess extends AppCompatActivity {

    WebView w;
    PrefManager prefManager;
    String lessonId = "", levelId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        levelId = bundle.getString("levelId");
        lessonId = bundle.getString("lessonId");

        Log.e("level id = ", levelId+"");
        Log.e("lesson id = ", lessonId+"");

        w = (WebView) findViewById(R.id.webview);
        prefManager = new PrefManager(this);

        w.getSettings().setDomStorageEnabled(true); // vase inke niaz ast ye seri ettelaAt zaruri ro dakhel khodesh zakhire kone

        try {

            w.loadUrl(App.BASE_URL + "/api/v1/pay/payment/"+prefManager.getToken());

            Toast.makeText(getApplicationContext(), "صفحه در حال بارگذاری میباشد. لطفا صبر کنید ...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "ارتباط برقرار نشد", Toast.LENGTH_SHORT).show();
        }

        w.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.equals(App.BASE_URL+"/api/v1/pay/success")){
                    Toasty.success(PaySuccess.this, "خرید موفقیت آمیز بود").show();
                    if (String.valueOf(lessonId).equals("all")){
                        updateLevelDb();
                    }else {
                        updateLessonDb();
                    }
                    finish();
                }else if (url.equals(App.BASE_URL+"/api/v1/pay/fail")){
                    Toasty.error(PaySuccess.this, "خرید موفقیت آمیز نبود").show();
                    finish();
                }

                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }
        });
    }

    private  void updateLessonDb(){
        // status = 2  => bought this lesson
        SQLite.update(LessonOf.class).set(LessonOf_Table.status.is("2"))
                    .where(LessonOf_Table.id.eq(Integer.valueOf(lessonId)))
                    .and(LessonOf_Table.level.eq(Integer.valueOf(levelId)))
                    .execute();
    }

    private void updateLevelDb(){
        SQLite.update(LevelOf.class).set(LevelOf_Table.price.is("0"))
                .where(LevelOf_Table.id.eq(Integer.valueOf(levelId)))
                .execute();
        LevelOf levelOf = SQLite.select().from(LevelOf.class).querySingle();
        Log.e("level = ", levelOf.getPrice());
    }
}