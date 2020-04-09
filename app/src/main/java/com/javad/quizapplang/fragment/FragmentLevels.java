package com.javad.quizapplang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.javad.quizapplang.R;
import com.javad.quizapplang.adapter.PayRecycleAdapter;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.model.dbFlow.LevelOf_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class FragmentLevels extends Fragment{

    RecyclerView recyclerView;
    List<LevelOf> levelOfList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragments_list,null);

        recyclerView = v.findViewById(R.id.rv);

        levelOfList = SQLite.select().from(LevelOf.class).queryList();

        setRecyclerView();
        return v;
    }

    private void setRecyclerView(){

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        PayRecycleAdapter payRecycleAdapter = new PayRecycleAdapter(null,levelOfList,getActivity(),2);
        recyclerView.setAdapter(payRecycleAdapter);

    }

}
