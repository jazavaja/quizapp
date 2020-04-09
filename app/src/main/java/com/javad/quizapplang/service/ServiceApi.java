package com.javad.quizapplang.service;

import com.javad.quizapplang.model.SignUpLoginData;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.data.ResultModel;
import com.javad.quizapplang.model.questionsOnline.Question;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by SalmanPC1 on 12/26/2017.
 */

public interface ServiceApi {

    @FormUrlEncoded
    @POST("/api/v1/pay/submit_request")
    Call<ResultModel<Data>> submitrequest(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/auth/setcode")
    Call<ResultModel<SignUpLoginData>> setCode(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/auth/register")
    Call<ResultModel<SignUpLoginData>> registered(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/auth/getcode")
    Call<ResultModel<SignUpLoginData>> getCode(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/set_progress")
    Call<ResultModel<Data>> setProgress(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/set_score")
    Call<ResultModel<Data>> setScore(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/set_level")
    Call<ResultModel<Data>> setLevel(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/get_progress")
    Call<ResultModel<Data>> getProgress(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/get_score")
    Call<ResultModel<Data>> getScore(@Field("data") String Data);

    /**  profile  **************************************************************** */

    @FormUrlEncoded
    @POST("/api/v1/levels")
    Call<ResultModel<Data>> getLevelList(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/profile")
    Call<ResultModel<Data>> getInfoProfile(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/edit")
    Call<ResultModel<Data>> setEditProfile(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/users/top")
    Call<ResultModel<Data>>     getRanking(@Field("data") String Data);

    /**    **************************************************************** */


    @FormUrlEncoded
    @POST("/api/v1/placement")
    Call<ResultModel<Data>>
    getPlacementList(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/questions")
    Call<ResultModel<Data>> getQuestions(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/lessons")
    Call<ResultModel<Data>> getLessonList(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/user/list")
    Call<ResultModel<Data>> getMangerList(@Field("data") String Data);

    // sessions

    @FormUrlEncoded
    @POST("/api/v1/session/list")
    Call<ResultModel<Data>> getSessionsList(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/session/creat")
    Call<ResultModel<Data>> setNewSession(@Field("data") String Data);

    @FormUrlEncoded
    @POST("/api/v1/user/changePremision")
    Call<ResultModel<Data>> setChangePermission(@Field("data") String Data);

    /**  project Management  *****************************************************************  */
}
