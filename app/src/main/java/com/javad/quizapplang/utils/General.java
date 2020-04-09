package com.javad.quizapplang.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.ProfileActivity;
import com.javad.quizapplang.QuestionsActivity;
import com.javad.quizapplang.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import es.dmoral.toasty.Toasty;


/**
 * Created by emad on 6/29/2016.
 */
public class General {


    public static int iconSeda=R.drawable.circle_img_sound;
    public static String PREF_NAME = "telescopeSetting";
    public static String PREF_FONT_KEY = "TXTfont";
    private static ProgressDialog loading;
    private static Dialog loadingDialog;
    static Dialog di;
    private static General instance;
    Context context;
    private static Typeface typeface;
    private static Typeface carNumberTypeface;

    public General(Context context) {
        this.context = context;
    }

    public static General getInstance(Context context) {
        if (instance == null) {
            instance = new General(context);
        }
        return instance;
    }

    public static void successToast(Context c, String msg){

        Toasty.success(c, msg, Toast.LENGTH_SHORT, true).show();

    }

    public static void errorToast(Context c, String msg){

        Toasty.error(c, msg, Toast.LENGTH_SHORT, true).show();

    }

    public static void infoToast(Context c, String msg){

        Toasty.warning(c, msg, Toast.LENGTH_SHORT, true).show();

    }

    @SuppressLint({"CheckResult", "ResourceAsColor"})
    public static void wrongCToasty(String correct){
        QuestionsActivity.txtToast.setText("Incorrect");
        QuestionsActivity.txtCorrectAns.setVisibility(View.VISIBLE);
        QuestionsActivity.txtCorrectAns.setText(correct);
//        QuestionsActivity.imgIcon.setImageResource(R.drawable.icon_w);
//        QuestionsActivity.thumbIcon.setVisibility(View.GONE);
        QuestionsActivity.bgToast.setBackgroundResource(R.drawable.bg_red);

    }

    @SuppressLint({"CheckResult", "ResourceAsColor"})
    public static void correctCToasty(Context c){
        QuestionsActivity.txtToast.setText("Correct");
        QuestionsActivity.txtCorrectAns.setVisibility(View.GONE);
//        QuestionsActivity.imgIcon.setImageResource(R.drawable.icon_c);
//        QuestionsActivity.thumbIcon.setVisibility(View.VISIBLE);
        QuestionsActivity.bgToast.setBackgroundResource(R.drawable.bg_green);

    }

    public static Typeface getFont(Context context) {
        if (typeface == null) {
            SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            if (!pref.contains(PREF_FONT_KEY)) {
                pref.edit().putString(PREF_FONT_KEY, "IRANSansMobile.ttf").commit();
            }
            String fontName = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(PREF_FONT_KEY, "sahel.ttf");
            typeface= Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
        }
        return typeface;
    }

    public static Typeface getCarNumberFont(Context context) {
        if (carNumberTypeface == null) {
            String fontName = "B_Traffic.ttf";
            carNumberTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
        }
        return carNumberTypeface;
    }

