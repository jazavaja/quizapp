package com.javad.quizapplang.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.javad.quizapplang.Calendar.FDate;

/**
 * Created by SalmanPC1 on 12/18/2017.
 */

public class PrefManager {

    private static final String COIN = "Allcoin";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    // shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "sabteneshan";
    private String IfUSER_IS_LOGIN="ifUserLogin";

    public PrefManager(Context context) {
        mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**   define fields   */

    // Shared Preferences keys
    private static final String TOKEN = "private_token";
    private static final String LEVEL = "level";
    private static final String PERMISSIONID = "permission_id";
    private static final String STATE = "state";
    private static final String STATEUSER = "state_user";
    private static String tedadTayinSath="tayinSath";

    private static final String PHOTOPROFILE = "photo_profile";
    private static final String NAMEPROFILE = "name_profile";
    private static final String EDUCATIONPROFILE = "education_profile";
    private static final String MOBILEPROFILE = "mobile_profile";
    private static final String USERNAMEPROFILE = "username_profile";
    private static final String FIRSTTIMEPROFLE = "firstTime_profile";
    private static final String NAMEAPP = "name_app";
    private static final String FIRST_SEE_PROFILE="firstSeeProfile";
    private static final String FIRST_SEE_MAINPAGE="firstSeeMain";
    private static final String PLACEMENT_SHODE_YA_NA="placement";
    private static final String STEP1 = "step1";
    private static final String STEP2 = "step2";
    private static final String STEP3 = "step3";
    private static final String STEP4 = "step4";
    private static final String STEP5 = "step5";
    private static final String STEP6 = "step6";
    private static final String STEP7 = "step7";


    public void setPlacementShode(){
        editor.putBoolean(PLACEMENT_SHODE_YA_NA,true);
        editor.apply();
    }

    public  boolean isPlacementShodeYaNa() {
        return pref.getBoolean(PLACEMENT_SHODE_YA_NA,false);
    }

    public void addTayinSathAnjamShode(){
        editor.putInt(tedadTayinSath,getTdadTayinSathAnjmShd()+1);
    }

    public int getTdadTayinSathAnjmShd(){
        return pref.getInt(tedadTayinSath,0);
    }

    public String getFirstSeeMainpage() {
        return pref.getString(FIRST_SEE_MAINPAGE,"No");
    }

    public void setFirstSeeMainpage(){
        editor.putString(FIRST_SEE_MAINPAGE,"Yes");
        editor.apply();
    }

    /**   setter and getter  */


    public  String getFirstSeeProfile() {
        return pref.getString(FIRST_SEE_PROFILE,"No");
    }
    public void setFirstSeeProfile(){
        editor.putString(FIRST_SEE_PROFILE,"Yes");
        editor.apply();
    }


    public String getToken() {
//        Log.e("token", pref.getString(TOKEN, ""));
        return pref.getString(TOKEN, "");
    }

    public void setToken(String token) {
        Log.e("set_token", token);
        editor.putString(TOKEN, token);
        editor.apply();
    }


    public String getPermissionid() {
//        Log.e("token", pref.getString(PERMISSIONID, ""));
        return pref.getString(PERMISSIONID, "");
    }

    public void setPermissionid(String permission_id) {
        Log.e("set_permission_id", permission_id);
        editor.putString(PERMISSIONID, permission_id);
        editor.apply();
    }

    public String getLevel() {
//        Log.e("token", pref.getString(PERMISSIONID, ""));
        return pref.getString(LEVEL, "");
    }

    public void setLevel(String level) {
        Log.e("set_pid", level);
        editor.putString(LEVEL, level);
        editor.apply();
    }


    public String getPhotoprofile() {

        return pref.getString(PHOTOPROFILE, "");
    }

    public void setPhotoprofile(String photoprofile) {
//        Log.e("set_photoprofile", photoprofile);
        editor.putString(PHOTOPROFILE, photoprofile);
        editor.apply();
    }

    public String getNameprofile() {

        return pref.getString(NAMEPROFILE, "");
    }

    public void setNameprofile(String nameprofile) {
        Log.e("set_photoprofile", nameprofile);
        editor.putString(NAMEPROFILE, nameprofile);
        editor.apply();
    }

    public String getState() {

        return pref.getString(STATE, "");
    }

    public void setState(String state) {
        Log.e("set state", state);
        editor.putString(STATE, state);
        editor.apply();
    }

    public String getStateuser() {

        return pref.getString(STATEUSER, "");
    }

    public void setStateuser(String state) {
        Log.e("set state user", state+"");
        editor.putString(STATEUSER, state);
        editor.apply();
    }


    /**  STEPS OF SECTIONS  */
    public String getStep1() {

        return pref.getString(STEP1, "");
    }

    public void setStep1(String step1) {
        Log.e("set step1", step1+"");
        editor.putString(STEP1, step1);
        editor.apply();
    }

    public String getStep2() {

        return pref.getString(STEP2, "");
    }

    public void setStep2(String step2) {
        Log.e("set step2", step2+"");
        editor.putString(STEP2, step2);
        editor.apply();
    }

    public String getStep3() {

        return pref.getString(STEP3, "");
    }

    public void setStep3(String step3) {
        Log.e("set step3", step3+"");
        editor.putString(STEP3, step3);
        editor.apply();
    }

    public String getStep4() {

        return pref.getString(STEP4, "");
    }

    public void setStep4(String step4) {
        Log.e("set step4", step4+"");
        editor.putString(STEP4, step4);
        editor.apply();
    }

    public String getStep5() {

        return pref.getString(STEP5, "");
    }

    public void setStep5(String step5) {
        Log.e("set step5", step5+"");
        editor.putString(STEP5, step5);
        editor.apply();
    }

    public String getStep6() {

        return pref.getString(STEP6, "");
    }

    public void setStep6(String step6) {
        Log.e("set step6", step6+"");
        editor.putString(STEP6, step6);
        editor.apply();
    }

    public String getStep7() {

        return pref.getString(STEP7, "");
    }

    public void setStep7(String step7) {
        Log.e("set step7", step7+"");
        editor.putString(STEP7, step7);
        editor.apply();
    }

    public String getEducationprofile() {

        return pref.getString(EDUCATIONPROFILE, "");
    }

    public void setMobileprofile(String mobileprofile) {
        Log.e("set step7", mobileprofile+"");
        editor.putString(MOBILEPROFILE, mobileprofile);
        editor.apply();
    }

    public String getMobileprofile() {

        return pref.getString(MOBILEPROFILE, "");
    }

    public void setEducationprofile(String educationprofile) {
        Log.e("set eduction", educationprofile+"");
        editor.putString(EDUCATIONPROFILE, educationprofile);
        editor.apply();
    }

    public String getUsernameprofile() {

        return pref.getString(USERNAMEPROFILE, "");
    }

    public void setUsernameprofile(String usernameprofile) {
        Log.e("set step7", usernameprofile+"");
        editor.putString(USERNAMEPROFILE, usernameprofile);
        editor.apply();
    }

    public String getFirsttimeprofle() {

        return pref.getString(FIRSTTIMEPROFLE, "0");
    }

    public void setFirsttimeprofle(String firsttimeprofle) {
        Log.e("set firstprofile", firsttimeprofle+"");
        editor.putString(FIRSTTIMEPROFLE, firsttimeprofle);
        editor.apply();
    }

    public String getNameapp() {

        return pref.getString(NAMEAPP, "Beginner");
    }

    public void setNameapp(String nameapp) {
        Log.e("set nameapp", nameapp+"");
        editor.putString(NAMEAPP, nameapp);
        editor.apply();
    }

    public String getIfUserLogin() {
        return pref.getString(IfUSER_IS_LOGIN, "No");
    }

    public void setUserIsLogin() {
        editor.putString(IfUSER_IS_LOGIN,"Yes");
        editor.apply();

    }
    public void addOneCoin(){
        int s=Integer.parseInt(pref.getString(COIN,"0"))+1;
        editor.putString(COIN, String.valueOf(s));
    }
    public String getAllCoin(){
        return pref.getString(COIN,"0");
    }
    public void getAllPoint(){

    }
    public void addPoint(){

    }

}
