package com.javad.quizapplang.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.javad.quizapplang.App;
import com.javad.quizapplang.Calendar.ChangeDate;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.dbFlow.OPtsGen;
import com.javad.quizapplang.model.dbFlow.OPtsGen_Table;
import com.javad.quizapplang.model.dbFlow.TimeChart;
import com.javad.quizapplang.model.dbFlow.TimeChart_Table;
import com.javad.quizapplang.model.dbFlow.questions.QPathPhoto;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Utils {

    public Context context;
    public Activity activity;
    public DisplayMetrics displayMetrics;
    public int height, width;
    public float xdpi, ydpi;
    public List<Bitmap> imagesList = new ArrayList<>();
    int startTime, day, month, year;
    String day_week = "";
    public TextToSpeech tts_en;

    public Utils(Activity activity){
        this.activity = activity;
    }

    public Utils(Context context){
        this.context = context;
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public void screen(){
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        xdpi = displayMetrics.xdpi;
        ydpi = displayMetrics.ydpi;
    }

    public static String implodeArrayUsingForLoop(String[] arrayToImplode,String separator){

        if (arrayToImplode.length == 0) {
            //empty array return empty string
            return "";
        }

        if(arrayToImplode.length < 2){
            //only 1 item
            return arrayToImplode[0];
        }

        StringBuffer stringbuffer = new StringBuffer();
        for (int i=0; i < arrayToImplode.length; i++) {
            if (i != 0) stringbuffer.append(separator);
            stringbuffer.append(arrayToImplode[i]);
        }

        //return the implode string
        return stringbuffer.toString();
    }

    public static String[] array (List<String> list){

        String[] array = new String[list.size()];
        list.toArray(array); // fill the array

        return array;
    }

    public static String[] explode(String str){

        String[] strings = str.split("],");
        String sd = null;
        String[] finall = new String[strings.length];

        for (int i = 0; i < strings.length; i++) {
            Log.e("i = "+ i, strings[i]);

            sd = strings[i].replace("[", "");
            sd = sd.replace("]", "");

//            if (sd.startsWith(",")){
//                sd = sd.replace(",", "");
//            }

            Log.e("optsoooooooooo", sd);

            finall[i] = sd;
        }

        finall = sd.split(",");

        return finall;
    }


    public static String implodeOpts(List<List<String>> lists){

        return Arrays.toString(lists.toArray());
    }

    public List<Bitmap> crop(int column,int row){
        Bitmap bitmapOrg = BitmapFactory.decodeResource(activity.getResources(), R.drawable.pici);

        int w = bitmapOrg.getWidth()/column;
        int h = bitmapOrg.getHeight()/row;

        for (int i = 0; i < row; i++) {

            Log.e("ii", String.valueOf(h));

            for (int j = 0; j < column; j++) {

                Bitmap croppedBmp1 = Bitmap.createBitmap(bitmapOrg, j*w, i*h,
                        w , h);

                imagesList.add(croppedBmp1);
                Log.e("jj", String.valueOf(j*h));
                Log.e("jj", String.valueOf(croppedBmp1));

            }
        }

        return imagesList;
    }

    public void startTime(){

        Date date = Calendar.getInstance().getTime();
        long a = System.currentTimeMillis()/1000L;
        String fjfj = String.valueOf(a);
        String[] sss = ChangeDate.getCurrentDateTimeString().split(" ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String s = simpleDateFormat.format(date);
        String[] strings = s.split("-");

        startTime = (int) System.currentTimeMillis();
        day = Integer.parseInt(strings[0]);
        month = Integer.parseInt(strings[1]);
        year = Integer.parseInt(strings[2]);

        day_week = sss[0];

//        int dayd = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (dayd){
//
//            case Calendar.SATURDAY:
//                day_week = "شنبه";
//                break;
//
//            case Calendar.SUNDAY:
//                day_week = "یکشنبه";
//                break;
//
//            case Calendar.MONDAY:
//                day_week = "دوشنبه";
//                break;
//
//            case Calendar.TUESDAY:
//                day_week = "سه شنبه";
//                break;
//
//            case Calendar.WEDNESDAY:
//                day_week = "چهارشنبه";
//                break;
//
//            case Calendar.THURSDAY:
//                day_week = "پنج شنبه";
//                break;
//
//            case Calendar.FRIDAY:
//                day_week = "جمعه";
//                break;
//        }


        Log.e("rooooooooz", s+"///"+day_week);
        Log.e("aaaaaa", ""+((Long.valueOf(fjfj) - 1544125063L)/60));
        Log.e("time rorooz", sss[0]);
//        Toast.makeText(activity, "start", Toast.LENGTH_SHORT).show();
        Log.e("startTime", "start = " + startTime + "// day = " + day + "// month = " + month + "// year = " + year);


    }

    public void endTime(int time){

//        int endTime = (int) System.currentTimeMillis();
//        int time = endTime - startTime;
        int oldTime = 0;
        boolean exist = false;

        List<TimeChart> timeCharts = SQLite.select().from(TimeChart.class).
                where(TimeChart_Table.month.eq(month))
                .queryList();
        Log.e("Time out", "end = "+time);

        for (int i = 0; i < timeCharts.size(); i++) {

            Log.e("Time out", "day = "+day +"/// query day = "+timeCharts.get(i).getDay());
            Log.e("Time out", "month = "+month +"/// query month = "+timeCharts.get(i).getMonth());

            if(timeCharts.get(i).getDay() != day){
                exist = false;
            }else {
                exist = true;
                oldTime = timeCharts.get(i).getTime();

            }

        }

        if (!exist){

            Log.e("Time of new day", "time chart in for");
            TimeChart timeChart = new TimeChart();
            timeChart.setTime(time);
            timeChart.setDay(day);
            timeChart.setMonth(month);
            timeChart.setYear(year);
            timeChart.setDay_week(day_week);
            timeChart.save();

        }else {

            int s = time + oldTime;

            Log.e("Time updated", "enddd = " + s);
            SQLite.update(TimeChart.class).set(TimeChart_Table.time.is(s))
                    .where(TimeChart_Table.month.eq(month))
                    .and(TimeChart_Table.day.eq(day))
                    .execute();

            Log.e("Time updated", "end = " + time + "// old time = " + oldTime);

        }
    }

    public TextToSpeech tts(final String text, final float speed, final String sex){

        tts_en = new TextToSpeech(App.context, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result = tts_en.setLanguage(Locale.UK);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    } else{

                        ConvertTextToSpeech(text, speed,sex);
                        Log.e("error", "en convert");
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        }, "com.google.android.tts");

        return tts_en;

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ConvertTextToSpeech(String content_en, float speed, String sex) {
        // TODO Auto-generated method stub

        if(content_en==null||"".equals(content_en))
        {
            content_en = "نمیتواند قبول کند";
        }

//        Toast.makeText(getActivity(), "pitch == " + pitch, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "speechSpeed/10 == " + speed, Toast.LENGTH_SHORT).show();
        Set<String> a=new HashSet<>();

        if (sex.equals("m")){

            a.add("male");//here you can give male if you want to select male voice.
            Voice v= new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200
                    ,true,a);
            tts_en.setVoice(v);
            tts_en.setSpeechRate(0.8f);

        }else{

            tts_en.setSpeechRate(speed);
//        tts_en.setSpeechRate((speed) * 0.25f);
//        HashMap<String, String> myHashRender = new HashMap();
//        myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, content_en);
//        tts_en.synthesizeToFile(content_en, myHashRender, path);
        }

        tts_en.speak(content_en, TextToSpeech.QUEUE_FLUSH, null);
    }

    public boolean isConnected() {
//استفاده از کانکت منیجر برای مطمن شدن از دسترسی و عدم دسترسی به اینترنت
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        if (net != null && net.isAvailable() && net.isConnected()) {
            return true;
        } else {
            return false;
        }
//برای استفاده از کانکت منیجر باید دسترسی اون رو در منی فست فعال کنیم.
    }

    public void mediaPlayer(int sound){

        MediaPlayer mediaPlayer = MediaPlayer.create(App.context,sound);
        mediaPlayer.start();

    }

    public static long getFreeRamSize(){
        ActivityManager activityManager = (ActivityManager) App.context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        assert activityManager != null;
        activityManager.getMemoryInfo(memoryInfo);
        long availableMegs = memoryInfo.availMem/1048576L;

        return availableMegs;
    }

    @SuppressLint("SdCardPath")
    public void downloadimage(String uRl, String filename, QPathPhoto qPathPhoto) {

        File path = new File("/sdcard/darsbazi/nomedia/");
        if(!path.exists()) {
            path.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(context.getResources().getString(R.string.app_name))
                .setDescription("در حال دانلود:" + filename + "")
                .setDestinationInExternalPublicDir("", "/darsbazi/nomedia/"+ filename + "");
        if (mgr != null) {
            mgr.enqueue(request);
        }

        qPathPhoto.setPath("/sdcard/darsbazi/nomedia/"+ filename + "");
        qPathPhoto.save();
    }

}