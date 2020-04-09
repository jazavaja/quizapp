package com.javad.quizapplang.service;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.javad.quizapplang.App;
import com.javad.quizapplang.SignUp;

import java.util.ArrayList;

/**
 * Created by AMIR on 5/28/2018.
 */

public class SmsReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

//        Toast.makeText(context, "getMessage", Toast.LENGTH_SHORT).show();

        // ==============================================================================d

        Bundle bundle = intent.getExtras();

            if (bundle != null) {

                Object[] objects = (Object[]) bundle.get("pdus");

                SmsMessage smsMessage = null;
                String content = null;
                assert objects != null;
                for (int i = 0; i < objects.length; i++) {

                    smsMessage = SmsMessage.createFromPdu((byte[]) objects[i]);
                    content = smsMessage.getMessageBody();

                }

                String number = smsMessage.getDisplayOriginatingAddress();
                Log.e("number", number);
                Log.e("content", content);

                if (number.equals("+98100020400")){

                    Intent intent1 = new Intent(App.context, SignUp.class);
                    intent1.putExtra("code",content);
                    App.context.startActivity(intent1);

                }

        }else {

//            Toast.makeText(context, "اجازه داده نشده", Toast.LENGTH_SHORT).show();

        }
    }

}
