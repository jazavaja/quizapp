package com.javad.quizapplang.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Req {
    public static PrefManager prefManager;
//    public static String rootAsli = "http://jafari.xyz";
//    public static String root = "http://jafari.xyz/api/v1";
//    public static String faildMessage = "";


    public Req(Context context, String url, final onRequest onRequest) {
        onRequest.OnProgress();
        prefManager = new PrefManager(context);
        final StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onRequest.isSucess(response);
                        Log.e("Response", response);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onRequest.isFailed(error.toString());
                        Log.e("ErrorResponse", error.toString());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return onRequest.Paramets();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


    public static void onFailed(View view) {
        Snackbar.make(view, "عملیات ناموفق بود دوباره تلاش کنید", Snackbar.LENGTH_SHORT).show();
    }

    public static void onFailedCustom(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void failedSetView(View progress, View fail, View mainOrSuccess) {
        progress.setVisibility(View.GONE);
        fail.setVisibility(View.VISIBLE);
        mainOrSuccess.setVisibility(View.GONE);
    }

    public static void sucessSetView(View progress, View fail, View mainOrSuccess) {
        progress.setVisibility(View.GONE);
        fail.setVisibility(View.GONE);
        mainOrSuccess.setVisibility(View.VISIBLE);
    }
    public  interface onRequest {
        public void isSucess(String response);
        public void isFailed(String error);
        public  void OnProgress();
        public Map<String,String> Paramets();
    }

}
