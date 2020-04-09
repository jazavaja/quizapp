package com.javad.quizapplang;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.javad.quizapplang.model.dbFlow.TimeChart;
import com.javad.quizapplang.model.dbFlow.TimeChart_Table;
import com.javad.quizapplang.utils.PrefManager;
import com.javad.quizapplang.utils.Utils;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;



/**
 * Created by SalmanPC3 on 9/16/2018.
 */

public class App extends Application{

    public static Context context;
    private static App instance;
    public static final String BASE_URL = "http://panel.darsbazi.com";

    boolean enter = false;
    Utils utils;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Realm.init(this);

//        RealmConfiguration realmConfiguration=new
//        Realm.setDefaultConfiguration();
        context = getApplicationContext();
//        Pushe.initialize(this,true);
        instance = this;
        utils = new Utils(context);

        FlowManager.init(new FlowConfig.Builder(this).build());


        PrefManager prefManager = new PrefManager(context);
        prefManager.setToken("d8e2c7baf307beeebb483ee521ba6ea2");

        getTime();


//        if (!enter){
//
//            utils.startTime();
//            enter = true;
//
//        }else {
//
//            utils.endTime();
//
//        }

    }

    private void getTime(){
        List<TimeChart> timeCharts = SQLite.select().from(TimeChart.class).groupBy(TimeChart_Table.month).queryList();
        int spendingTime = 0;
        Log.e("second = ", timeCharts.size() + "");
        for (int i = 0; i < timeCharts.size(); i++) {
            int secondTime = timeCharts.get(i).getTime();
            spendingTime = spendingTime + secondTime;
            Log.e("second = ", spendingTime+"// day = "+timeCharts.get(i).getDay()+
                    "// year = "+timeCharts.get(i).getYear()+"// month = "+timeCharts.get(i).getMonth());
        }
    }


}
