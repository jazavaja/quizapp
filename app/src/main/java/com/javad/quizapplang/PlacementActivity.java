package com.javad.quizapplang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.adapter.PlacementAdapterGeneral;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.PrefManager;
import com.javad.quizapplang.utils.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.OPtsDet;
import com.javad.quizapplang.model.dbFlow.OPtsGen;
import com.javad.quizapplang.model.dbFlow.Placement;
import com.javad.quizapplang.model.placement.PlacementOnline;


public class PlacementActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    PlacementAdapterGeneral placementAdapter;
    ArrayList<PlacementOnline>  placementOnlines = new ArrayList<>();
    Placement placement;
    OPtsGen oPts;
    OPtsDet oPtsDet;
    List<Placement> placementsList = new ArrayList<>();
    List<OPtsDet> oPtsDets = new ArrayList<>();
    List<OPtsGen> oPtsGen = new ArrayList<>();
    int answersCorrect = 0, wrongAnswer = 0,dontAnswer=0;
    TextView soso, correct, wrong,set_level;
    String level , str_level;
    PrefManager prefManager;
    LinearLayout linearLayout,ll_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        prefManager=new PrefManager(this);
        prefManager.setUserIsLogin();
        String s=getIntent().getStringExtra("agian");
        if (s!=null)
        {
            if (!s.equals("yes")) {
                if (prefManager.isPlacementShodeYaNa()) {
                    startActivity(new Intent(PlacementActivity.this, MainActivity.class));
                    finish();
                }
            }

        }else {
            startActivity(new Intent(PlacementActivity.this, MainActivity.class));
            finish();
        }

        linearLayout = findViewById(R.id.ll);
        ll_result = findViewById(R.id.ll_result);
        recyclerView = findViewById(R.id.recycler_view);
        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);
        soso = findViewById(R.id.soso);
        set_level = findViewById(R.id.set_level);
        placementOnlines = new ArrayList<>();
        prefManager.addTayinSathAnjamShode();
    }

    private void getPlacementList(){

        placementsList = SQLite.select().from(Placement.class).queryList();

        if (placementsList.size() == 0) {

            new Request<Data>(this).getPlacementList(new CallBack<Data>() {
                @Override
                public void onRequestSuccessful(Data data) {

                    placement = new Placement();

                    try {


                        if (data != null) {
                            placementOnlines = (ArrayList<PlacementOnline>) data.getPlacements();

                            for (int i = 0; i < placementOnlines.size(); i++) {

                                String head = placementOnlines.get(i).getFullQuestion().getHead();
                                String body = placementOnlines.get(i).getFullQuestion().getBody().toString();
                                //                            String opts = Utils.implodeOpts(placementOnlines.get(i).getOpts());
                                saveOpts(placementOnlines.get(i).getOpts(), i, placementOnlines.get(i).getAnswers(),
                                        placementOnlines.get(i).getFullQuestion().getBody());

                                String[] strings = new String[placementOnlines.get(i).getAnswers().size()];
                                for (int j = 0; j < placementOnlines.get(i).getAnswers().size(); j++) {
                                    strings[j] = String.valueOf(placementOnlines.get(i).getAnswers().get(j));
                                }

                                String answer = Utils.implodeArrayUsingForLoop(strings, ",");
                                String type = placementOnlines.get(i).getType();

                                //                    Log.e("placement getHead00 "+i+" = ", placementOnlines.get(i).getFullQuestion().getHead());
                                //                    Log.e("placement getBody00 "+i+" = ", placementOnlines.get(i).getFullQuestion().getBody().toString());
                                //                    Log.e("placement getAnswer00 "+i+" = ", Utils.implodeArrayUsingForLoop(strings,","));
                                //                    Log.e("placement getOpts00 "+i+" = ", Utils.implodeOpts(placementOnlines.get(i).getAnswer()));
                                //                    Log.e("placement gettype00 "+i+" = ", placementOnlines.get(i).getPlacement_id());

                                getList(head, body, answer, type);

                            }

                            //                        Toast.makeText(PlacementActivity.this, "successfull", Toast.LENGTH_SHORT).show();
                            initList();

                        } else {

                            Toast.makeText(PlacementActivity.this, "هیچ سوالی وجود ندارد", Toast.LENGTH_SHORT).show();

                        }
                    }catch (Exception e){
//                        Toast.makeText(PlacementActivity.this, "exception", Toast.LENGTH_SHORT).show();
                        initList();
                    }
                }
            });
        }else {


            initList();
//            Toast.makeText(this, "poreee", Toast.LENGTH_SHORT).show();

        }
    }

    private void getList(String head, String body, String answer, String type){

        placement = new Placement();
        placement.setHead(head);
        placement.setBody(body);
        placement.setAnswer(answer);
        placement.setType(type);
        placement.save();

    }

    public void initList() {

        placementsList = SQLite.select().from(Placement.class).queryList();

//        for (int i = 0; i < placementsList.size(); i++) {
//            Log.e("placement getHead "+i+" = ", placementsList.get(i).getHead());
//            Log.e("placement getBody "+i+" = ", placementsList.get(i).getBody());
//            Log.e("placement getAnswer "+i+" = ", placementsList.get(i).getAnswer());
//            Log.e("placement getTypes "+i+" = ", placementsList.get(i).getType());
//        }

        oPtsDets = SQLite.select().from(OPtsDet.class).queryList();

        for (int i = 0; i < oPtsDets.size(); i++) {

            Log.e("oPtsDets oPtsDets.size", String.valueOf(oPtsDets.size()));
            Log.e("oPtsDets getAnswer", oPtsDets.get(i).getOpts_name());
            Log.e("oPtsDets Id_opt_gen", String.valueOf(oPtsDets.get(i).getId_opt_gen()));
            Log.e("oPtsDets Id_placement", String.valueOf(oPtsDets.get(i).getId_placement()));
            Log.e("oPtsDets ", "-------==------------==------------==------------==------------==------------==-----");
        }

        oPtsGen = SQLite.select().from(OPtsGen.class).queryList();

        for (int i = 0; i < oPtsGen.size(); i++) {

            Log.e("oPtsDets oPtsDets.size", String.valueOf(oPtsGen.size()));
            Log.e("oPtsDets getAnswer", String.valueOf(oPtsGen.get(i).getAnswer()));
            Log.e("oPtsDets Id_opt_gen", String.valueOf(oPtsGen.get(i).getOpts_num()));
            Log.e("oPtsDets Id_placement", String.valueOf(oPtsGen.get(i).getId_placement()));
            Log.e("oPtsDets body", String.valueOf(oPtsGen.get(i).getBody()));
            Log.e("oPtsDets User_answer", String.valueOf(oPtsGen.get(i).getUser_answer()));
            Log.e("oPtsDets ", "=================================================================================");
        }

        // placement lis for meain header
        // oPtsGen list for body , answer
        //oPtsDets list for options
        placementAdapter = new PlacementAdapterGeneral(this, this, placementsList, oPtsGen, oPtsDets,
                new PlacementAdapterGeneral.Result() {
            @Override
            public void onClickListener() {
//                ll_result.setVisibility(View.VISIBLE);

                checkingOut();

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(1,0);
        recyclerView.setAdapter(placementAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    private void checkingOut(){

        List<OPtsGen> oPtsGenList = SQLite.select().from(OPtsGen.class).queryList();

        for (int i = 0; i < oPtsGenList.size(); i++) {

            Log.e("answer", String.valueOf(oPtsGenList.get(i).getAnswer()));
            Log.e("user", String.valueOf(oPtsGenList.get(i).getUser_answer()));

            if (oPtsGenList.get(i).getUser_answer() == oPtsGenList.get(i).getAnswer()){

                answersCorrect ++;

            }else if (oPtsGenList.get(i).getUser_answer() != 8 &&
                    oPtsGenList.get(i).getUser_answer() != oPtsGenList.get(i).getAnswer()){

                wrongAnswer ++;

            }else{

                dontAnswer ++;

            }
        }

        Intent intent = new Intent(PlacementActivity.this,ResultTest.class);
        intent.putExtra("correct",answersCorrect);
        startActivity(intent);

//        int wrongAns = wrongAnswer - answersCorrect;
        Log.e("Num correct", String.valueOf(answersCorrect));
        Log.e("Num wrong", String.valueOf(wrongAnswer));
        Log.e("Num donAnswer", String.valueOf(dontAnswer));

//        wrong.setText(String.valueOf((wrongAnswer)));
//        correct.setText(answersCorrect+"");
//        soso.setText(dontAnswer+"");
//
//        set_level.setText("شما باید از سطح " + getScore(answersCorrect) + " شروع کنید");

//        Toast.makeText(context, "Num correct = "+ answersCorrect +  ", Num wrongAnswer = "+ wrongAnswer +
//                ",Num dontAnswer = "+ dontAnswer , Toast.LENGTH_SHORT).show();

    }

    private void saveOpts(List<List<String>> list, int id, List<Integer> listAnswer, List<String> listBody){

        for (int i = 0; i < list.size() ; i++) {
            oPts = new OPtsGen();
            oPts.setId_placement(id);
            oPts.setOpts_num(list.get(i).get(0));
            oPts.setAnswer(listAnswer.get(i));
            try {
                oPts.setBody(listBody.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            oPts.setUser_answer(8);

            for (int j = 0; j < list.get(i).size(); j++) {
                oPtsDet = new OPtsDet();
                oPtsDet.setOpts_name(list.get(i).get(j));
                oPtsDet.setId_placement(id);
                oPtsDet.setId_opt_gen(i);

                oPtsDet.save();
            }

            oPts.save();

        }
    }

    public void checking(View view) {

//        List<OPtsGen> oPtsGenList = SQLite.select().from(OPtsGen.class).where(OPtsGen_Table.user_answer.is(OPtsGen_Table.answer)).queryList();
        List<OPtsGen> oPtsGenList = SQLite.select().from(OPtsGen.class).queryList();

        int answersCorrect = 0;
        int wrongAnswer = 0;
        int dontAnswer = 0;

        for (int i = 0; i < oPtsGenList.size(); i++) {

            Log.e("answer", String.valueOf(oPtsGenList.get(i).getAnswer()));
            Log.e("user", String.valueOf(oPtsGenList.get(i).getUser_answer()));

            if (oPtsGenList.get(i).getUser_answer() == oPtsGenList.get(i).getAnswer()){

                answersCorrect ++;

            }else if (oPtsGenList.get(i).getUser_answer() != 8){

                wrongAnswer ++;

            }else{

                dontAnswer ++;

            }
        }

        Log.e("Num correct", String.valueOf(answersCorrect));
        Log.e("Num wrong", String.valueOf(wrongAnswer));
        Log.e("Num donAnswer", String.valueOf(dontAnswer));

        Toast.makeText(this, "Num correct = "+ answersCorrect +  ", Num wrongAnswer = "+ wrongAnswer +
                ",Num dontAnswer = "+ dontAnswer , Toast.LENGTH_SHORT).show();

    }

    public void lessons(View view) {
        prefManager.setPlacementShode();
        startActivity(new Intent(this, MainActivity.class));

    }

    public void placements(View view) {

        linearLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        getPlacementList();

    }

    private String getScore(int score){

        if (score >= 0 && score <= 17){

            level = "Beginner";
            str_level = "1";

        }else if (score >= 18 && score <= 29){

            level = "pre-intermediate";
            str_level = "2";

        }else if (score >= 30 && score <= 39){

            level = "intermediate";
            str_level = "3";

        }else if (score >= 40 && score <= 50){

            level = "Upper-intermediate";
            str_level = "4";

        }

        return level;
    }

    public void start(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
