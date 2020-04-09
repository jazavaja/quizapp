package com.javad.quizapplang;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.javad.quizapplang.adapter.LessonAdapter;

import com.javad.quizapplang.model.Dars.Dars;
import com.javad.quizapplang.model.modelgetlesson.Lesson;
import com.javad.quizapplang.model.Level;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.model.dbFlow.LevelOf_Table;
import com.javad.quizapplang.model.dbFlow.ProgressSec;
import com.javad.quizapplang.model.dbFlow.StepSubLessonOf;
import com.javad.quizapplang.model.dbFlow.SubLessonOf;
import com.javad.quizapplang.model.dbFlow.TimeChart;
import com.javad.quizapplang.model.dbFlow.TimeChart_Table;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.LinkWebService;
import com.javad.quizapplang.utils.PrefManager;
import com.javad.quizapplang.utils.Req;
import com.javad.quizapplang.utils.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Lesson> lessons;
    RecyclerView recyclerview;
    List<LessonOf> lessonList = new ArrayList<>();
    PrefManager prefManager;
    LevelOf levelOf;
    Utils utils;
    LessonAdapter lessonAdapter;
    public static SwipeRefreshLayout swipeRefreshLayout;
    TextView txtTitle;

    public static Thread thread;
    public static Handler handler;
    public static int time;
    private int WRITE_PERMISSION=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new Utils(this);
        prefManager = new PrefManager(this);
        swipeRefreshLayout = findViewById(R.id.container);
        txtTitle = findViewById(R.id.title);
        txtTitle.setText(prefManager.getNameapp());
        time();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {

                    LevelOf levelOf = SQLite.select().from(LevelOf.class).querySingle();
                    List<LevelOf> levelOfList = SQLite.select().from(LevelOf.class).queryList();
                    List<LessonOf> lessonOf = SQLite.select().from(LessonOf.class).queryList();
                    String last_lessonId = "";
                    for (int i = 0; i < lessonOf.size(); i++) {
                        last_lessonId = String.valueOf(lessonOf.get(i).getId());
                    }

                    assert levelOf != null;
                    req();
                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, "از اتصال اینترنت مطمئن شوید", Toast.LENGTH_SHORT).show();

                }
            }
        });

