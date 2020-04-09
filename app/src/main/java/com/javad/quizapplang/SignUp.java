package com.javad.quizapplang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.javad.quizapplang.model.SignUpLoginData;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.PrefManager;

import net.cachapa.expandablelayout.ExpandableLayout;

import es.dmoral.toasty.Toasty;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    ExpandableLayout ex_password;
    EditText et_mobile, et_password, et_username, et_name, et_education;
    Button btnSignUp, btnSetCode, btn_info;
    PrefManager prefManager;
    LinearLayout ll_info, sign_up;
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        prefManager = new PrefManager(this);

//        if (prefManager.getIfUserLogin().equals("Yes")){
            startActivity(new Intent(SignUp.this, PlacementActivity.class));
//        }

//        Toasty.info(App.context,"این نسخه آزمایشی صرفا جهت نشان دادن عملکرد برنامه است و روی ظاهر آن کار نشده است").show();

        ll_info = findViewById(R.id.info);
        sign_up = findViewById(R.id.sign_up);
        ex_password = findViewById(R.id.ex_password);
        et_mobile = findViewById(R.id.et_mobile);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);
        et_name = findViewById(R.id.et_name);
        et_education = findViewById(R.id.et_education);
        btnSignUp = findViewById(R.id.btn_send_number);
        btnSetCode = findViewById(R.id.btn_setcode);
        btn_info = findViewById(R.id.btn_info);


        btnSetCode.setOnClickListener(this);
        btn_info.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if (prefManager.getIfUserLogin().equals("Yes")) {
            startActivity(new Intent(SignUp.this, PlacementActivity.class));
            finish();
        }

        if (prefManager.getState().equals("-1")){

            isState2();

        }else if (prefManager.getState().equals("-2")){

            isState2();

        }

    }

    private void sendNumber(){

//        Toasty.normal(this, "ارسال شماره شد").show();
        String mobile = et_mobile.getText().toString();

        if (mobile.length() != 11){

            Toasty.warning(this, "شماره وارد شده اشتباه میباشد").show();

        }else {
            new Request<SignUpLoginData>(this).getCode(mobile, new CallBack<SignUpLoginData>() {
                @SuppressLint("CheckResult")
                @Override
                public void onRequestSuccessful(SignUpLoginData data) {

                    String token = data.getToken();
                    prefManager.setToken(token);
                    Log.e("sendNumberToken", token);
                    Toasty.success(SignUp.this, data.getMessage());

//                    prefManager.setState("-1");
                    isState2();

                }
            });
        }
    }

    private void isState2(){

        ex_password.expand();
        btnSignUp.setVisibility(View.GONE);
        et_mobile.setVisibility(View.GONE);
        btn_info.setVisibility(View.GONE);
        btnSetCode.setVisibility(View.VISIBLE);

    }

    private void isState3(){

        btnSignUp.setVisibility(View.GONE);
        sign_up.setVisibility(View.GONE);
        btn_info.setVisibility(View.VISIBLE);
        ll_info.setVisibility(View.VISIBLE);
        btnSetCode.setVisibility(View.GONE);

    }

    private void sendCode(){

        String code = et_password.getText().toString();
//        Toasty.normal(this, "ارسال کد شد").show();

        new Request<SignUpLoginData>(this).setCode(code, new CallBack<SignUpLoginData>() {
            @SuppressLint("CheckResult")
            @Override
            public void onRequestSuccessful(SignUpLoginData data) {

                String stateUser = data.getStateUser();
                prefManager.setStateuser(stateUser);

                Log.e("user state ", stateUser + "");
                Toasty.success(SignUp.this, "با موفقیت وارد شدید").show();
                startActivity(new Intent(SignUp.this, PlacementActivity.class));

//                if (prefManager.getStateuser().equals("0")){
//
//                    prefManager.setState("-2");
//                    isState3();
//
//                }else {
//
//                    prefManager.setLevel(data.getLevel());
//                    Log.e("QWEEEElevel",data.getLevel());
//                    if (data.getLevel().equals("-1")) {
//
//                        startActivity(new Intent(SignUp.this, PlacementActivity.class));
//
//                    }
////                    else {
////
////                        startActivity(new Intent(SignUp.this, MainActivity.class));
////
////                    }
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            finish();
//                        }
//                    }, 500);

            }
        });
    }

    private void personalityInfo(){

//        Toasty.normal(this, "ارسال اطلاعات شد").show();
        String username = et_username.getText().toString();
        String name = et_name.getText().toString();
        String education = et_education.getText().toString();

        new Request<SignUpLoginData>(this).register(username, name, education, new CallBack<SignUpLoginData>() {
            @SuppressLint("CheckResult")
            @Override
            public void onRequestSuccessful(SignUpLoginData data) {

                Toasty.success(SignUp.this,"با موفقیت وارد شدید").show();
                startActivity(new Intent(SignUp.this, PlacementActivity.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        finish();

                    }
                },1000);

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_send_number:
                sendNumber();
                break;

            case R.id.btn_setcode:
                sendCode();
                break;

            case R.id.btn_info:
                personalityInfo();
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            code = getIntent().getExtras().getString("code");
            et_password.setText(code);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