    public static void changeBrightness(Context context, int brighness) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brighness);
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static void showMessagePopUp(Context context, String message, String btnMsg) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("")
                //.setMessage(context.getResources().getString(R.string.title_app_youShouldSignIn))
                .setCancelable(true)
                .setPositiveButton(btnMsg, null).show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTypeface(General.getFont(context));

        TextView textView3 = (TextView) dialog.findViewById(android.R.id.button1);
        textView3.setTypeface(General.getFont(context));
    }

    private void checkIfAnimationDone(AnimationDrawable anim){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 30;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDone(a);
                } else{
                    a.stop();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            a.start();
                            checkIfAnimationDone(a);
                        }
                    }, 1000);
                }
            }
        }, timeBetweenChecks);
    }

    public boolean isLoading() {
        if (loading != null) {
            if (loading.isShowing()) {
                return true;
            }
        }
        return false;
    }

    public void Clear() {
        loading = null;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static AlertDialog dialog(Context c, String title, String message, String posTxt, DialogInterface.OnClickListener posListener, String negTxt, DialogInterface.OnClickListener negListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posTxt, posListener);
        builder.setPositiveButton(negTxt, negListener);
        builder.create();
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static String getAppVersion(Context c) {
        try {
            PackageInfo packageInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public static int getScrWidth(Activity c) {
        Display display = c.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScrHeight(Activity c) {
        Display display = c.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static boolean DataOnOff(boolean status, Context context) {
        int bv = 0;
        try {
            if (bv == Build.VERSION_CODES.FROYO) {
                Log.e("internet_data", "first block");
                //android 2.2 versiyonu için
                Method dataConnSwitchmethod;
                Class<?> telephonyManagerClass;
                Object ITelephonyStub;
                Class<?> ITelephonyClass;

                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);

                telephonyManagerClass = Class.forName(telephonyManager
                        .getClass().getName());
                Method getITelephonyMethod = telephonyManagerClass
                        .getDeclaredMethod("getITelephony");
                getITelephonyMethod.setAccessible(true);
                ITelephonyStub = getITelephonyMethod.invoke(telephonyManager);
                ITelephonyClass = Class.forName(ITelephonyStub.getClass()
                        .getName());

                if (status) {
                    dataConnSwitchmethod = ITelephonyClass
                            .getDeclaredMethod("enableDataConnectivity");
                } else {
                    dataConnSwitchmethod = ITelephonyClass
                            .getDeclaredMethod("disableDataConnectivity");
                }
                dataConnSwitchmethod.setAccessible(true);
                dataConnSwitchmethod.invoke(ITelephonyStub);

            } else {
                // android 2.2 üstü versiyonlar için
                final ConnectivityManager conman = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                final Class<?> conmanClass = Class.forName(conman.getClass()
                        .getName());
                final Field iConnectivityManagerField = conmanClass
                        .getDeclaredField("mService");
                iConnectivityManagerField.setAccessible(true);
                final Object iConnectivityManager = iConnectivityManagerField
                        .get(conman);
                final Class<?> iConnectivityManagerClass = Class
                        .forName(iConnectivityManager.getClass().getName());
                final Method setMobileDataEnabledMethod = iConnectivityManagerClass
                        .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                setMobileDataEnabledMethod.setAccessible(true);
                setMobileDataEnabledMethod.invoke(iConnectivityManager, status);
            }

            return true;

        } catch (Exception e) {
            Log.e("Mobile Data", "error turning on/off data");
            e.printStackTrace();
            return false;
        }
    }

    public static void SetMobileDataEnabled(Context paramContext, boolean on) {
        try
        {

            ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            Method method = connectivityManager.getClass().getMethod("setMobileDataEnabled", Boolean.class);

            method.invoke(connectivityManager, on);
        }
        catch (NoSuchMethodException e)

        {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean GetMobileDataEnabled(Context paramContext) {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            Method method = connectivityManager.getClass().getMethod("getMobileDataEnabled");
            return (boolean)method.invoke(connectivityManager);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public static void turnGPSOn(Context c){
        String provider = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            c.sendBroadcast(poke);
        }
    }

    public static boolean canToggleGPS(Context c) {
        PackageManager pacman =c. getPackageManager();
        PackageInfo pacInfo = null;

        try {
            pacInfo = pacman.getPackageInfo("com.android.settings", PackageManager.GET_RECEIVERS);
        } catch (PackageManager.NameNotFoundException e) {
            return false; //package not found
        }

        if(pacInfo != null){
            for(ActivityInfo actInfo : pacInfo.receivers){
                //test if recevier is exported. if so, we can toggle GPS.
                if(actInfo.name.equals("com.android.settings.widget.SettingsAppWidgetProvider") && actInfo.exported){
                    return true;
                }
            }
        }

        return false; //default
    }

    public static Bitmap loadBitmapFromView(View v) {
        Log.e("lpw", "" + v.getLayoutParams().width);
        Log.e("lph", "" + v.getLayoutParams().height);
        v.layout(0,0 , 200, 200);
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static final String getDeviceID(Context context) {
        Log.e("deviceid_pieces", "35" +","+ (Build.BOARD.length() % 10) +","+ (Build.BRAND.length() % 10)
                +","+ (Build.CPU_ABI.length() % 10) +","+ (Build.DEVICE.length() % 10) +","+ (Build.MANUFACTURER.length() % 10)
                +","+ (Build.MODEL.length() % 10) +","+ (Build.PRODUCT.length() % 10));
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10)
                + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            Log.e("deviceid_serial", serial);
            Log.e("deviceid_hash_uuid", ""+m_szDevIDShort.hashCode());
            Log.e("deviceid_hash_serial", ""+serial.hashCode());
            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            // String needs to be initialized
            serial = "serial"; // some value
        }
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    public static void ShowLoading(Context context, boolean exist){

        if (exist) {
            try {

                if (di != null && di.isShowing())
                    di.dismiss();

                di = new Dialog(context, R.style.AppTheme_NoActionBar_FullScreen);
                di.setContentView(R.layout.loading_dialog);
                di.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                TextView txt = di.findViewById(R.id.txt);

                Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans_FaNum.ttf");
                txt.setTypeface(face);

                di.setCancelable(false);
                di.setCanceledOnTouchOutside(false);
                di.show();

            } catch (Exception e) {
                Log.e("exception", "آروم باش خخخخ");
                Log.e("exception", e.getMessage().toString());

            }
        }
    }

    public static void DismisLoading(final Context context, boolean exist){

        if (exist) {
            try {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (di == null) {
                            di = new Dialog(context, R.style.AppTheme_NoActionBar_FullScreen);
                            di.setContentView(R.layout.loading_dialog);
                            di.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        }

                        di.dismiss();

                    }
                }, 10);

            } catch (Exception e) {
                Log.e("exception", "آروم باش خخخخ");
                Log.e("exception", e.getMessage().toString());
            }
        }
    }

}
