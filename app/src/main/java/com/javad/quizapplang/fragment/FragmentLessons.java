package com.javad.quizapplang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.javad.quizapplang.App;
import com.javad.quizapplang.R;
import com.javad.quizapplang.adapter.PayLessonAdapter;
import com.javad.quizapplang.adapter.PayRecycleAdapter;
import com.javad.quizapplang.model.Lesson;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.modelgetlesson.ModelGetLesson;
import com.javad.quizapplang.utils.LinkWebService;
import com.javad.quizapplang.utils.PrefManager;
import com.javad.quizapplang.utils.Req;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentLessons extends Fragment {

    RecyclerView recyclerView;
    List<LessonOf> lessonOfList = new ArrayList<>();
    List<com.javad.quizapplang.model.Dars.Lesson> lessons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragments_list, null);

        recyclerView = v.findViewById(R.id.rv);
        getLessonFromServer();
        return v;
    }

    public void getLessonFromServer() {
        new Req(getContext(),LinkWebService.getLesson,new Req.onRequest()
        {
            @Override
            public void isSucess(String response)
            {
                Gson gson = new Gson();
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);

                ModelGetLesson modelGetLesson = gson.fromJson(response, ModelGetLesson.class);
                List<com.javad.quizapplang.model.modelgetlesson.Lesson> lessons = modelGetLesson.getData().getLessons();
                recyclerView.setAdapter(new PayLessonAdapter(getContext(),lessons));

            }

            @Override
            public void isFailed(String error)
            {

            }

            @Override
            public void OnProgress()
            {

            }

            @Override
            public Map<String, String> Paramets()
            {
                PrefManager prefManager = new PrefManager(getContext());
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("level", prefManager.getLevel());
                return stringStringMap;
            }
        });

//        new Request<Data>(getContext()).getLessonList(level, lesson_id, new CallBack<Data>() {
//            @Override
//            public void onRequestSuccessful(Data data) {
//
//                lessons =  data.getLessons();
////                data.getLessons();
//
//                for (int i = 0; i < lessons.size(); i++) {
//
//                    int id = lessons.get(i).getId();
//                    String title = lessons.get(i).getTitle();
//                    String name = lessons.get(i).getNameLesson();
//                    String imagePath = lessons.get(i).getUrlPic();
//                    String state = lessons.get(i).getStatus();
//                    String price = (String) lessons.get(i).getPrice();
//                    int level = Integer.parseInt(lessons.get(i).getLevel());
//                    int total_num = Integer.parseInt(String.valueOf(lessons.get(i).getCountQuestions()));
//                    Log.e("iddd", id + "");
////                        Toasty.normal(MainActivity.this, "لیست گرفته شد").show();
//
//                    setInDb(App.BASE_URL + imagePath, title, name, state, price, level, id, System.currentTimeMillis() + "", i
//                            , total_num);
//
//
//                }
//
//                lessonList = SQLite.select().from(LessonOf.class).queryList();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        list();
//
//                    }
//                }, 500);
//
//            }
//        });

    }

    private void setRecyclerView() {

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
//        PayLessonAdapter payRecycleAdapter = new PayLessonAdapter(getContext(), lessons);
//        recyclerView.setAdapter(payRecycleAdapter);

    }

}
