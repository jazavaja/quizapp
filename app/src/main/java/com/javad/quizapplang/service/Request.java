package com.javad.quizapplang.service;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import com.javad.quizapplang.App;
import com.javad.quizapplang.MainActivity;
import com.javad.quizapplang.model.SignUpLoginData;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.data.ResultModel;
import com.javad.quizapplang.model.questionsOnline.Question;
import com.javad.quizapplang.utils.General;
import com.javad.quizapplang.utils.JsonCreator;
import com.javad.quizapplang.utils.PrefManager;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SalmanPC1 on 12/26/2017.
 */

public class Request<ResultType> {

    Context mContext;
    private String privateToken;
    private ServiceApi serviceApi;

    public static int res;

    PrefManager pref;


    public Request(Context context) {
        mContext = context;
        createApi();
        privateToken = new PrefManager(context).getToken();


        pref = new PrefManager(context);
    }
    private void createApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .build();

        serviceApi = new Retrofit.Builder()
                .baseUrl(App.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceApi.class);
    }


    private void callRequest(Call call, final CallBack callBack) {

        if (!General.getInstance(mContext).isNetworkAvailable()){

            General.errorToast(mContext, "اینترنت متصل نیست");
            /**
             return; -> equals end and after that can't do
             */
            return;
        }

        try {
            General.ShowLoading(mContext, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        call.enqueue(new Callback<ResultModel<ResultType>>() {
            @Override
            public void onResponse(Call<ResultModel<ResultType>> call, Response<ResultModel<ResultType>> response) {
                try {
                    General.DismisLoading(mContext, true);
                    MainActivity.swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Toast.makeText(mContext, "response", Toast.LENGTH_SHORT).show();
                switch (response.code()) {
                    case 200:
                        switchResults(response.body().getResult(), null, response.body().getData(), callBack);
                        break;
                    case 404:
                        Log.e("error", "page not found");
                        break;
                    case 500:
                        Log.e("error", "internal server error");
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResultModel<ResultType>> call, Throwable t) {
                try {
                    General.DismisLoading(mContext, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                t.printStackTrace();
                Log.e("error", "error in connection");
                General.errorToast(mContext, "خطا در ارتباط با سرور");

                String message = t.getMessage();
                Log.e("failure", message);
            }
        });
    }

    private void callRequestResponseBody(Call<ResponseBody> call, final CallBack<String> callBack) {
//        General.ShowLoading(mContext, "لطفا صبر کنید");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                General.DismissLoading(mContext);
//                Toast.makeText(mContext, "response", Toast.LENGTH_SHORT).show();
                switch (response.code()) {
                    case 200:
                        String responseStr;
                        String dataString;
                        int result;
                        try {
                            responseStr = response.body().string();
                            JSONObject json = new JSONObject(responseStr);
                            result = json.getInt("result");
                            dataString = json.getJSONObject("data").toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error", "error in get data");
                            return;
                        }
                        switchResults(result, dataString, null, callBack);
                        break;
                    case 404:
                        Log.e("error", "page not found");
                        break;
                    case 500:
                        Log.e("error", "internal server error");
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                General.DismissLoading(mContext);
                Log.e("error", t.getMessage().toString());

                Log.e("error", "error in connection response");
                t.printStackTrace();
            }
        });

    }

    private void switchResults(int result, String dataString, ResultType data, CallBack callBack) {

        Log.e("resultiiiiiii", "result == "+ result);

        switch (result) {
            case 1:
                if (dataString != null) {
                    callBack.onRequestSuccessful(dataString);
                    Log.e("result123", dataString);
                }
                else {
                    callBack.onRequestSuccessful(data);
                    Log.e("result12", String.valueOf(data));
                }
                break;

            case 2:
                Log.e("error", "2");
                break;

            // public errors
            case 11:
                Log.e("error", "token invalid");
                break;
            case 12:
                Log.e("error", "service invalid");
                break;
            case 13:
                Log.e("error", "data invalid");
                break;
            case 99:
                Log.e("error", "calling service error");
                break;
            case 100:
                Log.e("error", "runtime error");
                break;

            // sign up errors
            case 20:
                Log.e("error", "user already exists in sign up");
                General.errorToast(mContext, "نام کاربری تکراری است");
                break;
            case 21:
                Log.e("error", "the insert operation failed in sign up");
                General.errorToast(mContext, "کد وارد شده اشتباه است");
                break;

            // login errors
            case 30:
                Log.e("error", "authentication Failed in login");
                General.errorToast(mContext, "نام کاربری اشتباه است");
                break;

            case 31:
                Log.e("error", "authentication Failed in login");
                General.errorToast(mContext, "کلمه عبور اشتباه است");
                break;

            // permission errors
            case 40:
                Log.e("error", "failed edit permission form admin");
                break;

            case 41:
                Log.e("error", "failed don't permission form admin");
                break;

            // city errors
            case 50:
                Log.e("error", "can’t fetch data from DB in City");
                break;

            // role errors
            case 60:
                Log.e("error", "can’t fetch data from DB in Role");
                break;

            // get job category errors
            case 70:
                Log.e("error", "can’t fetch data from DB in get job category");
                break;

            // set account errors
            case 80:
                Log.e("error", "the insert operation failed in set account");
                break;

            // get account errors
            case 90:
                Log.e("error", "mobile or trackcode is invalid in get account");

                break;

            // get account errors
            case 97:
                Log.e("error", "Already Registered");
                Toasty.error(mContext,"این شماره وجود دارد").show();

            // get account errors
            case 600:
                Log.e("error", "error set level");
                Toasty.error(mContext,"خطا در").show();

                break;

                // category job
            case 71:
                Log.e("OK", "yes");
                res = 71;
                callBack.onRequestSuccessful(null);
//                Toast.makeText(mContext, "select", Toast.LENGTH_SHORT).show();
//                pref.setResult(String.valueOf(result));
                break;
        }
    }

    /* list of services */
    /* ========================================================================= */

    public void getCode(String mobile, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token", "phone_number")
                .values(pref.getToken() ,mobile)
                .create();

        Call<ResultModel<SignUpLoginData>> call = serviceApi.getCode(jsonObject.toString());
        callRequest(call, callBack);

    }

    public void setCode(String code, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token", "code")
                .values(pref.getToken() ,code)
                .create();

        Call<ResultModel<SignUpLoginData>> call = serviceApi.setCode(jsonObject.toString());
        callRequest(call, callBack);

    }

    public void register(String username,String name,String education, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                    .keys("token", "username", "name", "education")
                .values(pref.getToken() ,username, name, education)
                .create();

        Call<ResultModel<SignUpLoginData>> call = serviceApi.registered(jsonObject.toString());
        callRequest(call, callBack);

    }

    /* list of services of main management */

    /* ========================================================================= */

    public void getQuestionList(String lessonId, String section, CallBack<ResultType> callBack) {

        Log.e("lessonId", lessonId);
        Log.e("section", section);
        JSONObject jsonObject = new JsonCreator()
                .keys("token","lesson_id", "section")
                .values(pref.getToken(), lessonId, section)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.getQuestions(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLevelList(CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token")
                .values(pref.getToken())
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.getLevelList(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getInfoProfile(CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token")
                .values(pref.getToken())
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.getInfoProfile(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRanking(CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token")
                .values(pref.getToken())
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.getRanking(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editInfoProfile(String fullname, String username, String education,
                                String pathPhoto, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token", "fullname","username","education","photo")
                .values(pref.getToken(), fullname,username, education, pathPhoto)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.setEditProfile(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getProgress(String lessonId, String section, CallBack<ResultType> callBack) {

        Log.e("lessonId", lessonId);
        Log.e("section", section);
        JSONObject jsonObject = new JsonCreator()
                .keys("token","lesson_id", "section")
                .values(pref.getToken(), lessonId, section)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.getProgress(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProgress(String lesson_id, String section, String question_id, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token","lesson_id","section", "question_id")
                .values(pref.getToken(), lesson_id, section, question_id)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.setProgress(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScore(String lesson_id,String section, String score, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token","lesson_id","section","score")
                .values(pref.getToken(), lesson_id,section, score)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.setScore(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLevel(String level, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token","level")
                .values(pref.getToken(), level)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.setLevel(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPlacementList(CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token")
                .values(pref.getToken())
                .create();

        Log.e("jsonObject",jsonObject.toString());

        Call<ResultModel<Data>> call = serviceApi.getPlacementList(jsonObject.toString());
        callRequest(call, callBack);

    }

//    public void getUnit(String section, String level, CallBack<ResultType> callBack) {

    public void getLessonList(String level , String lesson_id, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token","level","lesson_id")
                .values(pref.getToken(),level,lesson_id)
                .create();

        Log.e("jsonObject",jsonObject.toString());

        Call<ResultModel<Data>> call = serviceApi.getLessonList(jsonObject.toString());
        callRequest(call, callBack);

    }

    public void setNewSession(String mobile, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token", "mobile")
                .values(pref.getToken(), mobile)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.setNewSession(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setChangePermission(String userId, String permission_id, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token", "userId", "permision_id")
                .values(pref.getToken(), userId, permission_id)
                .create();

        Log.e("jsonObject", jsonObject.toString());

        Call<ResultModel<Data>> call = null;
        try {
            call = serviceApi.setChangePermission(jsonObject.toString());
            callRequest(call, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* list of services project management */
    /* ========================================================================= */

    public void submit_buy(String lesson_id,String levelId,String discount, CallBack<ResultType> callBack) {

        JSONObject jsonObject = new JsonCreator()
                .keys("token", "lesson_id", "level", "discount")
                .values(pref.getToken(), lesson_id, levelId, discount)
                .create();

        Call<ResultModel<Data>> call = serviceApi.submitrequest(jsonObject.toString());
        callRequest(call, callBack);

    }

}