//            new Request<Data>(this).setScore(String.valueOf(5),String.valueOf(1), String.valueOf(0), new CallBack<Data>() {
//                @Override
//                public void onRequestSuccessful(Data data) {
//
//                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
//
//                }
//            });

        prefManager.setState("1");

        recyclerview = findViewById(R.id.recyclerview);

        getLevels();

        lessonList = SQLite.select().from(LessonOf.class).queryList();
        list();

        SQLite.update(LessonOf.class).set(LessonOf_Table.permission.is(String.valueOf(1)))
                .where(LessonOf_Table.title.eq(String.valueOf(1)))
                .execute();
    }

    public void req() {
        new Req(this, LinkWebService.getLesson, new Req.onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson = new Gson();
                Dars dars = gson.fromJson(response, Dars.class);
                List<com.javad.quizapplang.model.Dars.Lesson> list = dars.getData().getLessons();
                for (int i = 0; i < list.size(); i++) {
                    int id = list.get(i).getId();
                    String title = list.get(i).getTitle();
                    String name = list.get(i).getNameLesson();
                    String urlPic = list.get(i).getUrlPic();
                    String state = list.get(i).getStatus();
                    String price = (String) list.get(i).getPrice();
                    int level = Integer.parseInt(list.get(i).getLevel());
                    int total_num = Integer.parseInt(String.valueOf(list.get(i).getCountQuestions()));
                    String describe = list.get(i).getDiscription();
                    try
                    {
                        permissonWrite();
                        setInDb(App.BASE_URL + urlPic, describe, title, name, state, price, level, id
                                , System.currentTimeMillis() + "", i, total_num
                        );
                    }
                    catch (Exception e)
                    {

                    }
                }
                lessonList = SQLite.select().from(LessonOf.class).queryList();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                Map<String, String> stringMap = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getToken());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stringMap.put("data", jsonObject.toString());
                return stringMap;
            }
        });
    }

    private void permissonWrite(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_PERMISSION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    private void getLevels() {

        final List<LevelOf> levelOfList = SQLite.select().from(LevelOf.class).queryList();

        if (levelOfList.size() == 0) {

            new Request<Data>(this).getLevelList(new CallBack<Data>() {
                @Override
                public void onRequestSuccessful(Data data) {

                    ArrayList<Level> levelList = (ArrayList<Level>) data.getLevels();

                    for (int i = 0; i < levelList.size(); i++) {
                        String name = data.getLevels().get(i).getName();
                        int id = data.getLevels().get(i).getId();
                        int active = data.getLevelUser();
                        int permission = data.getLevels().get(i).getPermision();
                        int totalScore = data.getLevels().get(i).getTotalScore();
                        int countLesson = data.getLevels().get(i).getCountLesson();
                        int price = data.getLevels().get(i).getPrice();
                        setLevelDB(name, id, active, totalScore, permission, price, countLesson);
                    }
                    int active = data.getLevelUser();

                    List<LevelOf> levelOfList1 = SQLite.select().from(LevelOf.class)
                            .where(LevelOf_Table.id.eq(active))
                            .queryList();

                    for (int i = 0; i < levelOfList1.size(); i++) {
                        LevelOf levelOf1 = levelOfList1.get(i);
                        Log.e("level db1 = ", "name = " + levelOf1.getNum_level() + "// id = " + levelOf1.getId());
                        Log.e("level db2 = ", "score = " + levelOf1.getTotal_score()
                                + "// permission = " + levelOf1.getPermission());
                    }

                    getLessnList(String.valueOf(levelOfList1.get(0).getActiveLevel()), "");
                    prefManager.setNameapp(levelOfList1.get(0).getNum_level());
                    txtTitle.setText(prefManager.getNameapp());

                }
            });

        }

    }

    private void setLevelDB(String name, int id, int active, int totalScore, int permission, int price, int countLesson) {
        levelOf = new LevelOf();
        levelOf.setNum_level(name);
        levelOf.setId(id);
        levelOf.setTotal_score(totalScore);
        levelOf.setActiveLevel(active);
        levelOf.setPermission(permission);
        levelOf.setPrice(String.valueOf(price));
        levelOf.setNum_lesson(String.valueOf(countLesson));
        levelOf.save();
    }

    private void getLessnList(String level, String lesson_id) {

        lessonList = SQLite.select().from(LessonOf.class).queryList();

        if (lessonList.size() == 0) {
            req();
//            new Request<Data>(this).getLessonList(level, lesson_id, new CallBack<Data>() {
//                @Override
//                public void onRequestSuccessful(Data data) {
//
//                    lessons = (ArrayList<Lesson>) data.getLessons();
//
//                    for (int i = 0; i < lessons.size(); i++) {
//
//                        int id = lessons.get(i).getId();
//                        String title = lessons.get(i).getTitle();
//                        String name = lessons.get(i).getNameLesson();
//                        String imagePath = lessons.get(i).getUrlPic();
//                        String state = lessons.get(i).getStatus();
//                        String price = lessons.get(i).getPrice();
//                        int level = Integer.parseInt(lessons.get(i).getLevel());
//                        int total_num = Integer.parseInt(lessons.get(i).getTotalNum());
//                        Log.e("iddd", id + "");
////                        Toasty.normal(MainActivity.this, "لیست گرفته شد").show();
//
//                        setInDb(App.BASE_URL + imagePath, title, name, state, price, level, id, System.currentTimeMillis() + "", i
//                                , total_num);
//
//
//                    }
//
//                    lessonList = SQLite.select().from(LessonOf.class).queryList();
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            list();
//
//                        }
//                    }, 500);
//
//                }
//            });

        }

        list();

    }

    private void list() {

        recyclerview.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        lessonAdapter = new LessonAdapter(lessonList, this);
        recyclerview.setAdapter(lessonAdapter);

        lessonAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        super.onResume();
        lessonAdapter.notifyDataSetChanged();
    }

    public void setInDb(String uRl, String Description, String title, String name, String state, String price, int level, int id, String filename, int position, int num) {
        String underImg = "";

        File path = new File(Environment.getExternalStorageDirectory() + "/darsbazi/nomedia/");
        if (!path.exists()) {
            path.mkdirs();
        }

        if (uRl.contains("&")) {
            String[] strings = uRl.split("&");
            uRl = strings[0];
            underImg = "&" + strings[1];
        }

        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(getResources().getString(R.string.app_name))
                .setDescription("در حال دانلود:" + filename + "")
                .setDestinationInExternalPublicDir("", "/darsbazi/nomedia/" + filename + "");
        if (mgr != null) {
            mgr.enqueue(request);
        }

        // save in database
        LessonOf lesson = new LessonOf();
        lesson.setId(id);
        lesson.setDescription(Description);
        lesson.setTitle(title);
        lesson.setName(name);
        lesson.setImgPath(Environment.getExternalStorageDirectory() + "/darsbazi/nomedia/" + filename + "" + underImg);
        lesson.setLevel(level);
        lesson.setStep("0");
        lesson.setHas_coin("0");
        lesson.setPermission("0");
        lesson.setTotal_num(num);
        lesson.setTotal_dl("0");
        lesson.setStatus(state);
        lesson.setPrice(price);
        lesson.save();

        SubLessonOf subLessonOf = new SubLessonOf();
        subLessonOf.setSec1(0);
        subLessonOf.setSec2(0);
        subLessonOf.setSec3(0);
        subLessonOf.setSec4(0);
        subLessonOf.setSec5(0);
        subLessonOf.setSec6(0);
        subLessonOf.setSec7(0);
        subLessonOf.save();

        setStepValue(position, id, position);

    }

    @Override
    protected void onStart() {
        super.onStart();
        utils.startTime();
    }

    private synchronized void stopThread(Thread theThread) {
        if (theThread != null) {
            theThread = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "destroyed = "+time, Toast.LENGTH_SHORT).show();
        utils.endTime(time);
        stopRunning();
        getTime();
    }

    private void getTime() {
        List<TimeChart> timeCharts = SQLite.select().from(TimeChart.class).groupBy(TimeChart_Table.month).queryList();
        int spendingTime = 0;
        Log.e("second = ", timeCharts.size() + "");
//        for (int i = 0; i < timeCharts.size(); i++) {
        int last = timeCharts.size() - 1;
        int secondTime = timeCharts.get(last).getTime();
        spendingTime = spendingTime + secondTime;
        Log.e("second = ", spendingTime + "// day = " + timeCharts.get(last).getDay() +
                "// year = " + timeCharts.get(last).getYear() + "// month = " + timeCharts.get(last).getMonth());
//        }


        List<TimeChart> timeChartss = SQLite.select().from(TimeChart.class).queryList();
        Log.e("count = ", timeChartss.size() + "");
        for (int i = 0; i < timeChartss.size(); i++) {
            Log.e("count = ", timeChartss.get(i).getTime() + "");
            Log.e("day = ", timeChartss.get(i).getDay() + "");
        }

    }

    public void spendTime(View view) {

        startActivity(new Intent(this, SpendingTime.class));
//        Toasty.info(App.context,"میزان تایم سپری شده، تست").show();

    }

    public void clickii(View view) {
//        Toasty.info(App.context,"خانه").show();
        startActivity(new Intent(this, PayList.class));
    }

    public void profile(View view) {
//        Toasty.info(App.context,"پروفایل").show();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void cup(View view) {
        startActivity(new Intent(this, Ranking.class));
    }

    private void setStepValue(int positon, int lessonId, int subLessonId) {

        StepSubLessonOf stepSubLessonOf = new StepSubLessonOf();
        stepSubLessonOf.setLesson_id(lessonId);
        stepSubLessonOf.setSub_lesson_id(subLessonId);
        stepSubLessonOf.setStep1("0");
        stepSubLessonOf.setStep2("0");
        stepSubLessonOf.setStep3("0");
        stepSubLessonOf.setStep4("0");
        stepSubLessonOf.setStep5("0");
        stepSubLessonOf.setStep6("0");
        stepSubLessonOf.setStep7("0");
        stepSubLessonOf.save();

        List<StepSubLessonOf> stepSubLessonOfList = SQLite.select().from(StepSubLessonOf.class)
                .queryList();

        StepSubLessonOf stepSubLessonOf1 = stepSubLessonOfList.get(positon);

        Log.e("lesson_id", stepSubLessonOf1.getLesson_id() + "");
        Log.e("sub lesson id", stepSubLessonOf1.getSub_lesson_id() + "");
        Log.e("step1", stepSubLessonOf1.getStep1() + "");
        Log.e("step2", stepSubLessonOf1.getStep2() + "");
        Log.e("step3", stepSubLessonOf1.getStep3() + "");
        Log.e("step4", stepSubLessonOf1.getStep4() + "");
        Log.e("step5", stepSubLessonOf1.getStep5() + "");
        Log.e("step6", stepSubLessonOf1.getStep6() + "");
        Log.e("step7", stepSubLessonOf1.getStep7() + "");


        ProgressSec progressSec = new ProgressSec();
        progressSec.setProgress1(0);
        progressSec.setProgress2(0);
        progressSec.setProgress3(0);
        progressSec.setProgress4(0);
        progressSec.setProgress5(0);
        progressSec.setProgress6(0);
        progressSec.setProgress7(0);
        progressSec.save();

    }

    boolean running = true;
    int i = 1;

    private void time() {

//       how to thread stop ?????????????????????
        handler = new Handler();

//        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    i++;
                    try {
                        Thread.sleep(5 * 1000);
                        final int finalI = i * 5;
                        time = finalI;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(MainActivity.this, "second = " + finalI, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        thread.start();

    }

    public void stopRunning() {
        running = false;
        time();
    }
}
