package com.javad.quizapplang;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.quizapplang.adapter.ImageAdapter;
import com.javad.quizapplang.adapter.Type13Adapter;
import com.javad.quizapplang.adapter.Type13GridAdapter;
import com.javad.quizapplang.adapter.Type15RecycleAdapter;
import com.javad.quizapplang.adapter.Type18RecycleAdapter;
import com.javad.quizapplang.adapter.Type2Adapter;
import com.javad.quizapplang.adapter.Type6Adapter;
import com.javad.quizapplang.adapter.Type6GrideAdapter;
import com.javad.quizapplang.adapter.Type91011RecycleAdapter;
import com.javad.quizapplang.helperClasses.Fun15;
import com.javad.quizapplang.helperClasses.Fun16;
import com.javad.quizapplang.helperClasses.FunType10;
import com.javad.quizapplang.helperClasses.FunType11;
import com.javad.quizapplang.helperClasses.FunType8And9;
import com.javad.quizapplang.helperClasses.OptionsSet;
import com.javad.quizapplang.helperClasses.OptionsSetSub;
import com.javad.quizapplang.helperClasses.OptionsSetSub2;
import com.javad.quizapplang.model.Type12Model;
import com.javad.quizapplang.model.Type15;
import com.javad.quizapplang.model.Type6;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.model.dbFlow.LevelOf_Table;
import com.javad.quizapplang.model.dbFlow.ProgressSec;
import com.javad.quizapplang.model.dbFlow.ProgressSec_Table;
import com.javad.quizapplang.model.dbFlow.StepSubLessonOf;
import com.javad.quizapplang.model.dbFlow.StepSubLessonOf_Table;
import com.javad.quizapplang.model.dbFlow.questions.QAnswers;
import com.javad.quizapplang.model.dbFlow.questions.QBodies;
import com.javad.quizapplang.model.dbFlow.questions.QBodies_Table;
import com.javad.quizapplang.model.dbFlow.questions.QFullText;
import com.javad.quizapplang.model.dbFlow.questions.QFullText_Table;
import com.javad.quizapplang.model.dbFlow.questions.QOptions;
import com.javad.quizapplang.model.dbFlow.questions.QOptions_Table;
import com.javad.quizapplang.model.dbFlow.questions.QPathPhoto;
import com.javad.quizapplang.model.dbFlow.questions.QPathPhoto_Table;
import com.javad.quizapplang.model.dbFlow.questions.Questions_Of;
import com.javad.quizapplang.model.dbFlow.questions.Questions_Of_Table;
import com.javad.quizapplang.type13Recycle.SwipeAndDragHelper;
import com.javad.quizapplang.type13Recycle.Type14Adapter;
import com.javad.quizapplang.utils.General;
import com.javad.quizapplang.utils.PrefManager;
import com.javad.quizapplang.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nex3z.flowlayout.FlowLayout;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment implements TextToSpeech.OnUtteranceCompletedListener {

    private static final String WITCH_TYPE = "Which type";
    View view;
    Activity activity;
    TextView txt_answer;
    Utils utils;
    GridView gridView;
    RecyclerView rv_answer;
    //    public static StringBuilder stringBuilder;
    public static String string = "";
    TextView word, txt_head;
    ImageView image;
    List<String> listGridAlphabet = new ArrayList<>();
    List<String> typeList = new ArrayList<>();
    Type2Adapter adapter;
    RecyclerView rv_type6;
    Type6Adapter type6Adapter;
    List<String> type6List = new ArrayList<>();
    GridView grid_type6;
    Type6GrideAdapter type6GridAdapter;
    List<Type6> type6GridList = new ArrayList<>();
    List<Questions_Of> questions_ofList = new ArrayList<>();
    List<QAnswers> qAnswers = new ArrayList<>();
    List<QFullText> qFullTexts = new ArrayList<>();
    List<QBodies> qBodies = new ArrayList<>();
    List<QOptions> qOptions = new ArrayList<>();
    List<QPathPhoto> qPathPhotos = new ArrayList<>();
    Questions_Of questions_of;
    public static String section, lessonId;
    int position, sublessonid, activeid, lessonSize, questionsSize;
    String mic1 = null, mic2 = null;
    List<StepSubLessonOf> stepSubLessonOfList = new ArrayList<>();
    PrefManager prefManager;
    String step;
    public static int selectType = -1;
    String alph = "abcdefghijklmnopqrstuvwxyz?/,";
    TextToSpeech textToSpeech;

    @Override
    public void onStop() {
        super.onStop();
        try {
            textToSpeech.shutdown();
            textToSpeech.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            textToSpeech.shutdown();
            textToSpeech.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        utils = new Utils(getActivity());
        prefManager = new PrefManager(getActivity());

        Bundle bundle = getActivity().getIntent().getExtras();
        assert bundle != null;
        // اینا مقادیری هست که از اداپتر lesson Adapter میاد
        section = bundle.getString("section");
        lessonId = bundle.getString("lesson_id");
        position = bundle.getInt("position");
        sublessonid = bundle.getInt("sublessonid");
        activeid = bundle.getInt("activeid");
        lessonSize = bundle.getInt("lessonsize");
        questionsSize = bundle.getInt("questionssize");
        Log.e("position = ", position + "");
        Log.e("section = ", section + "");
        Log.e("sublessonid = ", sublessonid + "");
        Log.e("lessonid = ", lessonId + "");
        Log.e("activeid = ", activeid + "");
        Log.e("lessonsize = ", lessonSize + "");
        Log.e("questionsSize = ", questionsSize + "");

        levelOf = SQLite.select().from(LevelOf.class).querySingle();
        int tttt = levelOf.getTotal_score();
        QuestionsActivity.point.setText(tttt + "");
        QuestionsActivity.seke.setText(prefManager.getAllCoin());

        try {
            // اپدیت میکنه توی هر بخش تا کجا پیش رفته
            setSectionProgress(QuestionsActivity.questionNum);
            stepSubLessonOfList = SQLite.select().from(StepSubLessonOf.class)
                    .where(StepSubLessonOf_Table.lesson_id.eq(Integer.valueOf(lessonId)))
//                .and(StepSubLessonOf_Table.sub_lesson_id.eq(sublessonid))
                    .queryList();
            Log.e("stepSubLessonOfList = ", stepSubLessonOfList.get(position).getStep1() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // شرط برای اینکه متوجه بشه باید ایتم اول و بخش اول رو باز کنه و اگر بخش اول چواب داده نشده بود اجازه ورود به بقیه بخشارو نده

        //    برای اجازه گرفتن کاربره که ایا اجازه داره وارد بشه یا ن
        //   اگر بخش قبلی جواب داده نبود و این بخشی ک میخوای واردش بشی، بخش 1 نبود => میگه اجازه ورود ندارید
//        if (!(getSectionStep((Integer.parseInt(section)-1)).equals("-1")) && !(section.equals("1"))){

        // اگه خواستین تست کنین بخشای مختلفو این خطو فعال کنید
        if (section.equals("0")) {
            Log.e("spet 1 = ", stepSubLessonOfList.get(position).getStep1());
            Toast.makeText(getActivity(), "شما اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
//            getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
            getActivity().finish();

        } else {
            // init lesson of list
            // اینجا وضعیت بخش رو مشخص میکنه که دفعه اوله سوالو میبینه یا ...
            step = getSectionStep(Integer.parseInt(section));
            Log.e("step = ", step);

            // این دوتا آیتم هم در اول gone میشه زمانی میاد ک طرف ب کل سوالای اون بخش جواب بده و فقط روی دکمه بعدی و قبلی بزنه
            QuestionsActivity.next.setVisibility(View.GONE);
            QuestionsActivity.previous.setVisibility(View.GONE);

            // اینم اصل توضیح زیرشه
            levelOf = SQLite.select().from(LevelOf.class).querySingle();
            Log.e("total score = ", levelOf.getTotal_score() + "");

            /**   step =
             *
             *      0 => regular question
             *      1 => has wrong answer in that
             *     -1 => all question correct answer
             *
             *    state =
             *    -1 => has wrong answer
             *    1  => all questions is correct
             *
             * */

            // goOn دکمه ادامه است که بعد از جواب دادن ظاهر میشه
            // check دکمه بررسی هستش ک چک میکنه درست جواب داده شده یا ن
            QuestionsActivity.goOn.setVisibility(View.GONE);
            QuestionsActivity.check.setVisibility(View.VISIBLE);


            //  *************************************   این قسمت چک میکنه این سوالی ک اومده روی کدوم پله است
            //   0 => معمولیه ینی اولین باره این سوال نشون داده میشه
            //   1 => سوال اشتباه جواب داده شده ک باید دوباره نشون داده بشه
            //   0 => درسته ینی دیگه بهش نشون داده نشه تا موقعی ک کل بخش تموم بشه

            if (step.equals("0")) {

                //list regular question
                questions_ofList = SQLite.select().from(Questions_Of.class)
                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
                        .and(Questions_Of_Table.sections.eq(section))
                        .queryList();
                QuestionsActivity.check.setVisibility(View.VISIBLE);
                Log.e("step 0 = ", questions_ofList.size() + "");

            } else if (step.equals("1")) {

                // list wrong answered
                questions_ofList = SQLite.select().from(Questions_Of.class)
                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
                        .and(Questions_Of_Table.sections.eq(section))
                        .and(Questions_Of_Table.state.eq(-1))
                        .queryList();
                QuestionsActivity.check.setVisibility(View.VISIBLE);

            } else {

                // list correct answered
                questions_ofList = SQLite.select().from(Questions_Of.class)
                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
                        .and(Questions_Of_Table.sections.eq(section))
                        .and(Questions_Of_Table.state.eq(1))
                        .queryList();

                QuestionsActivity.next.setVisibility(View.VISIBLE);
                QuestionsActivity.previous.setVisibility(View.VISIBLE);
                QuestionsActivity.check.setVisibility(View.GONE);
                QuestionsActivity.goOn.setVisibility(View.GONE);

            }
            updateProgress(false);
//            try {
//
//                // لیست کل سوالات بخش رو میده
//                List<Questions_Of> questionsOfListTotalSec = SQLite.select().from(Questions_Of.class)
//                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
//                        .and(Questions_Of_Table.sections.eq(section))
//                        .queryList();
//
//                // لیست کل سوالات درست پاسخ داده رو میده
//                List<Questions_Of> questionsOfListCorrect = SQLite.select().from(Questions_Of.class)
//                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
//                        .and(Questions_Of_Table.sections.eq(section))
//                        .and(Questions_Of_Table.state.eq(1))
//                        .queryList();
//
//                // درصد پیشرفت رو روی پروگرس خطی ک بالای صفحه است نشون میده
//                Log.e("correct = ", questionsOfListCorrect.size()+"");
//                Log.e("total = ", questionsOfListTotalSec.size()+"");
//                Log.e("DDDDDDDDDDDD","Here");
//                QuestionsActivity.progress.setProgress(( (questionsOfListCorrect.size())*100 )/questionsOfListTotalSec.size());
//                QuestionsActivity.point.setText(levelOf.getTotal_score());
//                QuestionsActivity.dars.setText(lessonId);
//                QuestionsActivity.bakhsh.setText(section);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            Log.e("questions_ofList = ", questions_ofList.size() + "");
            Log.e("questionNum = ", QuestionsActivity.questionNum + "");


            //  ------------             این قسمت چک میکنه که ایا اخرین سوال این بخشه یا ن؟    --------------------------------

            if (questions_ofList.size() <= QuestionsActivity.questionNum) {

                /** state = 1 is correct   .... state = -1 is wrong  */

                /** list of correct question  */

                // لیست کل سوالای درست پاسخ داده شده رو میگیره
                final List<Questions_Of> questions_ofListCorrect = SQLite.select().from(Questions_Of.class)
                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
                        .and(Questions_Of_Table.sections.eq(section))
                        .and(Questions_Of_Table.state.eq(1))
                        .queryList();

                // list of wrong question
                // سوالای اشتباه
                final List<Questions_Of> questions_ofListWrong = SQLite.select().from(Questions_Of.class)
                        .where(Questions_Of_Table.lesson_id.eq(lessonId))
                        .and(Questions_Of_Table.sections.eq(section))
                        .and(Questions_Of_Table.state.eq(-1))
                        .queryList();
                Log.e("size correct question", questions_ofListCorrect.size() + "");

                // چک  میکنه اگه تعداد سوالات غلط 0 نبود دوباره سوالات غلط رو بیاره ب کاربر نشون بده ک کاربر دوباره جواب بده
                if (questions_ofListWrong.size() != 0) {

                    QuestionsActivity.layoutResult.setVisibility(View.GONE);
//                QuestionsActivity.footer.setVisibility(View.GONE);
//                    QuestionsActivity.txtCorrect.setText(questions_ofListCorrect.size() + "");
//                    QuestionsActivity.txtWrong.setText(questions_ofListWrong.size() + "");
//
//                    QuestionsActivity.btnStart.setText("دوباره جواب میدم");

                    // وضعیت کل بخش رو تغییر میده => اگه وارد این قسمت بشه از اپ خارج بشه دوباره بیاد این متوجه میشه قبلا چندتا سوال استباه جواب داده
                    setSectionStep("1");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            QuestionsActivity.questionNum = 0;
                            // این دوباره فرگمنتو فراخوانی میکنه
                            changeQusetionPosition();

                        }
                    }, 500);

                    Log.e("step in wrong != 0", getSectionStep(Integer.parseInt(section)));


                    // اگه سوالات غلط 0 بود ینی این بخش کامل درست انجام شده
                } else {

                    // اگر وضعیت بخش هنوز -1 نشده ینی الان بخش رو تونست تموم کنه و باید نتایج بهش نشون داده بشه
                    if (!getSectionStep(Integer.parseInt(section)).equals("-1")) {

                        setSectionStep("-1");

                        /** in here send coin to server if that has not wrong answer*/

                        // نتیجه لایوتش فعال میشه
                        QuestionsActivity.layoutResult.setVisibility(View.VISIBLE);

                        QuestionsActivity.btn_go_main.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                activity.finish();

                            }
                        });

                        // این قسمت اجازه میده ک بخش یا درس بعدی فعال بشهه
                        permissionToNextLesson();

                        /** ===================================  */

                    } else {
                        Log.e("step in wrong == 0", getSectionStep(Integer.parseInt(section)));

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                QuestionsActivity.questionNum = 0;
                                changeQusetionPosition();

                            }
                        }, 50);

                    }
                }

            } else {

                if (questions_ofList.size() == 0) {
                    getActivity().finish();
                    Toasty.warning(getActivity(), "سوالی برای پاسخ دادن وجود ندارد").show();
                } else {
                    getStateLesson(inflater, container, step);
                }
            }
        }


        return view;
    }

    public void updateProgress(boolean nonCreate) {
        try {
            // لیست کل سوالات بخش رو میده
            List<Questions_Of> questionsOfListTotalSec = SQLite.select().from(Questions_Of.class)
                    .where(Questions_Of_Table.lesson_id.eq(lessonId))
                    .and(Questions_Of_Table.sections.eq(section))
                    .queryList();

            // لیست کل سوالات درست پاسخ داده رو میده
            List<Questions_Of> questionsOfListCorrect = SQLite.select().from(Questions_Of.class)
                    .where(Questions_Of_Table.lesson_id.eq(lessonId))
                    .and(Questions_Of_Table.sections.eq(section))
                    .and(Questions_Of_Table.state.eq(1))
                    .queryList();

            // درصد پیشرفت رو روی پروگرس خطی ک بالای صفحه است نشون میده
            Log.e("correct = ", questionsOfListCorrect.size() + "");
            Log.e("total = ", questionsOfListTotalSec.size() + "");
            Log.e("DDDDDDDDDDDD", "Here");
            QuestionsActivity.progress.setProgress(((questionsOfListCorrect.size()) * 100) / questionsOfListTotalSec.size());
            QuestionsActivity.point.setText(levelOf.getTotal_score());
            QuestionsActivity.dars.setText(lessonId);
            QuestionsActivity.bakhsh.setText(section);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nonCreate)
            funForShowPoint();

    }

    //        تابع برای نشان دادن امتیاز
    public void funForShowPoint() {
        Handler handler = new Handler();
        Context context = getContext();
        assert context != null;
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText("+۱۰ امتیاز");
        sweetAlertDialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sweetAlertDialog.dismiss();
            }
        }, 1000);

//        sweetAlertDialog.
    }

    // این متد برای اینه ک نشون بده این درس باید دوباره جواب بده یا ن قبلا جواب دادع
    private void getStateLesson(LayoutInflater inflater, ViewGroup container, String step) {
        if (step.equals("1")) {

            // wrong answer
            QuestionsActivity.blank.setVisibility(View.GONE);
            Log.e("step 1 = ", "step 1");
            getLists();

            views(inflater, container);

        } else if (step.equals("-1")) {

            // correct answer
            QuestionsActivity.blank.setVisibility(View.VISIBLE);
            Log.e("step -1 = ", "-1");
            getLists();

            views(inflater, container);

        } else {

            // don't answer
            QuestionsActivity.blank.setVisibility(View.GONE);
            Log.e("step 0 = ", "0");
            getLists();
            views(inflater, container);

        }

    }

    // توابع هر نوع سوال در این قسمت فراخونی میشه
    private void views(LayoutInflater inflater, ViewGroup container) {

        switch (questions_ofList.get(QuestionsActivity.questionNum).getType()) {

            case "1":
                view = xmlView(inflater, container, R.layout.type1);
                type1();
                break;

            case "2":
                view = xmlView(inflater, container, R.layout.type2);
                type2();
//                Toast.makeText(getActivity(), "type2", Toast.LENGTH_SHORT).show();
                break;

            case "3":
                view = xmlView(inflater, container, R.layout.type3);
                type3();
//                Toast.makeText(getActivity(), "type3", Toast.LENGTH_SHORT).show();
                break;

            case "4":
                view = xmlView(inflater, container, R.layout.type4);
                type4();
//                Toast.makeText(getActivity(), "type4", Toast.LENGTH_SHORT).show();
                break;

            case "5":
                view = xmlView(inflater, container, R.layout.type5);
                type5();
//                Toast.makeText(getActivity(), "type5", Toast.LENGTH_SHORT).show();
                break;

            case "6":
                view = xmlView(inflater, container, R.layout.type6);
                type6();
//                Toast.makeText(getActivity(), "type6", Toast.LENGTH_SHORT).show();
                break;

            case "7":
                view = xmlView(inflater, container, R.layout.type7);
                type7();
//                Toast.makeText(getActivity(), "type7", Toast.LENGTH_SHORT).show();
                break;

            case "8":
                view = xmlView(inflater, container, R.layout.type8);
                type8();
//                Toast.makeText(getActivity(), "type8", Toast.LENGTH_SHORT).show();
                break;

            case "9":
                view = xmlView(inflater, container, R.layout.type9);
                type9();
                break;

            case "10":
                view = xmlView(inflater, container, R.layout.type10);
                type10();
                break;

            case "11":
                view = xmlView(inflater, container, R.layout.type10);
                type11();
                break;

            case "12":
                view = xmlView(inflater, container, R.layout.type12a);
                type12(view);
                break;

            case "13":
                view = xmlView(inflater, container, R.layout.type13a);
                string = "";
                type13();
                break;

            case "14":
                view = xmlView(inflater, container, R.layout.type14a);
                type14();
                break;

            case "15":
                view = xmlView(inflater, container, R.layout.type9);
                type15();
                break;

            case "16":
                view = xmlView(inflater, container, R.layout.type9);
                type16();
                break;

            case "17":
                view = xmlView(inflater, container, R.layout.type17);
                type17();
                break;

            case "18":
                view = xmlView(inflater, container, R.layout.type18);
                type18();
                break;

            default:

                break;

        }
    }

    int totalScore = 0;

    //  لیست همه چیزایی ک لازمه مثه لیست جوابها لیست سوالات لیتس عکسا لیست زیرنویس ها و...
    private void getLists() {

        Log.e("questions id list", questions_ofList.size() + "");
        questions_of = questions_ofList.get(QuestionsActivity.questionNum);

        qAnswers = SQLite.select().from(QAnswers.class).where(QOptions_Table.questions_id.is(questions_of.getId())).queryList();
        qFullTexts = SQLite.select().from(QFullText.class).where(QFullText_Table.questions_id.is(questions_of.getId())).queryList();
        qOptions = SQLite.select().from(QOptions.class).where(QOptions_Table.questions_id.is(questions_of.getId())).queryList();
        qPathPhotos = SQLite.select().from(QPathPhoto.class).where(QPathPhoto_Table.question_id.is(questions_of.getId())).queryList();
        qBodies = SQLite.select().from(QBodies.class).where(QBodies_Table.questions_id.is(questions_of.getId())).queryList();

        QuestionsActivity.question_id = String.valueOf(questions_of.getId());

        for (int i = 0; i < 1; i++) {

            Log.e("spacer", "-------------------------===========================----------------------------");

            Questions_Of questions_of = questions_ofList.get(i);
            totalScore += Integer.parseInt(questions_of.getScore());
            Log.e("totalScore", String.valueOf(totalScore));
            Log.e("questions type", questions_of.getType());
            Log.e("questions head", questions_of.getHead());
            Log.e("questions lesson", questions_of.getLesson_id());
            Log.e("questions score", questions_of.getScore());
            Log.e("questions sections", questions_of.getSections());
            Log.e("questions id", questions_of.getId() + "");
            Log.e("questions state", questions_of.getState() + "");

            for (int j = 0; j < qOptions.size(); j++) {

                Log.e("questions options " + j, qOptions.get(j).getOptions());
                Log.e("size options ", qOptions.size() + "");

            }

            for (int j = 0; j < qAnswers.size(); j++) {

                Log.e("questions answers " + j, qAnswers.get(j).getAnswers());
                Log.e("size answers ", qAnswers.size() + "");
                Log.e("id answers ", qAnswers.get(j).getId() + "");
                Log.e("question id answers ", qAnswers.get(j).getQuestions_id() + "");

            }

            for (int j = 0; j < qBodies.size(); j++) {

                Log.e("questions bodies " + j, qBodies.get(j).getBodies());
                Log.e("size bodies ", qBodies.size() + "");

            }

            for (int j = 0; j < qPathPhotos.size(); j++) {

                Log.e("questions pathphoto " + j, qPathPhotos.get(j).getPath());
                Log.e("size pathphoto ", qPathPhotos.size() + "");

            }
        }


    }

    private View xmlView(LayoutInflater inflater, ViewGroup container, int layout) {

        return inflater.inflate(layout, container, false);
    }
    //*****************************************************   نوع ها اینجاست توابعشوون


    int selectPos1 = -1;

    private void type1() {
        logType(1);
        final RoundedImageView img1, img2, img3, img4;
        final RadioButton rb1, rb2, rb3, rb4;
        final LinearLayout ll1, ll2, ll3, ll4;
        TextView head1 = view.findViewById(R.id.head1);

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);

        rb1 = view.findViewById(R.id.caption1);
        rb2 = view.findViewById(R.id.caption2);
        rb3 = view.findViewById(R.id.caption3);
        rb4 = view.findViewById(R.id.caption4);

        ll1 = view.findViewById(R.id.ll1);
        ll2 = view.findViewById(R.id.ll2);
        ll3 = view.findViewById(R.id.ll3);
        ll4 = view.findViewById(R.id.ll4);
//        disableCheck();
        head1.setText(questions_of.getHead());

        Bitmap bitmap1 = BitmapFactory.decodeFile(qPathPhotos.get(0).getPath());
        img1.setImageBitmap(bitmap1);

        Bitmap bitmap2 = BitmapFactory.decodeFile(qPathPhotos.get(1).getPath());
        img2.setImageBitmap(bitmap2);

        Bitmap bitmap3 = BitmapFactory.decodeFile(qPathPhotos.get(2).getPath());
        img3.setImageBitmap(bitmap3);

        Bitmap bitmap4 = BitmapFactory.decodeFile(qPathPhotos.get(3).getPath());
        img4.setImageBitmap(bitmap4);

        rb1.setText(qOptions.get(0).getOptions());
        rb2.setText(qOptions.get(1).getOptions());
        rb3.setText(qOptions.get(2).getOptions());
        rb4.setText(qOptions.get(3).getOptions());

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectPos1 == Integer.parseInt(qAnswers.get(0).getAnswers())) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);


                } else {

                    if (selectPos1 == -1) {

                    } else {
                        General.wrongCToasty(qOptions.get(Integer.parseInt(qAnswers.get(0).getAnswers())).getOptions());
                        reduceScore();
                    }
                }
                nextQuestion();

            }
        });


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb1, rb2, rb3, rb4, ll1, ll2, ll3, ll4, rb1.getText().toString());
                selectPos1 = 0;

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb2, rb1, rb3, rb4, ll2, ll1, ll3, ll4, rb2.getText().toString());
                selectPos1 = 1;

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb3, rb1, rb2, rb4, ll3, ll2, ll1, ll4, rb3.getText().toString());
                selectPos1 = 2;

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb4, rb1, rb2, rb3, ll4, ll2, ll3, ll1, rb4.getText().toString());
                selectPos1 = 3;
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb1, rb2, rb3, rb4, ll1, ll2, ll3, ll4, rb1.getText().toString());
                selectPos1 = 0;
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb2, rb1, rb3, rb4, ll2, ll1, ll3, ll4, rb2.getText().toString());
                selectPos1 = 1;
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb3, rb1, rb2, rb4, ll3, ll2, ll1, ll4, rb3.getText().toString());
                selectPos1 = 2;
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRadioButton(rb4, rb1, rb2, rb3, ll4, ll2, ll3, ll1, rb4.getText().toString());
                selectPos1 = 3;
            }
        });


        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
//            Toast.makeText(activity, "blank on", Toast.LENGTH_SHORT).show();
            switch (qAnswers.get(0).getAnswers()) {

                case "0":
                    checkRadioButton(rb1, rb2, rb3, rb4, ll1, ll2, ll3, ll4, rb1.getText().toString());
                    break;
                case "1":
                    checkRadioButton(rb2, rb1, rb3, rb4, ll2, ll1, ll3, ll4, rb2.getText().toString());
                    break;
                case "2":
                    checkRadioButton(rb3, rb1, rb2, rb4, ll3, ll2, ll1, ll4, rb3.getText().toString());
                    break;
                case "3":
                    checkRadioButton(rb4, rb1, rb2, rb3, ll4, ll2, ll3, ll1, rb4.getText().toString());
                    break;
            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

        }

    }

    private void checkRadioButton(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, LinearLayout ll1, LinearLayout ll2, LinearLayout ll3, LinearLayout ll4, String toSpeak) {

        radioButton1.setChecked(true);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        radioButton4.setChecked(false);

        ll1.setBackgroundColor(getResources().getColor(R.color.light));
        ll2.setBackgroundColor(Color.WHITE);
        ll3.setBackgroundColor(Color.WHITE);
        ll4.setBackgroundColor(Color.WHITE);

        Log.e("sexxxx == ", questions_of.getSex());
        enableCheck();
        textToSpeech = utils.tts(toSpeak, 1f, "w");

    }

    FlowLayout payini;
    private int len = 70;
    FlowLayout balayi;
    int paddingTop = 20;
    int modeSurat = -1;
    int modeBottom = -1;

    //    bara
    public void logType(int a) {
        Log.e(WITCH_TYPE, a + "");
    }

    private void type2() {
        logType(2);
        final TextView txt_answer = view.findViewById(R.id.txt_answer2);
        TextView head2 = view.findViewById(R.id.head2);
        TextView txt_body = view.findViewById(R.id.txt_body);
        final ImageView clear = view.findViewById(R.id.clear);
        gridView = view.findViewById(R.id.grid_type2);
        rv_answer = view.findViewById(R.id.rv_answer);

        head2.setText(questions_of.getHead());

        txt_body.setText("nothing");
        txt_body.setText(qBodies.get(0).getBodies());
        Log.e("body == ", txt_body.getText().toString());
//        Toast.makeText(getActivity(), "body = "+ txt_body.getText().toString() + "\n" +
//                qBodies.get(0).getBodies(), Toast.LENGTH_SHORT).show();


        // ---------------------------------------

        //   گرفتن لیست

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> zirnevis = new ArrayList<>();
        List<String> surat_soal;
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
        FlowLayout flow_head = view.findViewById(R.id.flow_head);
        String[] title = qBodies.get(0).getBodies().split(" ");
//        String[] title = "thank you, father".split(" ");


//        String ssss = "father=پدر&thank you=تشکر از شما/ تشکر از تو";
        String ssss = questions_of.getSubtitle();
        String[] subtitle = ssss.split("&");


        Log.e("subtitle == ", subtitle.length + "");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitle, title, i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }

        for (int i = 0; i < qOptions.size(); i++) {

            typeList.add(qOptions.get(i).getOptions());

        }


        // en
        if (getLang(title[0]).equals("en")) {
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.ENGLISH;
            Log.e("modeSurat = en", modeSurat + "");
        } else {
            // fa
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.PERSIAN;
            Log.e("modeSurat = fa", modeSurat + "");
        }

        if (getLang(qOptions.get(0).getOptions()).equals("en")) {
            modeBottom = OptionsSetSub.ENGLISH;
            Log.e("modeee = en", modeBottom + "");
        } else {
            modeBottom = OptionsSetSub.PERSIAN;
            Log.e("modeee = fa", modeBottom + "");
        }

        OptionsSetSub2 optionsSetSub = new OptionsSetSub2();
        optionsSetSub.main(
                getActivity(),
                modeSurat,
                modeBottom,
                typeList,
                zirnevis,
                surat_soal,
                balayi,
                payini,
                flow_head,
                relativeLayout,
                typeList.size(),
                new OptionsSetSub2.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(0, 0, 0, 0);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void CustomTextViewGozineha(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                        enableCheck();
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setBackgroundColor(Color.GRAY);
                        String s = "";
                        for (int i = 0; i < OptionsSet.getStrListTextView(balayi).size(); i++) {
                            s += OptionsSet.getStrListTextView(balayi).get(i);
                        }

                        if ((OptionsSet.getStrListTextView(balayi).size() - 1) == 0) {
//                            disableCheck();
//                            Toast.makeText(activity, "disable", Toast.LENGTH_SHORT).show();
                        } else {
                            enableCheck();
//                            Toast.makeText(activity, "enable", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("s = ", s);

                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );

        //////////***************************

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAns = "";
                for (int i = 0; i < OptionsSetSub2.getStrListTxtView(balayi, 2).size(); i++) {
                    userAns += OptionsSetSub2.getStrListTxtView(balayi, modeBottom).get(i);
                }
                String answer = qAnswers.get(0).getAnswers().trim().toLowerCase().replace(" ", "")
                        .replace(",", "").replace(".", "");
                String offer = userAns.trim().toLowerCase().replace(" ", "")
                        .replace(",", "").replace(".", "");
                Log.e("answer", answer);
                Log.e("offer", offer);
                if (answer.equals(offer)) {
                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);
                } else {
                    General.wrongCToasty(qAnswers.get(0).getAnswers());
                    reduceScore();
                }
                nextQuestion();

            }
        });
        if (questions_of.getState() == 1) {

            String[] optsss = qAnswers.get(0).getAnswers().split(" ");

            for (int i = 0; i < optsss.length; i++) {

                FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                        FlowLayout.LayoutParams.WRAP_CONTENT,
                        FlowLayout.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(getActivity());
                textView.setLayoutParams(params);
                textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                textView.setTextSize(18f);
                textView.setText(optsss[i]);
                textView.setTextColor(Color.GRAY);
                textView.setBackgroundColor(Color.WHITE);
                textView.setTag("0");
                balayi.addView(textView);

            }

            QuestionsActivity.blank.setVisibility(View.VISIBLE);

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);
//            disableCheck();

        }

    }

    String[] sub2;

    private String addToZirnevisList(String[] subtitle, String[] title, int i) {

        String addToListZirnevis = "";

        for (int j = 0; j < subtitle.length; j++) {

            sub2 = subtitle[j].split("=");
            Log.e("sub == ", "sub0 == " + sub2[0] + " == title = " + title[i]);
            Log.e("index == ", "i == " + i + " == j" + j);
            if (sub2[0].contains(title[i].replace(",", "").replace("،", "").trim())) {

                addToListZirnevis = sub2[1].replace("/", "\n");
                Log.e("sub 1== ", addToListZirnevis);

                return addToListZirnevis;
            }

        }

        return "";
    }

    private String getLang(String title) {

        for (int i = 0; i < alph.length(); i++) {
            if (title.replace("?", "").replace(",", "")
                    .toLowerCase().contains(alph.substring(i, i + 1))) {

                Log.e("english = ", "english = " + title.replace("?", "").replace(",", ""));

                return "en";
            }
        }

        Log.e("persian = ", "persian = " + title.replace("?", "").replace(",", ""));

        return "fa";
    }

    List<String> type3List = null;
    String selectPos3 = "";

    //  questionNum 3
    private void type3() {
        logType(3);
        type3List = new ArrayList<>();

        final TextView body = view.findViewById(R.id.body);
        final TextView head = view.findViewById(R.id.head);
        final RadioButton rb1 = view.findViewById(R.id.rb_opts1);
        final RadioButton rb2 = view.findViewById(R.id.rb_opts2);
        final RadioButton rb3 = view.findViewById(R.id.rb_opts3);
        final RadioButton rb4 = view.findViewById(R.id.rb_opts4);

        head.setText(questions_of.getHead());
//        body.setText(qBodies.get(0).getBodies());


        for (int i = 0; i < qOptions.size(); i++) {
            type3List.add(qOptions.get(i).getOptions());
        }

        if (qOptions.size() > 3) {
            rb4.setVisibility(View.VISIBLE);
            rb4.setText(qOptions.get(3).getOptions());
        } else {
            rb4.setVisibility(View.GONE);
            rb4.setText("");
        }
        rb1.setText(qOptions.get(0).getOptions());
        rb2.setText(qOptions.get(1).getOptions());
        rb3.setText(qOptions.get(2).getOptions());

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPos3 = rb1.getText().toString();
                enableCheck();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPos3 = rb2.getText().toString();
                enableCheck();
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPos3 = rb3.getText().toString();
                enableCheck();
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPos3 = rb4.getText().toString();
                enableCheck();
            }
        });

        // ---------------------------------------------------------

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> zirnevis = new ArrayList<>();
        List<String> surat_soal;
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
        FlowLayout flow_head = view.findViewById(R.id.flow_head);
        String[] title = qBodies.get(0).getBodies().split(" ");
//        String[] title = "thank you, father".split(" ");


//        String ssss = "father=پدر&thank you=تشکر از شما/ تشکر از تو";
        String ssss = questions_of.getSubtitle();
        String[] subtitle = ssss.split("&");


        Log.e("subtitle == ", subtitle.length + "");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitle, title, i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }

        for (int i = 0; i < qOptions.size(); i++) {
            typeList.add(qOptions.get(i).getOptions());
        }

        if (getLang(title[0]).equals("en")) {
            surat_soal = Arrays.asList(title);

            modeSurat = OptionsSetSub.ENGLISH;
//            Toast.makeText(activity, "nashodeh", Toast.LENGTH_SHORT).show();
        } else {
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.PERSIAN;
//            Toast.makeText(activity, "reverse shod", Toast.LENGTH_SHORT).show();
        }

        OptionsSetSub optionsSetSub = new OptionsSetSub();
        optionsSetSub.main(getActivity(),
                modeSurat, modeBottom,
                typeList,
                zirnevis, surat_soal, balayi, payini, flow_head, relativeLayout, typeList.size(),
                new OptionsSetSub.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(0, 0, 0, 0);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void CustomTextViewBottom(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setTextColor(Color.RED);
                        textView.setBackgroundColor(Color.GRAY);
                        textView.setBackgroundResource(R.color.colorWhite);
                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );


        //////////***************************-----------------------


        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);

            for (int i = 0; i < qOptions.size(); i++) {
                if (qOptions.get(i).getOptions().trim().equals(qAnswers.get(0).getAnswers().trim())) {
                    switch (i) {
                        case 0:
                            rb1.setChecked(true);
                            break;
                        case 1:
                            rb2.setChecked(true);
                            break;
                        case 2:
                            rb3.setChecked(true);
                            break;
                        case 3:
                            rb4.setChecked(true);
                            break;
                    }
                }
            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);
//            disableCheck();

        }


        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answer = qAnswers.get(0).getAnswers().trim();
                String offer = selectPos3;

                Log.e("answer", answer);
                Log.e("offer", offer);

                if (answer.equals(offer)) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    General.wrongCToasty(answer);
                    reduceScore();

                }

                nextQuestion();

            }
        });

//        type3Adapter = new Type3Adapter(type3List, getActivity(), new Type3Adapter.OnClickAdapterType2() {
//            @Override
//            public void listener(int position, RecyclerView.ViewHolder viewHolder, RadioButton radioButton) {
//
//                itemPos = position;
//
//                radioButton.setChecked(true);
//
//
//            }
//        });

//        RecyclerView recyclerView = view.findViewById(R.id.rv_opts);
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(type3Adapter);
    }

    private void doNotNeedCheck() {

        QuestionsActivity.footer.setVisibility(View.GONE);

    }

//    private void disableCheck() {
//
//        QuestionsActivity.footer.setVisibility(View.VISIBLE);
//        QuestionsActivity.check.setEnabled(false);
//        QuestionsActivity.footer.setBackgroundResource(R.color.border_circles_gray);
//
//    }

    private void enableCheck() {

        QuestionsActivity.footer.setVisibility(View.VISIBLE);
        QuestionsActivity.check.setEnabled(true);
        QuestionsActivity.footer.setBackgroundResource(R.color.colorPrimaryDark);

    }

    private void type4() {
        logType(4);
        TextView head = view.findViewById(R.id.head4);
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        final TextView body = view.findViewById(R.id.body4);
        ImageView img_mic = view.findViewById(R.id.img_mic);
        txt_answer = view.findViewById(R.id.txt_answer);

        head.setText(questions_of.getHead());
//        subtitles();
        body.setText(qBodies.get(0).getBodies());

//        TextView subtitle = view.findViewById(R.id.subtitle);
        String[] sub = questions_of.getSubtitle().split("&");
        String[] sub1 = body.getText().toString().split(" ");
        List<String> sbbi = Arrays.asList(sub1);
        List<String> sbb2 = Arrays.asList(sub);
//
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(new SubtitleAdapter(sbbi,sbb2,getActivity()));


        // =---------------------------------------------

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> zirnevis = new ArrayList<>();
        List<String> surat_soal;
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
        FlowLayout flow_head = view.findViewById(R.id.flow_head);
        String[] title = qBodies.get(0).getBodies().split(" ");
//        String[] title = "thank you, father".split(" ");


//        String ssss = "father=پدر&thank you=تشکر از شما/ تشکر از تو";
        String ssss = questions_of.getSubtitle();
        String[] subtitle = ssss.split("&");

        Log.e("subtitle == ", subtitle.length + "");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitle, title, i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }

        for (int i = 0; i < qOptions.size(); i++) {

            typeList.add(qOptions.get(i).getOptions());

        }


        if (getLang(title[0]).equals("en")) {
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.ENGLISH;
            modeBottom = OptionsSetSub.ENGLISH;
        } else {
            surat_soal = Arrays.asList(title);
//            Collections.reverse(surat_soal);
//            Collections.reverse(zirnevis);
            modeSurat = OptionsSetSub.PERSIAN;
            modeBottom = OptionsSetSub.PERSIAN;
        }

        OptionsSetSub optionsSetSub = new OptionsSetSub();
        optionsSetSub.main(getActivity(),
                modeSurat, modeBottom,
                typeList,
                zirnevis, surat_soal, balayi, payini, flow_head, relativeLayout, typeList.size(),
                new OptionsSetSub.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void CustomTextViewBottom(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setTextColor(Color.RED);
                        textView.setBackgroundColor(Color.GRAY);
                        textView.setBackgroundResource(R.color.colorWhite);
                        String s = "";
                        for (int i = 0; i < OptionsSetSub.getStrListTxtView(balayi, modeBottom).size(); i++) {
                            s += OptionsSetSub.getStrListTxtView(balayi, modeBottom).get(i);
                        }

                        if ((OptionsSetSub.getStrListTxtView(balayi, modeBottom).size() - 1) == 0) {
//                            disableCheck();
//                            Toast.makeText(activity, "disable", Toast.LENGTH_SHORT).show();
                        } else {
                            enableCheck();
//                            Toast.makeText(activity, "enable", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("s = ", s);

                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );


        //////////***************************


        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextQuestion();

                for (int i = 0; i < qAnswers.size(); i++) {
                    boolean b = qAnswers.get(i).getAnswers().replace("؟", "")
                            .replace(".", "")
                            .equals(txt_answer.getText().toString());
                    Log.e("answerrr", qAnswers.get(i).getAnswers()
                            .replace("؟", "")
                            .replace(".", "").trim());
                    if (b) {

                        General.correctCToasty(getActivity());
                        increaseState();
                        updateProgress(true);
                        return;

                    } else {

                        if (i == qAnswers.size() - 1) {
                            General.wrongCToasty(qAnswers.get(i).getAnswers().replace("؟", ""));
                            reduceScore();
                            return;
                        }
                    }
                }

            }
        });


        img_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sttGoogle(4, enOrFa());

            }
        });


        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
            txt_answer.setText(qAnswers.get(0).getAnswers());

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);
//            disableCheck();

        }

//        disableCheck();
    }

    private String enOrFa() {

        String faORen = null;
        Log.e("body content", qBodies.get(0).getBodies().toLowerCase());

        for (int i = 0; i < alph.length(); i++) {

            if (qBodies.get(0).getBodies().toLowerCase().contains(alph.substring(i, i + 1))) {
                faORen = "fa-IR";
                Log.e("fa or en", faORen);
                return faORen;
            } else {
                faORen = "en-US";
                Log.e("fa or en", faORen);
                return faORen;
            }

        }
        return "";
    }

    private void type5() {
        logType(5);
        final TextView txt_answer = view.findViewById(R.id.txt_answer);
        final ImageView clear = view.findViewById(R.id.clear);
        txt_head = view.findViewById(R.id.txt_head);
        word = view.findViewById(R.id.word);
        image = view.findViewById(R.id.image5);

        txt_head.setText(questions_of.getHead());
        Bitmap bitmap = BitmapFactory.decodeFile(qPathPhotos.get(0).getPath());
        image.setImageBitmap(bitmap);
        word.setText(qBodies.get(0).getBodies());
//        disableCheck();

        //   گرفتن لیست
        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> zirnevis = new ArrayList<>();
        List<String> surat_soal;
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
        FlowLayout flow_head = view.findViewById(R.id.flow_head);
        String[] title = qBodies.get(0).getBodies().split(" ");
//        String[] title = "thank you, father".split(" ");


//        String ssss = "father=پدر&thank you=تشکر از شما/ تشکر از تو";
        String ssss = questions_of.getSubtitle();
        String[] subtitle = ssss.split("&");


        Log.e("subtitle == ", subtitle.length + "");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitle, title, i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }

        for (int i = 0; i < qOptions.size(); i++) {

            typeList.add(qOptions.get(i).getOptions());

        }
        typeList.add("g");


        if (getLang(title[0]).equals("en")) {
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.ENGLISH;
        } else {
            surat_soal = Arrays.asList(title);
//            Collections.reverse(surat_soal);
//            Collections.reverse(zirnevis);
            modeSurat = OptionsSetSub.PERSIAN;
        }

        if (getLang(qOptions.get(0).getOptions()).equals("en")) {
            modeBottom = OptionsSetSub.ENGLISH;
        } else {
            modeBottom = OptionsSetSub.PERSIAN;
        }


        OptionsSetSub optionsSetSub = new OptionsSetSub();
        optionsSetSub.main(getActivity(),
                modeSurat, modeBottom,
                typeList,
                zirnevis, surat_soal, balayi, payini, flow_head, relativeLayout, typeList.size(),
                new OptionsSetSub.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(0, 0, 0, 0);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void CustomTextViewBottom(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                        enableCheck();
//                        Toast.makeText(activity, "paini", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setBackgroundColor(Color.GRAY);

                        String s = "";
                        for (int i = 0; i < OptionsSetSub.getStrListTxtView(balayi, modeBottom).size(); i++) {
                            s += OptionsSetSub.getStrListTxtView(balayi, modeBottom).get(i);
                        }

                        if ((OptionsSetSub.getStrListTxtView(balayi, modeBottom).size() - 1) == 0) {
//                            disableCheck();
//                            Toast.makeText(activity, "disable", Toast.LENGTH_SHORT).show();
                        } else {
                            enableCheck();
//                            Toast.makeText(activity, "enable", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("s = ", s);

                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );

        //////////***************************

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> answerList = OptionsSetSub.getStrListTxtView(balayi, modeBottom);
                String answer = qAnswers.get(0).getAnswers().trim().toLowerCase().replace(" ", "");
//                String offer = txt_answer.getText().toString().trim().toLowerCase().replace(" ","");
                String offer = "";

                for (int i = 0; i < answerList.size(); i++) {
                    offer += answerList.get(i);
                }

                Log.e("answer", answer);
                Log.e("offer", offer.trim());

                if (answer.toLowerCase().trim().equals(offer.toLowerCase().trim())) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    General.wrongCToasty(answer.toLowerCase().trim());
                    reduceScore();

                }

                nextQuestion();

            }
        });


        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);

            String[] optsss = qAnswers.get(0).getAnswers().split("");

            for (int i = 0; i < optsss.length; i++) {

                if (!(optsss[i].replace(" ", "").trim().equals(""))) {

                    FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                            FlowLayout.LayoutParams.WRAP_CONTENT,
                            FlowLayout.LayoutParams.WRAP_CONTENT);
                    TextView textView = new TextView(getActivity());
                    textView.setLayoutParams(params);
                    textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                    textView.setTextSize(18f);
                    textView.setText(optsss[i]);
                    textView.setTextColor(Color.GRAY);
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTag("0");
                    balayi.addView(textView);

                }
            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

        }

    }

    int selectPosition6 = -1;

    private void type6() {
        logType(6);
        String contentSound = "";
        TextView txt_head = view.findViewById(R.id.txt_head6);
        TextView main_title = view.findViewById(R.id.main_title);
        rv_type6 = view.findViewById(R.id.rv_type6);
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
//        disableCheck();
        txt_head.setText(questions_of.getHead());
        main_title.setText(questions_of.getTitleConvquestion());

        // when answered
        if (questions_of.getState() == 1) {
            QuestionsActivity.blank.setVisibility(View.VISIBLE);
        } else {
            QuestionsActivity.blank.setVisibility(View.GONE);
        }


        contentSound += questions_of.getTitleConvquestion();
        Log.e("url main", "url === " + qPathPhotos.size());

        for (int i = 0; i < qPathPhotos.size(); i++) {

            String url = qPathPhotos.get(i).getPath();
            Log.e("url main", "url === " + url);
            String caption = "(" + (i + 1) + ")";

            if (url.contains("&")) {
                String[] strPath = url.split("&");
                url = strPath[0];
                Log.e("url", "url === " + url);
                if (strPath.length > 1) {
                    caption = strPath[1];
                }
            }

            Log.e("url finea", "url === " + url);
            type6GridList.add(new Type6(url, caption));

        }

        for (int i = 0; i < qBodies.size(); i++) {

            type6List.add(qBodies.get(i).getBodies());
            String[] strings = type6List.get(i).split(":");
            contentSound += "\n" + strings[1];

        }

        QuestionsActivity.blank.setVisibility(View.VISIBLE);
        textToSpeech = utils.tts(contentSound, 1, questions_of.getSex());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionsActivity.blank.setVisibility(View.GONE);
            }
        }, contentSound.length() * 80);

//        type6List.add("hello, how are you");
//        type6List.add("hello, i'm fine ,thanks");

        // for answer

        grid_type6 = view.findViewById(R.id.grid_type6);
        type6GridAdapter = new Type6GrideAdapter(type6GridList, getActivity(), questions_of.getState()
                , Integer.parseInt(qAnswers.get(0).getAnswers()));
        grid_type6.setAdapter(type6GridAdapter);

        grid_type6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                enableCheck();
                selectPosition6 = position;
                view.setSelected(true);

                Log.e("item position == ", selectPosition6 + "");
                Log.e("item answers == ", qAnswers.get(0).getAnswers());

            }
        });

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("item position == ", selectPosition6 + "");
                Log.e("item answers == ", qAnswers.get(0).getAnswers());

                if (selectPosition6 == Integer.parseInt(qAnswers.get(0).getAnswers())) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    String ans = type6GridList.get(Integer.parseInt(qAnswers.get(0).getAnswers())).getCaption();
                    General.wrongCToasty(ans);
                    reduceScore();

                }

                nextQuestion();

            }
        });

        // for question
        rv_type6.setHasFixedSize(true);
        type6Adapter = new Type6Adapter(type6List, getActivity(), questions_of.getSex(), relativeLayout,
                questions_of.getSubtitle(), questions_of.getTitleConvquestion());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false);
        rv_type6.setLayoutManager(linearLayoutManager);
        rv_type6.setAdapter(type6Adapter);

    }

    private void type7() {
        logType(7);
        final StringBuilder stringBuilder = new StringBuilder();
        final ImageView btn_voice_low = view.findViewById(R.id.btn_voice_low);
        ImageView btn_voice = view.findViewById(R.id.btn_voice);
//        ImageView clear = view.findViewById(R.id.clear);
//        final TextView txt_answer7 = view.findViewById(R.id.txt_answer7);

//        final List<String> listGridType7 = new ArrayList<>();
//        for (int i = 0; i < qOptions.size(); i++) {
//
//            listGridType7.add(qOptions.get(i).getOptions());
//
//        }

        final String body = qBodies.get(0).getBodies().replace("?", "")
                .replace("؟", "").replace(".", "");

        final String[] voice = body.split(":");


        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (body.contains(":")) {
                    textToSpeech = utils.tts(voice[1], 1f, "w");
                } else {
                    textToSpeech = utils.tts(voice[0], 1f, "w");

                }
            }
        });

        btn_voice_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (body.contains(":")) {
                    textToSpeech = utils.tts(voice[1], 0.05f, "w");
                } else {
                    textToSpeech = utils.tts(voice[0], 0.05f, "w");
                }

            }
        });


        //--------------------------------------------------

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> zirnevis = new ArrayList<>();
        List<String> surat_soal;
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
        FlowLayout flow_head = view.findViewById(R.id.flow_head);
        String[] title = qBodies.get(0).getBodies().split(" ");
//        String[] title = "thank you, father".split(" ");


//        String ssss = "father=پدر&thank you=تشکر از شما/ تشکر از تو";
        String ssss = questions_of.getSubtitle();
        String[] subtitle = ssss.split("&");


        Log.e("subtitle == ", subtitle.length + "");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitle, title, i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }

        for (int i = 0; i < qOptions.size(); i++) {

            typeList.add(qOptions.get(i).getOptions());

        }


        typeList.add("you");


        if (getLang(title[0]).equals("en")) {
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.ENGLISH;
        } else {
            surat_soal = Arrays.asList(title);
            Collections.reverse(surat_soal);
            Collections.reverse(zirnevis);
            modeSurat = OptionsSetSub.PERSIAN;
        }

        if (getLang(qOptions.get(0).getOptions()).equals("en")) {
            modeBottom = OptionsSetSub.ENGLISH;
        } else {
            modeBottom = OptionsSetSub.PERSIAN;
        }


        OptionsSetSub optionsSetSub = new OptionsSetSub();
        optionsSetSub.main(getActivity(),
                modeSurat, modeBottom,
                typeList,
                zirnevis, surat_soal, balayi, payini, flow_head, relativeLayout, typeList.size(),
                new OptionsSetSub.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(0, 0, 0, 0);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void CustomTextViewBottom(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                        enableCheck();
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setBackgroundColor(Color.GRAY);
                        String s = "";
                        for (int i = 0; i < OptionsSet.getStrListTextView(balayi).size(); i++) {
                            s += OptionsSet.getStrListTextView(balayi).get(i);
                        }

                        if ((OptionsSet.getStrListTextView(balayi).size() - 1) == 0) {
//                            disableCheck();
//                            Toast.makeText(activity, "disable", Toast.LENGTH_SHORT).show();
                        } else {
                            enableCheck();
//                            Toast.makeText(activity, "enable", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("s = ", s);

                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userAns = "";
                for (int i = 0; i < OptionsSet.getStrListTextView(balayi).size(); i++) {
                    userAns += OptionsSet.getStrListTextView(balayi).get(i);
                }

                String answer = qAnswers.get(0).getAnswers().replace("?", "").replace("؟", "")
                        .trim().toLowerCase().replace(" ", "")
                        .replace(",", "").replace(".", "");
                String offer = userAns.trim().toLowerCase().replace("?", "").replace("؟", "")
                        .trim().toLowerCase().replace(" ", "")
                        .replace(",", "").replace(".", "");

                Log.e("answer", answer);
                Log.e("offer", offer);

                if (answer.equals(offer)) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    General.wrongCToasty(qAnswers.get(0).getAnswers());
                    reduceScore();

                }

                nextQuestion();

            }
        });


        if (questions_of.getState() == 1) {

            String[] optsss = qAnswers.get(0).getAnswers().split(" ");

            for (int i = 0; i < optsss.length; i++) {

                FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                        FlowLayout.LayoutParams.WRAP_CONTENT,
                        FlowLayout.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(getActivity());
                textView.setLayoutParams(params);
                textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                textView.setTextSize(18f);
                textView.setText(optsss[i]);
                textView.setTextColor(Color.GRAY);
                textView.setBackgroundColor(Color.WHITE);
                textView.setTag("0");
                balayi.addView(textView);

            }

            QuestionsActivity.blank.setVisibility(View.VISIBLE);

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);
//            disableCheck();

        }

    }

    private void type8() {
        logType(8);
        selectType = 8;
//        disableCheck();

        RoundedImageView img_ask = view.findViewById(R.id.img_ask);
        final ImageView play_voice = view.findViewById(R.id.play_voice);
        final TextView txt2 = view.findViewById(R.id.txt2);
        final TextView txt1 = view.findViewById(R.id.txt1);
        final TextView txt3 = view.findViewById(R.id.txt3);
        final TextView et1 = view.findViewById(R.id.et1);
        final TextView et2 = view.findViewById(R.id.et2);
        final TextView txt81 = view.findViewById(R.id.txt81);
        final TextView txt82 = view.findViewById(R.id.txt82);
        String content = "";


        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> surat_soal = new ArrayList<>();
        final FlowLayout flow_head = view.findViewById(R.id.flow_head);


        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);

            for (int i = 0; i < qFullTexts.size(); i++) {
                String[] stringg = qFullTexts.get(i).getFulltext().replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
                surat_soal.add("##");

            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

            for (int i = 0; i < qBodies.size(); i++) {
                String[] stringg = qBodies.get(i).getBodies().replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
                surat_soal.add("##");
            }

        }

        Bitmap bitmap = BitmapFactory.decodeFile(qPathPhotos.get(0).getPath());
        img_ask.setImageBitmap(bitmap);


        content = qFullTexts.get(0).getFulltext();
        final String finalContent = content;
        play_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int delayed = (finalContent.length() * 100) + 500;

                Log.e("stringBuilder null", finalContent + "");
                textToSpeech = utils.tts(finalContent, 1, questions_of.getSex());
                play_voice.setImageResource(R.drawable.ic_pause);
                play_voice.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        play_voice.setImageResource(R.drawable.ic_play_arrow);
                        play_voice.setEnabled(true);

                    }
                }, delayed);

            }
        });

        // play conversation
        textToSpeech = utils.tts(content, 1, questions_of.getSex());
        utils.tts_en.setOnUtteranceCompletedListener(this);

        QuestionsActivity.blank.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (questions_of.getState() == 1) {

                } else {
                    QuestionsActivity.blank.setVisibility(View.GONE);
                }
            }
        }, content.length() * 80);


        for (int i = 0; i < qOptions.size(); i++) {
            typeList.add(qOptions.get(i).getOptions());
        }

        List<String> fullText = new ArrayList<>();
        for (int i = 0; i < qFullTexts.size(); i++) {
            fullText.add(qFullTexts.get(i).getFulltext());
        }


        FunType8And9 funType8And9 = new FunType8And9();
        funType8And9.main(getContext(), surat_soal, typeList, flow_head, fullText, payini, new FunType8And9.funOpt8() {
            @Override
            public void CunstomTextViewBottom(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(1, 0, 0, 1);
                textViews.get(position).setTextSize(16f);
                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setTextColor(Color.BLACK);
            }

            @Override
            public void CustomTextViewSoratSoal(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(0, 1, 0, 1);
                textViews.get(position).setTextSize(18f);
//                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setTextColor(Color.BLACK);
            }

            @Override
            public void SetEffectDisableJakhali(List<TextView> textViews, int position) {
                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setBackgroundResource(R.drawable.shape);
            }

            @Override
            public void SetEffectWhenEnableJakhali(TextView textView) {
//                textView.setBackgroundColor(Color.BLUE);
                textView.setBackgroundResource(R.drawable.shape);
//                textView.setText("        ");
                textView.setTextColor(Color.BLUE);
                textView.setMinWidth(100);
                textView.setPadding(10, 10, 10, 10);

//                disableCheck();
            }

            @Override
            public void SetEffectFirstPositionJakhali(TextView textView) {
                textView.setBackgroundResource(R.drawable.shape);
            }

            @Override
            public void SetEffectWhenGozinehaClick(TextView textView, TextView jakhali) {
//                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                textView.setBackgroundColor(Color.GRAY);
                textView.setTextColor(Color.GRAY);

                for (int i = 0; i < FunType8And9.getStrJakhaliha(flow_head).size(); i++) {
                    Log.e("size = ", FunType8And9.getStrJakhaliha(flow_head).get(i));
                    if (FunType8And9.getStrJakhaliha(flow_head).get(i).equals(" ")) {
//                        disableCheck();
                        return;
                    } else {
                        enableCheck();
                    }
                }
            }

            @Override
            public void WhenJakhaliClick(TextView textView, List<TextView> b, int i) {
                textView.setBackgroundResource(R.drawable.shape);
                b.get(i).setBackgroundColor(Color.WHITE);
                b.get(i).setTextColor(Color.BLACK);
            }

            @Override
            public void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme) {

            }

            @Override
            public void CustomVoice(TextView t) {

            }
        });


        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Log.e("item position == ", FunType8And9.getStrJakhaliha(flow_head).get(position) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("item answers == ", qAnswers.get(0).getAnswers().toLowerCase());

                if (FunType8And9.getStrJakhaliha(flow_head).get(position)
                        .toLowerCase().trim().equals(qAnswers.get(0).getAnswers().toLowerCase())) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    General.wrongCToasty(qAnswers.get(0).getAnswers().toLowerCase());
                    reduceScore();

                }

                nextQuestion();

            }
        });




/*

        card82 = "";
        String card1 = "", str1 = "", str2 = "", str3 = "";
        numCards = 0;numStr = 0; numExit = 0;
        final String[] userAnswer;

        String[] strContent = content.split("-");

        for (int i = 0; i < strContent.length; i++) {
            Log.e("stringBuilder numC out", strContent[i]+"");
            if (strContent[i].trim().equals("")){
                numCards ++;
                Log.e(" numCards", numCards+"");

                Log.e("stringBuilder null", strContent[i]+"");
                if (numCards == 1){
                    card1 = "exist";
                    numExit ++;
                    Log.e(" numCards1", numCards+"");
                }else if (numCards == 3){
                    card82 = "exist";
                    numExit ++;
                    Log.e(" numCards2", numCards+"");
                }
            }else {

                if (!content.startsWith("-")) {
                    numStr++;
                    Log.e("strcards ", numStr + "");
                    Log.e("stringBuilder full", strContent[i] + "");
                    if (numStr == 1) {
                        str1 = strContent[i];
                    } else if (numStr == 2) {
                        str2 = strContent[i];
                    } else if (numStr == 3) {
                        str3 = strContent[i];
                    }
                }else {

                    numStr++;
                    Log.e("strcards 2", numStr + "");
                    Log.e("stringBuilder full 2", strContent[i] + "");
                    if (numStr == 1) {
                        str2 = strContent[i];
                    } else if (numStr == 2) {
                        str3 = strContent[i];
                    }
                }
            }
        }

        userAnswer = new String[numExit+1];

        if (qOptions.size() == 0){

            if (card1.equals("")){
                et1.setVisibility(View.GONE);
            }else {
                et1.setVisibility(View.VISIBLE);
            }

            if (card82.equals("")){
                et2.setVisibility(View.GONE);
            }else {
                et2.setVisibility(View.VISIBLE);
            }

            if (str1.equals("")){
                txt1.setVisibility(View.GONE);
            }else {
                txt1.setVisibility(View.VISIBLE);
            }

            if (str2.equals("")){
                txt2.setVisibility(View.GONE);
            }else {
                txt2.setVisibility(View.VISIBLE);
            }

            if (str3.equals("")){
                txt3.setVisibility(View.GONE);
            }else {
                txt3.setVisibility(View.VISIBLE);
            }

        }else {

            if (numCards == 3) {

                if (card1.equals("")){
                    txt81.setVisibility(View.GONE);
                }else {
                    txt81.setVisibility(View.VISIBLE);
                }

                if (card82.equals("")){
                    txt82.setVisibility(View.GONE);
                }else {
                    txt82.setVisibility(View.VISIBLE);
                }

                if (str1.equals("")){
                    txt1.setVisibility(View.GONE);
                }else {
                    txt1.setVisibility(View.VISIBLE);
                    txt1.setText(str1);
                }

                if (str2.equals("")){
                    txt2.setVisibility(View.GONE);
                }else {
                    txt2.setVisibility(View.VISIBLE);
                    txt2.setText(str2);
                }

                if (str3.equals("")){
                    txt3.setVisibility(View.GONE);
                }else {
                    txt3.setVisibility(View.VISIBLE);
                    txt3.setText(str3);
                }

            } else {
                content = qBodies.get(0).getBodies().replace("- -", "......");
                txt2.setText(content);
            }
        }

        txt81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posSelect = 1;
                if (txt81.getText().toString().replace("...","").trim().equals("")) {
                    txt82.setBackgroundResource(R.drawable.shape);
                    txt81.setBackgroundResource(R.drawable.shape_select);
                }else {
                    listGridType8.add(userAnswer[posSelect]);
                    userAnswer[posSelect] = "";
                    txt81.setText(userAnswer[posSelect]);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        txt82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posSelect = 2;
                if (txt82.getText().toString().replace("...","").trim().equals("")) {
                    txt81.setBackgroundResource(R.drawable.shape);
                    txt82.setBackgroundResource(R.drawable.shape_select);
                }else {
                    listGridType8.add(userAnswer[posSelect]);
                    userAnswer[posSelect] = "";
                    txt82.setText(userAnswer[posSelect]);
                    adapter.notifyDataSetChanged();
                }

            }
        });


        final String answer = qBodies.get(0).getBodies().replace("- -"," "+ qAnswers.get(0).getAnswers() +" ");

        Bitmap bitmap = BitmapFactory.decodeFile(qPathPhotos.get(0).getPath());
        img_ask.setImageBitmap(bitmap);

        utils.tts(answer, 1f,"m");
        utils.tts_en.setOnUtteranceCompletedListener(this);

        play_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int delayed = (answer.length()*100)+1000;

                utils.tts(answer,1,"m");
                play_voice.setImageResource(R.drawable.ic_pause);
                play_voice.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        play_voice.setImageResource(R.drawable.ic_play_arrow);
                        play_voice.setEnabled(true);

                    }
                },delayed);

            }
        });

        int answerId = -1;

        for (int i = 0; i < qOptions.size(); i++) {
            listGridType8.add(qOptions.get(i).getOptions());

            if (qAnswers.get(0).getAnswers().equals(qOptions.get(i).getOptions())){
                answerId = i;
            }
        }

        gridView = view.findViewById(R.id.grid_type8);
        adapter = new Type2Adapter(listGridType8,getActivity(),
                questions_of.getState(),answerId);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                if (numCards != 3) {
                    select8 = listGridType8.get(position);
                    view.setSelected(true);
                } else {

//                    if (userAnswer[posSelect].trim().equals("")) {
//                        Toast.makeText(getActivity(), "ابتدا کادر را خالی کنید", Toast.LENGTH_SHORT).show();
//                    } else {
                        userAnswer[posSelect] = listGridType8.get(position);
                        if (posSelect == 1) {
                            txt81.setText(userAnswer[posSelect]);
                        } else if (posSelect == 2) {
                            txt82.setText(userAnswer[posSelect]);
                        }
                        listGridType8.remove(position);
                        adapter.notifyDataSetChanged();
//                    }
                }
            }
        });

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String userAns = "", ans = "";

                    if (qOptions.size() > 0) {

                        if (selectType == 8) {

                            if (numCards == 3) {

                                userAns = userAnswer[1].trim() + userAnswer[2].trim();
                                for (int i = 0; i < qAnswers.size(); i++) {
                                    ans += qAnswers.get(i).getAnswers().trim();
                                }
                                Log.e("item user answer == ", userAns);
                                Log.e("item answers == ", ans);
                            } else {
                                userAns = select8;
                                ans = qAnswers.get(0).getAnswers();
                                Log.e("item user answer == ", userAns);
                                Log.e("item answers == ", ans);
                            }
                            try {

                                if (userAns.equals(ans)) {

                                    increaseState();

                                } else {

                                    General.wrongCToasty(ans);
                                    reduceScore();

                                }

                                nextQuestion();

                            } catch (Exception e) {

                                Toasty.info(App.context, "ابتدا کادر مورد نظر را انتخاب کنید").show();

                            }
                        }

                    } else {

                        String userAnswer = et1.getText().toString() + et2.getText().toString();
                        String answer = "";
                        for (int i = 0; i < qAnswers.size(); i++) {
                            answer += qAnswers.get(i).getAnswers();
                        }

                        answer = answer.trim().replace(" ", "");
                        userAnswer = userAnswer.trim().replace(" ", "");

                        if (selectType == 8) {

                            try {
                                Log.e("item position == ", userAnswer + "");
                                Log.e("item answers == ", answer);

                                if (userAnswer.equals(answer)) {

                                    increaseState();

                                } else {

                                    General.wrongCToasty(answer);
                                    reduceScore();

                                }

                                nextQuestion();

                            } catch (Exception e) {

                                Toasty.info(App.context, "ابتدا کادر مورد نظر را انتخاب کنید").show();

                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
*/
    }

    public static List<String> select9List;
    List<String> listGridType9;
    Type91011RecycleAdapter type9Adapter;
    String card1 = "", card2 = "", str1 = "", str2 = "";
    String[][] strings;
    String[][] arrAnswer;
    int numExist = -1;

    private void type9() {
        logType(9);
        TextView name_conv = view.findViewById(R.id.name_conv);
        TextView head9 = view.findViewById(R.id.head9);
        head9.setText(questions_of.getHead());
        if (!questions_of.getTitleConvquestion().equals("")) {
            name_conv.setText(questions_of.getTitleConvquestion());
        } else {
            name_conv.setText("");
        }

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> surat_soal = new ArrayList<>();
        final FlowLayout flow_head = view.findViewById(R.id.flow_head);


        String content = "";
        content += questions_of.getTitleConvquestion();


        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
//            nextQuestion();

            for (int i = 0; i < qFullTexts.size(); i++) {
                String[] stringg = qFullTexts.get(i).getFulltext().replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
                if (qFullTexts.size() - 1 > i) {
                    surat_soal.add("##");
                }
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

            for (int i = 0; i < qBodies.size(); i++) {
                String[] stringg = qBodies.get(i).getBodies().replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
                if (qBodies.size() - 1 > i) {
                    surat_soal.add("##");
                }
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }

        }

        // play conversation
        textToSpeech = utils.tts(content, 1, questions_of.getSex());

        QuestionsActivity.blank.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (questions_of.getState() == 1) {

                } else {
                    QuestionsActivity.blank.setVisibility(View.GONE);
                }
            }
        }, content.length() * 80);


        List<String> fullText = new ArrayList<>();
        for (int i = 0; i < qFullTexts.size(); i++) {
            fullText.add(qFullTexts.get(i).getFulltext());
        }

        for (int i = 0; i < qOptions.size(); i++) {
            typeList.add(qOptions.get(i).getOptions());
        }

//
//        if (getLang(title[0]).equals("en")){
//            surat_soal = Arrays.asList(title);
//            modeSurat = OptionsSetSub.ENGLISH;
//            modeBottom = OptionsSetSub.ENGLISH;
//        }else {
//            surat_soal = Arrays.asList(title);
//            Collections.reverse(surat_soal);
//            Collections.reverse(zirnevis);
//            modeSurat = OptionsSetSub.PERSIAN;
//            modeBottom = OptionsSetSub.PERSIAN;
//        }

        FunType8And9 funType8And9 = new FunType8And9();
        funType8And9.main(getContext(), surat_soal, typeList, flow_head, fullText, payini, new FunType8And9.funOpt8() {
            @Override
            public void CunstomTextViewBottom(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                textViews.get(position).setTextSize(16f);
                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setTextColor(Color.BLACK);
            }

            @Override
            public void CustomTextViewSoratSoal(List<TextView> textViews, int position) {
                textViews.get(position).setPadding(1, 1, 1, 1);
                textViews.get(position).setTextSize(18f);
//                textViews.get(position).setBackgroundColor(Color.WHITE);
                textViews.get(position).setTextColor(Color.BLACK);
            }

            @Override
            public void SetEffectDisableJakhali(List<TextView> textViews, int position) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(TextView textView) {
//                textView.setBackgroundColor(Color.BLUE);
                textView.setBackgroundResource(R.drawable.shape);
                textView.setText("     ");
                b = true;
                textView.setTextColor(Color.BLUE);
                textView.setMinWidth(100);
                textView.setPadding(10, 10, 10, 10);

//                disableCheck();
            }

            @Override
            public void SetEffectFirstPositionJakhali(TextView textView) {
//                textView.setBackgroundResource(R.drawable.shape);
//                textView.setText("     ");
            }

            @Override
            public void SetEffectWhenGozinehaClick(TextView textView, TextView jakhali) {
//                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();

                if (b) {
                    textView.setBackgroundColor(Color.GRAY);
                    textView.setTextColor(Color.GRAY);

                    for (int i = 0; i < FunType8And9.getStrJakhaliha(flow_head).size(); i++) {
                        Log.e("size = ", FunType8And9.getStrJakhaliha(flow_head).get(i));
                        if (FunType8And9.getStrJakhaliha(flow_head).get(i).equals(" ")) {
//                            disableCheck();
                            return;
                        } else {
                            enableCheck();
                        }
                    }
                } else {
                    Toasty.info(getContext(), "ابتدا جای خالی مورد نظر را انتخاب کنید").show();
                }
            }

            @Override
            public void WhenJakhaliClick(TextView textView, List<TextView> b, int i) {
                textView.setBackgroundResource(R.drawable.shape);
                b.get(i).setBackgroundColor(Color.WHITE);
                b.get(i).setTextColor(Color.BLACK);
            }

            @Override
            public void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme) {
                String[] cont = everyMokaleme.get(position).split(":");
                textToSpeech = utils.tts(cont[1], 1f, questions_of.getSex());
            }

            @Override
            public void CustomVoice(TextView t) {
                t.setPadding(15, 15, 15, 15);
                t.setBackgroundResource(General.iconSeda);
            }
        });

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userAns = "";
                String answer = "";

                try {
                    for (int i = 0; i < FunType8And9.getStrJakhaliha(flow_head).size(); i++) {

                        userAns += FunType8And9.getStrJakhaliha(flow_head).get(i).toLowerCase().replace(",", "")
                                .replace(" ", "");
                        Log.e("item position == ", userAns + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < qAnswers.size(); i++) {
                    answer += qAnswers.get(i).getAnswers().toLowerCase().replace(",", "")
                            .replace(" ", "");
                }
                Log.e("item answers == ", answer);

                if (userAns.equals(answer)) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);


                } else {
                    String s = "";
                    for (int i = 0; i < qAnswers.size(); i++) {
                        s += qAnswers.get(i).getAnswers() + "\n";
                    }
                    General.wrongCToasty(s);
                    reduceScore();

                }

                nextQuestion();

            }
        });

    }

    boolean b = false;

    public void type10() {
        logType(10);
        TextView name_conv = view.findViewById(R.id.name_conv);
        TextView head9 = view.findViewById(R.id.head9);
//        disableCheck();
        if (questions_of.getHead().length() != 0) {
            head9.setText(questions_of.getHead());
        }

        if (!questions_of.getTitleConvquestion().equals("")) {
            name_conv.setText(questions_of.getTitleConvquestion());
        } else {
            name_conv.setText("");
        }
        payini = view.findViewById(R.id.llMain);
        List<String> surat_soal = new ArrayList<>();
        final FlowLayout flow_head = view.findViewById(R.id.flow_head);


        String content = "";
        content += questions_of.getTitleConvquestion();

        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
//            nextQuestion();

            for (int i = 0; i < qFullTexts.size(); i++) {
                String[] stringg = qFullTexts.get(i).getFulltext().replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
                if (qBodies.size() - 1 > i) {
                    surat_soal.add("##");
                }
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

            for (int i = 0; i < qBodies.size(); i++) {
                String[] stringg = qBodies.get(i).getBodies().replace("--", " -- ").split(" ");
                Log.e("sorat soal = ", stringg[i]);
                surat_soal.addAll(Arrays.asList(stringg));
                if (qBodies.size() - 1 > i) {
                    surat_soal.add("##");
                }
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];

            }

        }

        // play conversation
        textToSpeech = utils.tts(content, 1, questions_of.getSex());

        QuestionsActivity.blank.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (questions_of.getState() == 1) {

                } else {
                    QuestionsActivity.blank.setVisibility(View.GONE);
                }
            }
        }, content.length() * 80);


        List<String> answer1 = new ArrayList<>();
        String[] strings;
        for (int i = 0; i < qFullTexts.size(); i++) {
            strings = qFullTexts.get(i).getFulltext().split(":");
            answer1.add(strings[1]);
        }

        final List<String> str1 = new ArrayList<>();
        for (int i = 0; i < surat_soal.size(); i++) {
            str1.add(surat_soal.get(i).trim());
            Log.e("num list= ", str1.get(i));
        }

        final FunType10 funType10 = new FunType10();
        funType10.setSoratSoal(getContext(), str1, flow_head, answer1, new FunType10.fun10() {
            @Override
            public void SetEffectDisableJakhali(List<TextView> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(TextView textView) {

            }

            @Override
            public void WhenJakhaliClick(List<TextView> textView, int t) {

            }

            @Override
            public void SetEffectFirstPositionJakhali(TextView textView) {

            }

            @Override
            public void CustomTextViewSoratSoal(List<TextView> listJaKhaliha, int i) {
                listJaKhaliha.get(i).setPadding(5, 4, 4, 5);
                listJaKhaliha.get(position).setTextSize(18f);
                listJaKhaliha.get(position).setTextColor(Color.BLACK);
                boolean oi = listJaKhaliha.get(i).getTag().equals("1");
                if (oi)
                    listJaKhaliha.get(i).setBackgroundResource(R.drawable.circle_img_voice);
//                if (is){
//                    listJaKhaliha.get(i).setBackgroundColor(Color.RED);
//                }
            }

            @Override
            public void CustomVoice(TextView t) {
                t.setPadding(15, 15, 15, 15);
                t.setBackgroundResource(General.iconSeda);
            }

            @Override
            public void WhenVoiceClick(List<TextView> listJaKhaliha, int position, List<String> everyMokaleme) {
//                for (int i=0;i<everyMokaleme.size();i++){
//                    Log.e("Tag",everyMokaleme.get(i));
//                }

//                String[] strings = new String[qFullTexts.size()];

//                Log.e("positi =", position+ "");
//                Toast.makeText(getContext(), everyMokaleme.get(position), Toast.LENGTH_SHORT).show();

                textToSpeech = utils.tts(everyMokaleme.get(position), 1f, questions_of.getSex());

            }

            @Override
            public void WhenPO(List<TextView> listJaKhaliha, int pppsss, Integer integer) {

                tvFocus10 = ((TextView) listJaKhaliha.get(pppsss));
//                Log.e("position = ", listJaKhaliha.get(integer).getText().toString());
                Log.e("position = ", integer + "");
                arrayUser[0] = "def";
                String[] arr = qAnswers.get(integer).getAnswers().split("/");
                answer10 = arr[0];
                Log.e("answer position = ", answer10);
                sttGoogle(11, "");
//                Toast.makeText(getContext(), listJaKhaliha.get(pppsss).getText().toString(), Toast.LENGTH_SHORT).show();

            }

        });

    }

    TextView tvFocus10 = null;
    EditText editTextFocus11 = null;

    public void type11() {
        logType(11);
        TextView name_conv = view.findViewById(R.id.name_conv);
        TextView head9 = view.findViewById(R.id.head9);
//        disableCheck();
        if (questions_of.getHead().length() != 0) {
            head9.setText(questions_of.getHead());
        }

        if (!questions_of.getTitleConvquestion().equals("")) {
            name_conv.setText(questions_of.getTitleConvquestion());
        } else {
            name_conv.setText("");
        }
        payini = view.findViewById(R.id.llMain);
        List<String> surat_soal = new ArrayList<>();
        final FlowLayout flow_head = view.findViewById(R.id.flow_head);


        String content = "";
        content += questions_of.getTitleConvquestion();

        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
//            nextQuestion();

            for (int i = 0; i < qFullTexts.size(); i++) {
                String[] stringg = qFullTexts.get(i).getFulltext().replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
                if (qBodies.size() - 1 > i) {
                    surat_soal.add("##");
                }
                try {
                    String[] strings = qFullTexts.get(i).getFulltext().split(":");
                    content += "\n" + strings[1];
                } catch (Exception e) {

                }
            }

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

            for (int i = 0; i < qBodies.size(); i++) {
                String[] stringg = qBodies.get(i).getBodies().replace("--", " -- ").split(" ");
                Log.e("sorat soal = ", stringg[i]);
                surat_soal.addAll(Arrays.asList(stringg));
                if (qBodies.size() - 1 > i) {
                    surat_soal.add("##");
                }
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];

            }

        }

        // play conversation
        textToSpeech = utils.tts(content, 1, questions_of.getSex());

        QuestionsActivity.blank.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (questions_of.getState() == 1) {

                } else {
                    QuestionsActivity.blank.setVisibility(View.GONE);
                }
            }
        }, content.length() * 80);


        List<String> answer1 = new ArrayList<>();
        String[] strings1;
        for (int i = 0; i < qFullTexts.size(); i++) {
            strings1 = qFullTexts.get(i).getFulltext().split(":");
            answer1.add(strings1[1]);
        }


        final List<String> str1 = new ArrayList<>();
        for (int i = 0; i < surat_soal.size(); i++) {
            str1.add(surat_soal.get(i).trim());
            Log.e("num list= ", str1.get(i));
        }

        final FunType11 funType11 = new FunType11();
//        funType11.setSoratSoal(getContext(),);
        funType11.setSoratSoal(getContext(), str1, flow_head, answer1, new FunType11.fun11() {
            @Override
            public void SetEffectDisableJakhali(List<View> textView, int p) {

            }

            @Override
            public void SetEffectWhenEnableJakhali(EditText textView) {
            }

            @Override
            public void WhenJakhaliClick(List<View> textView, int t) {

            }

            @Override
            public void SetEffectFirstPositionJakhali(View textView) {

            }

            @Override
            public void CustomTextViewSoratSoal(List<View> listJaKhaliha, int i) {

                ((TextView) listJaKhaliha.get(i)).setPadding(5, 4, 4, 5);
                ((TextView) listJaKhaliha.get(i)).setTextSize(18f);
                ((TextView) listJaKhaliha.get(i)).setTextColor(Color.BLACK);
                boolean oi = listJaKhaliha.get(i).getTag().equals("1");
                if (oi)
                    listJaKhaliha.get(i).setBackgroundResource(R.drawable.shape);

            }

            @Override
            public void CustomVoice(View t) {

                t.setPadding(15, 15, 15, 15);
                t.setBackgroundResource(General.iconSeda);

            }

            @Override
            public void WhenVoiceClick(List<View> listJaKhaliha, int position, List<String> everyMokaleme) {
//                Toast.makeText(getContext(), everyMokaleme.get(position), Toast.LENGTH_SHORT).show();
                textToSpeech = utils.tts(everyMokaleme.get(position), 1f, questions_of.getSex());

            }

            @Override
            public void OnTextChanged(List<View> listJaKhaliha, int positionListJakhali, Integer integer, CharSequence s,
                                      int start, int before, int count) {
                editTextFocus11 = ((EditText) listJaKhaliha.get(positionListJakhali));
                Log.e("posi = ", positionListJakhali + "");
                Log.e("javab = ", funType11.getJavab().get(integer));
                arrayUser[0] = "def";
                String[] arr = qAnswers.get(integer).getAnswers().split("/");
                answer10 = arr[0];
                setItems11(s.toString());
            }

        });
    }

    String userAnswer = "";
    String answer = "";
    int s;

    private void setItems10(String userAnswer) {

        try {
            Log.e("user ans = ", userAnswer);
            Log.e("ans = ", answer10);

            if (!userAnswer.equals("") || getActivity() != null) {

                if (answer10.replace("’", "").replace("'", "")
                        .equals(userAnswer.replace("’", "").replace("'", ""))) {

                    Log.e("user ans size = ", arrayUser.length + "");
                    Log.e("ans size= ", qAnswers.size() + "");

                    for (int i = 0; i < qAnswers.size(); i++) {

                        Log.e("for user = ", s + "");
                        Log.e("array user = ", arrayUser[i] + "");

                        if (arrayUser[i].equals("def")) {
                            s++;
                            Log.e("arr user s = ", s + "");
                        } else {
                            Log.e("arr user = ", s + "");
                        }

                        if (s == qAnswers.size()) {
                            tvFocus10.setBackgroundResource(R.drawable.circle_img_voice_correct);
                            General.correctCToasty(getActivity());
                            increaseState();
                            updateProgress(true);
//                            nextQuestion();
                            justNextQuestion();
                        } else {
                            tvFocus10.setBackgroundResource(R.drawable.circle_img_voice_correct);
                            Toasty.success(activity, "درسته برو گزینه بعدی").show();
                        }
                    }

                } else {

                    General.wrongCToasty(answer10);
                    nextQuestion();

                }

            } else {

                assert getActivity() != null;
                Toasty.info(getActivity(), "ابتدا کادر مورد نظر را انتخاب کنید").show();

            }

        } catch (Exception e) {

        }
    }

    private void setItems11(String userAnswer) {

        try {

//            if (Type91011RecycleAdapter.card != null) {
//
//                if (select == 1){
//                    Type91011RecycleAdapter.selected = mic1;
//                }else if (select == 2){
//                    Type91011RecycleAdapter.selected = mic2;
//                }
//
//                Type91011RecycleAdapter.selected = Type91011RecycleAdapter.selected.replace("...", "");
//                Log.e("pos vertical position", Type91011RecycleAdapter.pos + "// POSITION horizontal= " + position);
//                Log.e("select ", Type91011RecycleAdapter.selected.replace("...", ""));
//                type9Adapter.notifyDataSetChanged();
//
//                // پر کردن ارایه دو بعدی با اندیس های مشخص pos = لیست عمودی  و position = لیست افقی
//                strings[Type91011RecycleAdapter.pos][position] = Type91011RecycleAdapter.selected;

//                QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {

//                        if (selectType == 10) {
//
//                            String content = "heyyy";
//
////                            for (int i = 0; i < strings.length; i++) {
////
////                                if (userAnswer == null) {
////                                    userAnswer = "";
////                                }
////
////                                for (int j = 0; j < strings[i].length; j++) {
////
////                                    if (userAnswer == null) {
////                                        userAnswer = "";
////                                    }
////
////                                    try {
////
////                                        userAnswer += strings[i][j];
////                                        Log.e("userAnswer=", userAnswer);
////
////                                    } catch (Exception e) {
////
////                                    }
////                                }
////                            }
//
//                            posNumber ++;

//                            String[] strings1;
//                            for (int i = 0; i < qAnswers.size(); i++) {
//                                strings1 = qAnswers.get(posNumber).getAnswers().split("/");
//                                Log.e("answer = ", strings1[posNumber]);
//                            }
//
//                            userAnswer = strings[Type91011RecycleAdapter.pos][position];
//                            answer = qAnswers.get(posNumber).getAnswers();
//                            answer = strings1[0];

//                            for (int i = 0; i < qAnswers.size(); i++) {
//                                answer += qAnswers.get(i).getAnswers();
//                                Log.e("Answer=", answer);
//                            }

//                            content = select9List.get(0).trim() + str1.trim() + " " + select9List.get(1).trim();
//                            content = userAnswer.trim();
//                            userAnswer = userAnswer.replace(" ", "").replace("null","").trim();
//                            answer = answer.replace(" ", "").replace("null","").trim();
//                            Log.e("content=", content + "// answer=" + qAnswers.get(0).getAnswers().trim());
//                            Log.e("userAnswerFinal=", userAnswer);
//                            Log.e("AnswerFinal=", answer);
//                            Log.e("posNumber=", posNumber+"");
//                            Log.e("total num =", numExist+"");
            Log.e("user ans = ", userAnswer);
            Log.e("ans = ", answer10);

            if (!userAnswer.equals("") || getActivity() != null) {

                if (answer10.replace("’", "").replace("'", "").replace("?", "").toLowerCase().trim()
                        .equals(userAnswer.replace("’", "").replace("'", "").replace("?", "").toLowerCase().trim())) {

                    Log.e("user ans size = ", arrayUser.length + "");
                    Log.e("ans size= ", qAnswers.size() + "");
                    for (int i = 0; i < qAnswers.size(); i++) {

                        Log.e("for user = ", s + "");
                        Log.e("array user = ", arrayUser[i] + "");

                        if (arrayUser[i].equals("def")) {
                            s++;
                            Log.e("arr user s = ", s + "");
                        } else {
                            Log.e("arr user = ", s + "");
                        }

                        if (s == qAnswers.size()) {
                            editTextFocus11.setBackgroundColor(Color.GREEN);
                            General.correctCToasty(getActivity());
                            increaseState();
                            updateProgress(true);
//                            nextQuestion();
                            justNextQuestion();
                        } else {
                            Toasty.success(activity, "درسته برو گزینه بعدی", Toast.LENGTH_SHORT).show();
                            editTextFocus11.setBackgroundColor(Color.GREEN);
                        }
                    }

                } else {

//                    Toast.makeText(activity, "wrong", Toast.LENGTH_SHORT).show();
                    editTextFocus11.setBackgroundColor(Color.RED);
                }

            } else {

                assert getActivity() != null;
                Toasty.info(getActivity(), "ابتدا کادر مورد نظر را انتخاب کنید").show();

            }
        } catch (Exception e) {

        }
    }

    private static String answer10 = "";
    private String[] arrayUser = {""};

    public void type12(View view) {
        // puzzle
        logType(12);
        selectType = 12;

        final String[] items = new String[qOptions.size() * 2];
        int[] pos = new int[qOptions.size() * 2];
        ImageAdapter.countPair = 0;

        for (int i = 0; i < qOptions.size(); i++) {
            items[i] = qOptions.get(i).getOptions();
            pos[i] = i;
            Log.e("type 12-1", " items = " + items[i] + " // pos " + pos[i]);

        }

        int i = 0;
        for (int j = qOptions.size(); j < qOptions.size() * 2; j++) {

            items[j] = qAnswers.get(i).getAnswers();
            pos[j] = i;
            Log.e("type 12-2", " item = " + i + "= " + items[i] + " // pos " + pos[i]);

            i++;

        }

        Log.e("rowssss =====", "row = " + qOptions.size());
        int num = 2;
        int row = qOptions.size() * 2;
        Log.e("row =====", "row = " + row);

        List<Bitmap> bitmaps = utils.crop(num, row);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setNumColumns(num);
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), items, pos, 0, bitmaps,
                new ImageAdapter.GoNextQuestion() {
                    @Override
                    public void next() {

                        General.correctCToasty(getActivity());
                        increaseState();
                        updateProgress(true);
//                nextQuestion();
                        justNextQuestion();

                    }
                });
        gridView.setAdapter(imageAdapter);

        doNotNeedCheck();
        if (questions_of.getState() == 1) {
            QuestionsActivity.blank.setVisibility(View.VISIBLE);
            QuestionsActivity.footer.setVisibility(View.VISIBLE);
            imageAdapter = new ImageAdapter(getActivity(), items, pos, 1, bitmaps, new ImageAdapter.GoNextQuestion() {
                @Override
                public void next() {

                }
            });
            gridView = (GridView) view.findViewById(R.id.gridView);
            gridView.setAdapter(imageAdapter);
        } else {
            QuestionsActivity.blank.setVisibility(View.GONE);
        }
    }

    Type13GridAdapter gridAdapter;
    public static int posCard = 0;

    private void type13() {
        logType(13);
        selectType = 13;
        final GridView grid_type13 = view.findViewById(R.id.gridview);


        numExist = 0;

        card1 = "";
        card2 = "";
        str1 = "";
        str2 = "";
        RecyclerView recycler_view = view.findViewById(R.id.recyclerview);
        TextView head9 = view.findViewById(R.id.head9);

        final List<Type12Model> stringList = new ArrayList<>();
        List<String> body = new ArrayList<>();

        for (int i = 0; i < qBodies.size(); i++) {

            body.add(qBodies.get(i).getBodies());

        }

        if (questions_of.getHead().length() != 0) {
            head9.setText(questions_of.getHead());
        }

//        body.add("A: good morning.");
//        body.add("B:- - , - - ?");
//        body.add("A: I’m fine, thank you.");
//        body.add("B:- - , - - ?");

//        body.add("C:hello - - how are u?dsgdgasdgdgdsaggagggd");
//        body.add("B:- - how gdgdgdfgfare u?dsgdgasdgdgdsaggagggd");
//        body.add("B:- - , - - how?");

        for (int i = 0; i < body.size(); i++) {

            int numCards = 0, numStr = 0;

            Log.e("carti", card1 + "");
            String content = null;

            content = body.get(i);
            Log.e("content type 13", content + "");

            String[] s = content.split("-");
            Log.e("stringBuilder length", s.length + "");

            for (int j = 0; j < s.length; j++) {

                if (s[j].trim().equals("")) {

                    numCards++;
                    Log.e(" numCards", numCards + "");

                    Log.e("stringBuilder numCards", s[j] + "");
                    if (numCards == 1) {
                        card1 = "exist";
                        numExist++;
                    } else if (numCards == 3) {
                        card2 = "exist";
                        numExist++;
                    }
                } else {
                    numStr++;
                    Log.e("strcards ", numStr + "");
                    Log.e("stringBuilder str", s[j] + "");
                    if (numStr == 1) {
                        str1 = s[j];
                    } else if (numStr == 2) {
                        str2 = s[j];
                    }
                }
            }

            strings = new String[numExist][numExist];

            stringList.add(new Type12Model("", card1, card2, str1, str2, content, qOptions.get(i).getOptions()));
            Log.e("loglist" + i, "num = 0" + "// card1 = " + card1 +
                    "// card2 = " + card2 + "// str1 = " + str1 + "// str2 = " + str2 + "// content = " + content);

        }

        for (int i = 0; i < stringList.size(); i++) {
            Log.e("string list = ", stringList.get(i).getContent());
        }


        final List<String> listAlph = new ArrayList<>();
        for (int i = 0; i < qAnswers.size(); i++) {
            listAlph.add(qAnswers.get(i).getAnswers());
            Log.e("qAnswer = ", qAnswers.get(i).getAnswers());
        }

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);
        final Type13Adapter type13Adapter = new Type13Adapter(getActivity(), qAnswers, stringList,
                questions_of.getState(), new Type13Adapter.OnClickListener() {
            @Override
            public void onClickType13(RecyclerView.ViewHolder viewHolder, int position) {

                try {

//                     جای خالی رو مشخص میکنه ک جای خالی 1 یا 2 توی ی خط هستش
                    posCard = position;
                    Log.e("list item", listAlph.get(Type13Adapter.pos));
                    // convert word to alphabet
                    listGridAlphabet = wordToAlph(listAlph.get(Type13Adapter.pos));
                    Collections.shuffle(listGridAlphabet);

                    gridAdapter = new Type13GridAdapter(listGridAlphabet, getActivity());
                    grid_type13.setAdapter(gridAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        recycler_view.setAdapter(type13Adapter);
        select9List = new ArrayList<>();


        grid_type13.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    Type13Adapter.selected += listGridAlphabet.get(position);
//                    Toast.makeText(getActivity(), alphabt + "", Toast.LENGTH_SHORT).show();

                    strings[Type13Adapter.pos][posCard] = Type13Adapter.selected;

                    listGridAlphabet.remove(position);
                    gridAdapter.notifyDataSetChanged();
                    type13Adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                        if (selectType == 13) {

                for (int i = 0; i < strings.length; i++) {

                    if (userAnswer == null) {
                        userAnswer = "";
                    }


                    for (int j = 0; j < strings[i].length; j++) {

                        if (userAnswer == null) {
                            userAnswer = "";
                        }

                        try {

                            userAnswer += strings[i][j];
                            Log.e("userAnswer=", userAnswer);

                        } catch (Exception e) {

                        }
                    }
                }

                for (int i = 0; i < qAnswers.size(); i++) {
                    answer += qAnswers.get(i).getAnswers();
                    Log.e("Answer=", answer);
                }


                Log.e("userAnswerFinal=", userAnswer.replace(" ", "")
                        .replace("null", "").trim());

                Log.e("AnswerFinal=", answer.replace(" ", "")
                        .replace("null", "").trim());

                if (!userAnswer.equals("") || getActivity() != null) {

                    if (userAnswer.replace(" ", "").replace("null", "").trim().
                            equals(answer.replace(" ", "").replace("null", "").trim())) {

                        General.correctCToasty(getActivity());
                        increaseState();
                        updateProgress(true);

                    } else {

                        General.wrongCToasty(answer);
                        reduceScore();

                    }

                    nextQuestion();
                } else {

                    assert getActivity() != null;
                    Toasty.info(getActivity(), "ابتدا کادر مورد نظر را انتخاب کنید").show();

                }
//                        }
            }
        });

    }

    private List<String> wordToAlph(String word) {

        List<String> stringList = new ArrayList<>();

        for (int j = 0; j < word.length(); j++) {

//                int random = randoms.nextInt(alphabet.length());
            stringList.add(word.substring(j, j + 1));

        }
        return stringList;
    }

    String original = "";

    private void type14() {
        logType(14);
        RecyclerView myrecyclerview = view.findViewById(R.id.rv_fruits);
        TextView head14 = view.findViewById(R.id.head14);

        head14.setText(questions_of.getHead());

        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        Type14Adapter adapter = new Type14Adapter();
        SwipeAndDragHelper swipeAndDragHelper = new SwipeAndDragHelper(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeAndDragHelper);
        adapter.setTouchHelper(touchHelper);
        myrecyclerview.setAdapter(adapter);
        touchHelper.attachToRecyclerView(myrecyclerview);

        List<String> items = new ArrayList<>();
        for (int i = 0; i < qAnswers.size(); i++) {
            String s = qAnswers.get(i).getAnswers();
            items.add(s);
            original += s;
        }
//        items.add("monday");
//        items.add("sunday");
//        items.add("tuesday");
//        items.add("wednesday");
//        items.add("thursday");
//        items.add("friday");
//        items.add("saturday");

        adapter.setUserList(questions_of.getState(), items);

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("original", original);
                Log.e("new list", Type14Adapter.implode);

                if (original.equals(Type14Adapter.implode)) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    General.wrongCToasty(original);
                    reduceScore();

                }

                nextQuestion();

            }
        });

    }

    Type15RecycleAdapter type15RecycleAdapter;
    int tempPos;
    int gridTempPos;
    List<Type15> temp = null;
    List<String> gridTemp = null;

    public void type15() {
        logType(15);
//        Log.e(WITCH_TYPE,"type15");
//        doNotNeedCheck();

//        Matn zirnevis koli bayad @@ dashte bashe     Jakhali bayad -- bashad
        List<List<String>> listList = new ArrayList<>();

        List<List<String>> listList2 = new ArrayList<>();
        List<String> javab = new ArrayList<>();

        for (int i = 0; i < qAnswers.size(); i++) {
            if (i == 0) {
                javab.add(qAnswers.get(i).getAnswers());
            } else {
                javab.add(" " + qAnswers.get(i).getAnswers());
            }

            Log.e("javaaaaaaaaaab", "javabb = " + javab.get(i));
        }

        // --------------------------------------------

        TextView name_conv = view.findViewById(R.id.name_conv);
        TextView head9 = view.findViewById(R.id.head9);

        if (questions_of.getHead().length() != 0) {
            head9.setText(questions_of.getHead());
        }

        if (!questions_of.getTitleConvquestion().equals("")) {
            name_conv.setText(questions_of.getTitleConvquestion());
        }

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> surat_soal;
        final FlowLayout flow_head = view.findViewById(R.id.flow_head);

        String content = "";
        content += questions_of.getTitleConvquestion();


        String[] zirnevis = questions_of.getSubtitle().split("&");
        String zir = "";

        // when answered
        if (questions_of.getState() == 1) {

//            QuestionsActivity.blank.setVisibility(View.GONE);
//            nextQuestion();

            Toast.makeText(activity, "state = end", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < qFullTexts.size(); i++) {
                surat_soal = new ArrayList<>();
                if (zirnevis.length > 1) {
                    if (!(zirnevis[i].equals(""))) {
                        zir = zirnevis[i];
                    } else {
                        zir = "این جا محل زیر نویس است";
                    }
                }

                String s = qFullTexts.get(i).getFulltext() + " @@" + zir;
                String[] stringg = s.replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
//                if (qBodies.size()-1 > i) {
//                    surat_soal.add("##");
//                }
                for (int j = 0; j < stringg.length; j++) {
                    Log.e("add item = ", stringg[j]);
                }
                listList.add(surat_soal);
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }

        } else {
//            Toast.makeText(activity, "state = starttt", Toast.LENGTH_SHORT).show();
//            QuestionsActivity.blank.setVisibility(View.GONE);
            for (int i = 0; i < qBodies.size(); i++) {
                surat_soal = new ArrayList<>();
                if (zirnevis.length > 1) {
                    if (!(zirnevis[i].equals(""))) {
                        zir = zirnevis[i];
                    } else {
                        zir = "این جا محل زیر نویس است";
                    }
                }

                String s = qBodies.get(i).getBodies() + " @@" + zir;
                String[] stringg = s.replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
//                if (qBodies.size()-1 > i) {
//                    surat_soal.add("##");
//                }
                for (int j = 0; j < stringg.length; j++) {
                    Log.e("add item = ", stringg[j]);
                }
                listList.add(surat_soal);
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }
        }


        for (int i = 0; i < listList.size(); i++) {
            for (int j = 0; j < listList.get(i).size(); j++) {
                Log.e("add item list list = ", listList.get(i).get(j));
            }
        }

        // play conversation
        textToSpeech = utils.tts(content, 1, questions_of.getSex());

        QuestionsActivity.blank.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (questions_of.getState() == 1) {

                } else {
                    QuestionsActivity.blank.setVisibility(View.GONE);
                }
            }
        }, content.length() * 80);


        List<String> answer;
        int size = 0;
        int size2 = 0;
        for (int i = 0; i < qAnswers.size(); i++) {
            answer = new ArrayList<>();
            size = i * 3;
            size2 = (i + 1) * 3;
            for (int j = size; j < size2; j++) {
                answer.add(qOptions.get(j).getOptions());
//                Log.e("answeer = ", j+" // "+answer.get(j));
            }
            listList2.add(answer);
            Log.e("answeer = ", listList2 + "");
        }

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < qFullTexts.size(); i++) {
            String[] strings = qFullTexts.get(i).getFulltext().split(":");
            stringList.add(strings[1]);
        }

        Fun16 fun16=new Fun16();
        fun16.main(getContext(), flow_head, payini, listList, listList2, javab, javab, new Fun16.funInterface() {
            @Override
            public void WhenCorrectGozine(TextView textView) {
                textView.setTextColor(Color.GREEN);
            }

            @Override
            public void CustomTextViewMokaleme(List<TextView> textView, int pos) {
                textView.get(pos).setPadding(4,4,4,4);
            }

            @Override
            public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception
            {

            }

            @Override
            public void SetonclickGozine(TextView txtViewGozine, TextView txtViewJakhaliEnable)
            {

            }

            @Override
            public void WhenGozineFalse(TextView t)
            {
                t.setTextColor(Color.RED);
            }

            @Override
            public void CustomTxtViewGozineha(List<TextView> lstTxtViewGozine, int i, ViewGroup.LayoutParams layoutParams)
            {
                lstTxtViewGozine.get(i).setPadding(4,4,4,4);
                lstTxtViewGozine.get(i).setBackgroundColor(Color.WHITE);
                lstTxtViewGozine.get(i).setTextColor(Color.BLACK);

            }

            @Override
            public void WhenDone()
            {

                nextQuestion();
            }

            @Override
            public void CustomVoice(TextView t)
            {

            }

            @Override
            public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme)
            {

            }

            @Override
            public void CannotAddNewGozine()
            {

            }

            @Override
            public void CustomViewAdamak(List<ImageView> adamakHa)
            {

            }

            @Override
            public void customFlowLayoutMokaleme(List<FlowLayout> flowLayoutList)
            {

            }

            @Override
            public void CustomTxtViewMokaleme(TextView textView) {
                textView.setPadding(4,4,4,4);

            }

            @Override
            public void CustomLinearMainMokalemeHa(List<LinearLayout> linearLayoutList, ViewGroup.LayoutParams layoutParams) {

            }
        });
        // --------------------------------------
//        FunType166 funType16 = new FunType166();
//        funType16.main(getContext(), listList, flow_head, listList2, payini, javab, stringList,
//                new FunType166.funType15() {
//                    @Override
//                    public void CustomTextViewMokaleme(List<TextView> textViews, int position) {
//                        textViews.get(position).setPadding(5, 5, 5, 5);
//                        textViews.get(position).setTextSize(18f);
////                textViews.get(position).setBackgroundColor(Color.WHITE);
//                        textViews.get(position).setTextColor(Color.BLACK);
//                    }
//
//                    @Override
//                    public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception {
//                        textView.setBackgroundResource(R.drawable.shape);
//                        textView.setText("");
//                        Toast.makeText(getContext(), "gozine = " + gozineha.size(), Toast.LENGTH_SHORT).show();
//
//                        // back
//                        for (int i = 0; i < gozineha.size(); i++) {
//                            gozineha.get(i).setTextColor(Color.BLACK);
//                            gozineha.get(i).setBackgroundColor(Color.WHITE);
//                            gozineha.get(i).setEnabled(true);
//                            Toast.makeText(activity, "" + gozineha.get(i).getText().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void SetonclickGozine(TextView textView) {
//                        textView.setBackgroundColor(Color.GRAY);
//                        textView.setTextColor(Color.GRAY);
//
////                        for (int i = 0; i < FunType8And9.getStrJakhaliha(flow_head).size(); i++) {
////                            Log.e("size = ", FunType8And9.getStrJakhaliha(flow_head).get(i));
////                        }
////                        Toast.makeText(getContext(), "Gozine", Toast.LENGTH_SHORT).show();
//                        textView.setEnabled(false);
//                    }
//
//                    @Override
//                    public void WhenGozineTrue(TextView t) {
//                        t.setBackgroundColor(Color.GREEN);
//                    }
//
//                    @Override
//                    public void WhenGozineFalse(TextView t) {
//                        t.setBackgroundColor(Color.RED);
//                    }
//
//                    @Override
//                    public void CustomTxtViewGozineha(List<TextView> textViews, int position) {
//                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
//                        textViews.get(position).setTextSize(18f);
//                        textViews.get(position).setBackgroundColor(Color.WHITE);
//                        textViews.get(position).setTextColor(Color.BLACK);
//                    }
//
//                    @Override
//                    public void WhenDone() {
//                        General.correctCToasty(activity);
//                        increaseState();
//                        updateProgress(true);
//                        nextQuestion();
//                    }
//
//                    @Override
//                    public void CustomVoice(TextView t) {
//                        t.setPadding(15, 15, 15, 15);
//                        t.setBackgroundResource(General.iconSeda);
//                    }
//
//                    @Override
//                    public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme) {
//                        Log.e("cont = ", "// " + everyMokaleme.get(finalI));
//                        textToSpeech = utils.tts(everyMokaleme.get(finalI), 1f, questions_of.getSex());
//                    }
//
//                    @Override
//                    public void CannotAddNewGozine() {
//
//                    }
//                });
    }

    public void type16() {
        logType(16);
//        doNotNeedCheck();

//        Matn zirnevis koli bayad @@ dashte bashe     Jakhali bayad -- bashad
        List<List<String>> listList = new ArrayList<>();
        List<List<String>> listList2 = new ArrayList<>();

        List<String> javab = new ArrayList<>();
        for (int i = 0; i < qAnswers.size(); i++) {
            javab.add(qAnswers.get(i).getAnswers());
        }

        // --------------------------------------------

        TextView name_conv = view.findViewById(R.id.name_conv);
        TextView head9 = view.findViewById(R.id.head9);
        if (questions_of.getHead().length() != 0) {
            head9.setText(questions_of.getHead());
        }

        if (!questions_of.getTitleConvquestion().equals("")) {
            name_conv.setText(questions_of.getTitleConvquestion());
        }

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> surat_soal = new ArrayList<>();
        final FlowLayout flow_head = view.findViewById(R.id.flow_head);

        String content = "";
        content += questions_of.getTitleConvquestion();


        String[] zirnevis = questions_of.getSubtitle().split("&");
        String zir = "";
        // when answered
        if (questions_of.getState() == 1) {

//            QuestionsActivity.blank.setVisibility(View.GONE);
//            nextQuestion();

            Toast.makeText(activity, "state = end", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < qFullTexts.size(); i++) {
                surat_soal = new ArrayList<>();
                if (zirnevis.length > 1) {
                    if (!(zirnevis[i].equals(""))) {
                        zir = zirnevis[i];
                    } else {
                        zir = "این محل زیر نویس است";
                    }
                }
                String s = qFullTexts.get(i).getFulltext() + " @@" + zir;
                String[] stringg = s.replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
//                if (qFullTexts.size()-1 > i) {
//                    surat_soal.add("##");
//                }
                for (int j = 0; j < stringg.length; j++) {
                    Log.e("add item = ", stringg[j]);
                }
                listList.add(surat_soal);
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }

        } else {

//            Toast.makeText(activity, "state = starttt", Toast.LENGTH_SHORT).show();
//            QuestionsActivity.blank.setVisibility(View.GONE);
            for (int i = 0; i < qBodies.size(); i++) {
                surat_soal = new ArrayList<>();
                if (zirnevis.length > 1) {
                    if (!(zirnevis[i].equals(""))) {
                        zir = zirnevis[i];
                    } else {
                        zir = "این محل زیر نویس است";
                    }
                }
                String s = qBodies.get(i).getBodies() + " @@" + zir;
                String[] stringg = s.replace("--", " -- ").split(" ");
                surat_soal.addAll(Arrays.asList(stringg));
//                if (qBodies.size()-1 > i) {
//                    surat_soal.add("##");
//                }
                for (int j = 0; j < stringg.length; j++) {
                    Log.e("add item = ", stringg[j]);
                }
                listList.add(surat_soal);
                String[] strings = qFullTexts.get(i).getFulltext().split(":");
                content += "\n" + strings[1];
            }
        }

        for (int i = 0; i < listList.size(); i++) {
            for (int j = 0; j < listList.get(i).size(); j++) {
                Log.e("add item list list = ", listList.get(i).get(j));
            }
            Log.e("add item list list = ", "=-----------------------------------");
        }

        // play conversation
        textToSpeech = utils.tts(content, 1, questions_of.getSex());

        QuestionsActivity.blank.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (questions_of.getState() == 1) {

                } else {
                    QuestionsActivity.blank.setVisibility(View.GONE);
                }
            }
        }, content.length() * 80);


        List<String> answer;
        for (int i = 0; i < qOptions.size(); i++) {
            Log.e("opt = ", qOptions.get(i).getOptions() + " //");
            String[] strings = qOptions.get(i).getOptions().split(",");
            answer = new ArrayList<>();
            for (int j = 0; j < strings.length; j++) {
                answer.add(strings[j]);
                Log.e("answeer = ", j + " // " + answer.get(j));
                Log.e("answeer in = ", answer + " //");
            }
            listList2.add(answer);
            Log.e("answeer = ", listList2 + " //");
            Log.e("answeeer1 = ", "=-----------------------------------");
        }


        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < qFullTexts.size(); i++) {
            String[] strings = qFullTexts.get(i).getFulltext().split(":");
            stringList.add(strings[1]);
        }

        Fun15 fun15=new Fun15();
        fun15.main(getContext(), flow_head, payini, listList, listList2, javab, javab, new Fun15.funInterface() {
            @Override
            public void WhenCorrectGozine(TextView textView) {
                textView.setTextColor(Color.GREEN);
            }

            @Override
            public void CustomTextViewMokaleme(List<TextView> textView, int pos) {
                textView.get(pos).setPadding(4,4,4,4);

            }

            @Override
            public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception {

            }

            @Override
            public void SetonclickGozine(TextView txtViewGozine, TextView txtViewJakhaliEnable) {

            }

            @Override
            public void WhenGozineFalse(TextView t) {

            }

            @Override
            public void CustomTxtViewGozineha(List<TextView> lstTxtViewGozine, int i, ViewGroup.LayoutParams layoutParams) {

            }

            @Override
            public void WhenDone() {

            }

            @Override
            public void CustomVoice(TextView t) {

            }

            @Override
            public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme) {

            }

            @Override
            public void CannotAddNewGozine() {

            }

            @Override
            public void CustomViewAdamak(List<ImageView> adamakHa) {

            }

            @Override
            public void customFlowLayoutMokaleme(List<FlowLayout> flowLayoutList) {

            }

            @Override
            public void CustomTxtViewMokaleme(TextView textView) {

            }

            @Override
            public void CustomLinearMainMokalemeHa(List<LinearLayout> linearLayoutList, ViewGroup.LayoutParams layoutParams) {

            }
        });
//        FunType15 funType15 = new FunType15();
//        funType15.main(getContext(), listList, flow_head, listList2, payini, javab, stringList,
//                new FunType15.funType15() {
//                    @Override
//                    public void CustomTextViewMokaleme(List<TextView> textViews, int position) {
//                        textViews.get(position).setPadding(5, 5, 5, 5);
//                        textViews.get(position).setTextSize(18f);
////                textViews.get(position).setBackgroundColor(Color.WHITE);
//                        textViews.get(position).setTextColor(Color.BLACK);
//
//                    }
//
//                    @Override
//                    public void SetOnClickJakhali(TextView textView, List<TextView> gozineha) throws Exception {
//                        textView.setBackgroundResource(R.drawable.shape);
//                        textView.setText("------");
////                        textView.setText("       ");
////                        Toast.makeText(getContext(), "Jakhali", Toast.LENGTH_SHORT).show();
//
//                        // back
//                        for (int i = 0; i < gozineha.size(); i++) {
//                            gozineha.get(i).setTextColor(Color.BLACK);
//                            gozineha.get(i).setBackgroundColor(Color.WHITE);
//                            gozineha.get(i).setEnabled(true);
//                        }
//                    }
//
//                    @Override
//                    public void SetonclickGozine(TextView textView) {
//                        textView.setBackgroundColor(Color.GRAY);
//                        textView.setTextColor(Color.GRAY);
//
//                        for (int i = 0; i < FunType8And9.getStrJakhaliha(flow_head).size(); i++) {
//                            Log.e("size = ", FunType8And9.getStrJakhaliha(flow_head).get(i));
//
//                        }
//                        textView.setEnabled(false);
////                        Toast.makeText(getContext(), "Gozine", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void WhenGozineTrue(TextView t) {
//                        t.setBackgroundColor(Color.GREEN);
//                    }
//
//                    @Override
//                    public void WhenGozineFalse(TextView t) {
//                        t.setBackgroundColor(Color.RED);
//                    }
//
//                    @Override
//                    public void CustomTxtViewGozineha(List<TextView> textViews, int position) {
//                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
//                        textViews.get(position).setTextSize(18f);
//                        textViews.get(position).setBackgroundColor(Color.WHITE);
//                        textViews.get(position).setTextColor(Color.BLACK);
//                    }
//
//                    @Override
//                    public void WhenDone() {
//                        General.correctCToasty(activity);
//                        increaseState();
//                        updateProgress(true);
//                        nextQuestion();
//                    }
//
//                    @Override
//                    public void CustomVoice(TextView t) {
//                        t.setPadding(15, 15, 15, 15);
//                        t.setBackgroundResource(General.iconSeda);
//                    }
//
//                    @Override
//                    public void WhenVoiceClick(List<TextView> lstTxtViewMokaleme, int finalI, List<String> everyMokaleme) {
//                        Log.e("cont = ", "// " + everyMokaleme.get(finalI));
//                        textToSpeech = utils.tts(everyMokaleme.get(finalI), 1f, questions_of.getSex());
//                    }
//
//                    @Override
//                    public void CannotAddNewGozine() {
//
//                    }
//                });

        // --------------------------------------

        Log.e("lislist = ", listList.size() + "");
        Log.e("lislist2 = ", listList2.size() + "");

    }

    public void type17() {
        logType(17);
        TextView head16 = view.findViewById(R.id.head16);
        final TextView txt_body16 = view.findViewById(R.id.txt_body16);
        final EditText et_answer16 = view.findViewById(R.id.et_answer16);
        final ImageView btn_voice = view.findViewById(R.id.btn_voice);
        final String strings = txt_body16.getText().toString();

        btn_voice.setEnabled(false);
//        disableCheck();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_voice.setEnabled(true);
            }
        }, 50);

        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                utils.tts(strings,1f,questions_of.getSex());
                textToSpeech = utils.tts(strings, 1f, "w");

            }
        });
        head16.setText(questions_of.getHead());
        txt_body16.setText(qBodies.get(0).getBodies());


        textToSpeech = utils.tts(strings, 1, "w");

        et_answer16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_answer16.getText().length() == 0) {
//                    disableCheck();
                } else {
                    enableCheck();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (qAnswers.get(0).getAnswers().replace("،", "").equals(et_answer16.getText().toString().replace("،", ""))) {

                    General.correctCToasty(getActivity());
                    increaseState();
                    updateProgress(true);

                } else {

                    General.wrongCToasty(qAnswers.get(0).getAnswers());
                    reduceScore();

                }

                nextQuestion();
            }
        });

        // --------------------------------- subtitle

        payini = view.findViewById(R.id.llMain);
        balayi = view.findViewById(R.id.balayi);
        List<String> zirnevis = new ArrayList<>();
        List<String> surat_soal;
        RelativeLayout relativeLayout = view.findViewById(R.id.root);
        FlowLayout flow_head = view.findViewById(R.id.flow_head);
        String[] title = qBodies.get(0).getBodies().split(" ");

        String ssss = questions_of.getSubtitle();
        String[] subtitle = ssss.split("&");


        Log.e("subtitle == ", subtitle.length + "");
        for (int i = 0; i < title.length; i++) {

            zirnevis.add(addToZirnevisList(subtitle, title, i));
        }

        for (int i = 0; i < title.length; i++) {
            Log.e("zirnevis == ", zirnevis.get(i));
        }

        if (getLang(title[0]).equals("en")) {
            surat_soal = Arrays.asList(title);
            modeSurat = OptionsSetSub.ENGLISH;
        } else {
            surat_soal = Arrays.asList(title);
            Collections.reverse(surat_soal);
            Collections.reverse(zirnevis);
            modeSurat = OptionsSetSub.PERSIAN;
        }


        OptionsSetSub optionsSetSub = new OptionsSetSub();
        optionsSetSub.main(getActivity(),
                modeSurat, -1,
                typeList,
                zirnevis, surat_soal, balayi, payini, flow_head, relativeLayout, typeList.size(),
                new OptionsSetSub.OptionInterface() {
                    @Override
                    public void CustomTextViewSoal(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(0, 0, 0, 0);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void CustomTextViewBottom(List<TextView> textViews, int position) {
                        textViews.get(position).setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textViews.get(position).setTextSize(18f);
                        textViews.get(position).setBackgroundColor(Color.WHITE);

                    }

                    @Override
                    public void CustomTextViewTop(TextView textView) {
                        textView.setPadding(paddingTop, paddingTop, paddingTop, paddingTop);
                        textView.setTextSize(18f);
                        textView.setTextColor(Color.GRAY);
                        textView.setBackgroundColor(Color.WHITE);
                    }

                    @Override
                    public void SetOnClickBottomOption(TextView textViews) {
                        textViews.setTextColor(Color.GRAY);
                        textViews.setBackgroundColor(Color.GRAY);
                        enableCheck();
                    }

                    @Override
                    public void SetOnClickUpperOption(TextView textView) {
                        textView.setBackgroundColor(Color.GRAY);
                        String s = "";
                        for (int i = 0; i < OptionsSetSub.getStrListTxtView(balayi, modeBottom).size(); i++) {
                            s += OptionsSetSub.getStrListTxtView(balayi, modeBottom).get(i);
                        }

                        if ((OptionsSetSub.getStrListTxtView(balayi, modeBottom).size() - 1) == 0) {
//                            disableCheck();
//                            Toast.makeText(activity, "disable", Toast.LENGTH_SHORT).show();
                        } else {
                            enableCheck();
//                            Toast.makeText(activity, "enable", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("s = ", s);

                    }

                    @Override
                    public void EfectOnBottomOptionWhenUpperClick(TextView textView) {
                        textView.setBackgroundColor(Color.WHITE);
                        textView.setTextColor(Color.GRAY);
                    }
                }
        );

        // -------------------------------------------

        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
            et_answer16.setText(qAnswers.get(0).getAnswers());

        } else {

            QuestionsActivity.blank.setVisibility(View.GONE);

        }
    }

    String[] select;
    String answer18 = "";
    Type18RecycleAdapter type18RecycleAdapter;

    public void type18() {
        logType(18);
        RecyclerView recycler_view = view.findViewById(R.id.recyclerview);
        final GridView gridview = view.findViewById(R.id.gridview);
        TextView head18 = view.findViewById(R.id.head9);

//        disableCheck();
        head18.setText(questions_of.getHead());

        List<String> qustionList = new ArrayList<>();
        for (int i = 0; i < qOptions.size(); i++) {
            qustionList.add(qOptions.get(i).getOptions());
        }

        final List<String> answerList = new ArrayList<>();
        String answers;
        for (int i = 0; i < qAnswers.size(); i++) {
            answers = qAnswers.get(i).getAnswers().trim();
            answerList.add(answers);
            answer18 += answers;
        }

        select = new String[qustionList.size()];
        // when answered
        if (questions_of.getState() == 1) {

            QuestionsActivity.blank.setVisibility(View.VISIBLE);
            QuestionsActivity.footer.setBackgroundResource(R.color.colorPrimaryDark);
            Log.e("state = ", questions_of.getState() + "");
            for (int i = 0; i < qAnswers.size(); i++) {
                select[i] = qAnswers.get(i).getAnswers();
                Log.e("state = ", select[i] + "");
            }

        }

        type18RecycleAdapter = new Type18RecycleAdapter(getActivity(), qustionList,
                questions_of.getState(), select, new Type18RecycleAdapter.SelectItem() {
            @Override
            public void selectListener(int position) {

                try {
                    if (!select[position].trim().equals("")) {
                        answerList.add(select[position]);
                        select[position] = "";
                        adapter.notifyDataSetChanged();
                        type18RecycleAdapter.notifyDataSetChanged();
                    }


                    if (answerList.size() != 0) {
//                        disableCheck();
                    } else {
                        enableCheck();
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(manager);
        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(type18RecycleAdapter);


        // --------------------------  grid View

        adapter = new Type2Adapter(answerList, getActivity(), 0, 0);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    if (type18RecycleAdapter.pos == -1) {

                        Toasty.warning(App.context, "ابتدا روی جای خالی مورد نظر کلیک کنید").show();

                    } else {

                        Type18RecycleAdapter.selected = answerList.get(position);

                        select[type18RecycleAdapter.pos] = Type18RecycleAdapter.selected;

                        answerList.remove(position);
                        adapter.notifyDataSetChanged();
                        type18RecycleAdapter.notifyDataSetChanged();

                    }


                    if (answerList.size() != 0) {
//                        disableCheck();
                    } else {
                        enableCheck();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.e("pos = ", String.valueOf(type18RecycleAdapter.pos));

            }
        });


        QuestionsActivity.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                        if (selectType == 13) {

//                Toast.makeText(getActivity(), "18Type", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < select.length; i++) {

                    if (userAnswer == null) {
                        userAnswer = "";
                    }

                    userAnswer += select[i] + "\n";
                    Log.e("userAnswer=", userAnswer);

                }

                for (int i = 0; i < qAnswers.size(); i++) {
                    answer += qAnswers.get(i).getAnswers() + "\n";
                    Log.e("Answer=", answer);
                }


                Log.e("userAnswerFinal=", userAnswer.replace(" ", "")
                        .replace("null", "").trim());

                Log.e("AnswerFinal=", answer.replace(" ", "")
                        .replace("null", "").trim());

                if (!userAnswer.equals("") || getActivity() != null) {

                    if (userAnswer.replace(" ", "").replace("null", "").trim().
                            equals(answer.replace(" ", "").replace("null", "").trim())) {

                        General.correctCToasty(getActivity());
                        increaseState();
                        updateProgress(true);
                    } else {

                        General.wrongCToasty(answer);
                        reduceScore();

                    }

                    nextQuestion();
                } else {

                    assert getActivity() != null;
                    Toasty.info(getActivity(), "ابتدا کادر مورد نظر را انتخاب کنید").show();

                }
//                        }
            }
        });

    }

    public void sttGoogle(int REQUEST_CODE, String lang) {

        if (lang.equals("")) {
            lang = "en-US";
        }

        if (utils.isConnected()) {

//استفاده از اینتنت برای فراخوانی و بررسی دسترسی گوشی به اینترنت
            try {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // fa-IR, en-US
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                startActivityForResult(intent, REQUEST_CODE);
            } catch (Exception e) {
                nextQuestion();
                Log.e("No", "Not Expet Recognize");
            }
        } else {
            Toast.makeText(getActivity(), "اینترنت متصل نیست", Toast.LENGTH_LONG).show();
        }
    }

    //    استفاده از اکتیوتی ریسالت برای نتیجه گیری از دریافت اطلاعات از google voice و  نمایش ان بروی لیست ویو
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            ArrayList<String> result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (requestCode == 4) {

                txt_answer.setText(result.get(0));
//                if (!(result.get(0).equals(""))){
//                    enableCheck();
//                }

                nextQuestion();

                for (int i = 0; i < qAnswers.size(); i++) {
                    boolean b = qAnswers.get(i).getAnswers().replace("؟", "").equals(txt_answer.getText().toString());
                    Log.e("answerrr", qAnswers.get(i).getAnswers().replace("؟", "").trim());
                    if (b) {

                        General.correctCToasty(getActivity());
                        increaseState();
                        updateProgress(true);
                        return;

                    } else {

                        if (i == qAnswers.size() - 1) {
                            General.wrongCToasty(qAnswers.get(i).getAnswers().replace("؟", ""));
                            reduceScore();
                            return;
                        }
                    }
                }

            } else if (requestCode == 11) {

                mic1 = result.get(0);
                setItems10(mic1);
                if (!(result.get(0).equals(""))) {
                    enableCheck();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {

//        Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
    }
    // =======================================================

    // وقتی اشتباه جواب بده از امتیازش یکی کم میکنه
    private void reduceScore() {
        try {


            int r = Integer.parseInt(questions_ofList.get(QuestionsActivity.questionNum).getScore()) - 1;
            if (r <= 0) {
                r = 0;
            }
            questions_of.setScore(String.valueOf(r));
            questions_of.setState(-1);
            questions_of.save();

            Log.e("Reduce@@@", questions_of.getScore());
//            questions_of.getScore();

//            SQLite.update(Questions_Of.class).set(Questions_Of_Table.state.is(1))
//                    .where(Questions_Of_Table.lesson_id.eq(lessonId))
//                    .and(Questions_Of_Table.sections.eq(String.valueOf(sublessonid)))
//                    .execute();
//
//            SQLite.update(Questions_Of.class).set(Questions_Of_Table.score.is(String.valueOf(r)))
//                    .where(Questions_Of_Table.lesson_id.eq(lessonId))
//                    .and(Questions_Of_Table.sections.eq(String.valueOf(sublessonid)))
//                    .execute();

//            Toast.makeText(getActivity(), "new score = " + questions_ofList.get(QuestionsActivity.questionNum).getScore(), Toast.LENGTH_SHORT).show();
//
//            Toast.makeText(getActivity(), "new state = " + questions_ofList.get(QuestionsActivity.questionNum).getState(),
//                    Toast.LENGTH_SHORT).show();

            utils.mediaPlayer(R.raw.wrong_s);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    LevelOf levelOf;

    // وقتی درست جواب بدخ کم نمیکنه
    private void increaseState() {

        questions_of.setState(1);
        questions_of.save();

        /**  increase score and set */
        String newScore = "";
        Log.e("new score 0= ", questions_of.getScore());
        if (questions_of.getScore() == null) {
            newScore = "0";
            Log.e("new score 0= ", newScore);
        } else {
            newScore = questions_of.getScore();
            Log.e("new score else ", newScore);
        }

        assert levelOf != null;
        int totalScoree = levelOf.getTotal_score() + Integer.parseInt(newScore);
//        levelOf.setTotal_score(totalScore);
//        levelOf.save();
        SQLite.update(LevelOf.class)
                .set(LevelOf_Table.total_score.is(totalScoree))
                .where(LevelOf_Table.id.eq(activeid))
                .execute();
        Log.e("scoreer = ", totalScoree + "");
        QuestionsActivity.point.setText(totalScoree + "");


        /**   move progress      * */

        utils.mediaPlayer(R.raw.correct_s);

    }

    FragmentTransaction fragmentTransaction;

    // رفرش میکنه صفحه رو
    public void changeQusetionPosition() {

        try {
            Log.e("Nextr", "S");
            android.support.v4.app.FragmentManager ffmm = getActivity().getSupportFragmentManager();
            if (ffmm != null) {
                fragmentTransaction = ffmm.beginTransaction();
//            if (fragmentTransaction==null){
//            }
                fragmentTransaction.replace(R.id.main_container, new MainFragment());
                fragmentTransaction.commit();
            } else {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    //     میره سوال بعدی
    private void nextQuestion() {

        QuestionsActivity.goOn.setVisibility(View.VISIBLE);
        QuestionsActivity.result_question.setVisibility(View.VISIBLE);
        QuestionsActivity.check.setVisibility(View.GONE);
        QuestionsActivity.goOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    changeQusetionPosition();
                    QuestionsActivity.questionNum++;
                    QuestionsActivity.result_question.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void justNextQuestion() {

        changeQusetionPosition();
        QuestionsActivity.questionNum++;

        QuestionsActivity.result_question.setVisibility(View.GONE);

    }

    private void wrongAnswerList() {

        if (questions_ofList.size() == 0) {

            // -1 means answered to this lesson before
            LessonOf lessonOf = new LessonOf();
            lessonOf.setStep("-1");
            lessonOf.save();

            getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            totalScore = 0;

            for (int i = 0; i < questions_ofList.size(); i++) {

                totalScore += Integer.parseInt(questions_of.getScore());

            }

//            new Request<Data>(getActivity()).setScore(lessonId, String.valueOf(totalScore), new CallBack<Data>() {
//                @Override
//                public void onRequestSuccessful(Data data) {

//                    Log.e("send score = ", data.getMessage());

//                    Toast.makeText(getActivity(), "send score successful", Toast.LENGTH_SHORT).show();

//                }
//            });

        }

    }

    private String getSectionStep(int section) {

        // چون براساس lesson id داره فیلتر میکنع 0 رو میذاریم
        StepSubLessonOf stepSubLessonOf = stepSubLessonOfList.get(0);
        String step = "";

        switch (section) {

            case 1:
                step = stepSubLessonOf.getStep1();
                break;
            case 2:
                step = stepSubLessonOf.getStep2();
                break;
            case 3:
                step = stepSubLessonOf.getStep3();
                break;
            case 4:
                step = stepSubLessonOf.getStep4();
                break;
            case 5:
                step = stepSubLessonOf.getStep5();
                break;
            case 6:
                step = stepSubLessonOf.getStep6();
                break;
            case 7:
                step = stepSubLessonOf.getStep7();
                break;

        }


        return step;
    }

    private void setSectionStep(String value) {

        int sectionId = Integer.parseInt(section);
        Property<String> step = null;


        switch (sectionId) {

            case 1:
                step = StepSubLessonOf_Table.step1;
//                stepSubLessonOf.setStep1(value);
                break;
            case 2:
                step = StepSubLessonOf_Table.step2;
//                stepSubLessonOf.setStep2(value);
                break;
            case 3:
                step = StepSubLessonOf_Table.step3;
//                stepSubLessonOf.setStep3(value);
                break;
            case 4:
                step = StepSubLessonOf_Table.step4;
//                stepSubLessonOf.setStep4(value);
                break;
            case 5:
                step = StepSubLessonOf_Table.step5;
//                stepSubLessonOf.setStep5(value);
                break;
            case 6:
                step = StepSubLessonOf_Table.step6;
//                stepSubLessonOf.setStep6(value);
                break;
            case 7:
                step = StepSubLessonOf_Table.step7;
//                stepSubLessonOf.setStep7(value);
                break;

        }

//        stepSubLessonOf.save();
        assert step != null;
        SQLite.update(StepSubLessonOf.class).set(step.is(value))
                .where(StepSubLessonOf_Table.lesson_id.eq(Integer.valueOf(lessonId)))
//                .and(StepSubLessonOf_Table.sub_lesson_id.eq(sectionId))
                .execute();

//        Toast.makeText(getActivity(), "step == "+ step, Toast.LENGTH_SHORT).show();
    }

    // از جایی ک سوال مونده میاره
    private void setSectionProgress(int progress) {

        int sectionId = Integer.parseInt(section);
        Property<Integer> step = null;


        switch (sectionId) {

            case 1:
                step = ProgressSec_Table.progress1;
//                stepSubLessonOf.setStep1(value);
                break;
            case 2:
                step = ProgressSec_Table.progress2;
//                stepSubLessonOf.setStep2(value);
                break;
            case 3:
                step = ProgressSec_Table.progress3;
//                stepSubLessonOf.setStep3(value);
                break;
            case 4:
                step = ProgressSec_Table.progress4;
//                stepSubLessonOf.setStep4(value);
                break;
            case 5:
                step = ProgressSec_Table.progress5;
//                stepSubLessonOf.setStep5(value);
                break;
            case 6:
                step = ProgressSec_Table.progress6;
//                stepSubLessonOf.setStep6(value);
                break;
            case 7:
                step = ProgressSec_Table.progress7;
//                stepSubLessonOf.setStep7(value);
                break;

        }

//        stepSubLessonOf.save();
        assert step != null;
        SQLite.update(ProgressSec.class).set(step.is(progress))
                .where(ProgressSec_Table.lesson_id.eq(Integer.valueOf(lessonId)))
                .execute();

    }

    int userScore = 0;
    int score = 0;
    int newPermission;

    String title = "";
    String nextpart = "";
    // برای فهمیدن این ک کدوم سکشن پاسخ داده شده
    int sec = -1;

    // get key and open the lock of next question

    private void permissionToNextLesson() {

        List<Questions_Of> questions_ofListCorrect = SQLite.select().from(Questions_Of.class)
                .where(Questions_Of_Table.lesson_id.eq(lessonId))
                .and(Questions_Of_Table.sections.eq(section))
                .and(Questions_Of_Table.state.eq(1))
                .queryList();


        stepSubLessonOfList = SQLite.select().from(StepSubLessonOf.class)
                .where(StepSubLessonOf_Table.lesson_id.eq(Integer.valueOf(lessonId)))
//                .and(StepSubLessonOf_Table.sub_lesson_id.eq(sublessonid))
                .queryList();

        Log.e("size of question = ", stepSubLessonOfList.size() + "");
        Log.e("size correct question", questions_ofListCorrect.size() + "");
        Log.e("position= ", position + "");
        try {
            Log.e("getStep7", stepSubLessonOfList.get(position).getStep7() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  ---------------  test
//        lessonSize = 5;

//        if (lessonSize == questions_ofListCorrect.size()){
        if ((stepSubLessonOfList.get(0).getStep7().equals("-1"))) {
            // NEXT LESSON
            Toast.makeText(getActivity(), "permis", Toast.LENGTH_SHORT).show();
            Log.e("next level = ", "next level opened");
            /** new permission == position of lesson list that unlock  */
            newPermission = position + 1 + 1;
            Log.e("new permission = ", newPermission + "");
            if (newPermission <= lessonSize) {

                SQLite.update(LessonOf.class).set(LessonOf_Table.permission.is(String.valueOf(1)))
                        .where(LessonOf_Table.title.eq(String.valueOf(newPermission)))
                        .execute();
                Log.e("next lesson = ", newPermission + "");


                //=============================   GET COIN    =============================

                // در صورتی سکه میگیره، که به همه سوالات درس درست جواب داده باشه
                int sumScoreLesson = 0;
                for (int i = 0; i < questions_ofListCorrect.size(); i++) {
                    sumScoreLesson += Integer.parseInt(questions_ofListCorrect.get(i).getScore());
                }

                //  ---------------  test
//                sumScoreLesson = 50;
                Log.e("lessonSize = ", (lessonSize * 10) + "");
                Log.e("sumScoreLesson = ", (sumScoreLesson) + "");

                if ((lessonSize * 10) == sumScoreLesson) {
                    funGetCoin();
                    Toasty.success(getContext(), "شما یک سکه گرفتید").show();
                    Log.e("get coin = ", "you get a coin");

                } else {

                    Toasty.info(getContext(), "شما نتوانستید سکه ای بگیرید").show();
                    Log.e("didn't get coin = ", "you didn't get a coin");

                }

                //=============================      =============================

            } else {

                //  NEXT LEVEL
                /**  this part when user answer to all of lessons in this part the user can go to next LEVEL     --------  */

                List<LevelOf> levelOfList = SQLite.select().from(LevelOf.class).queryList();
                int levelSize = levelOfList.size();
                int activeLevel = levelOfList.get(0).getActiveLevel() + 1;
                Log.e("next level = ", "next level opened");

                if (activeLevel <= levelSize) {
                    SQLite.update(LevelOf.class).set(LevelOf_Table.permission.is(1))
                            .where(LevelOf_Table.id.eq(activeLevel))
                            .execute();
                } else {

                    // THE END
                    Toasty.success(App.context, "تبریک میگم شما به پایان برنامه رسیدید").show();
                    Log.e("congratulation = ", "yessssssssssss");
                }
            }

        }

        // سکشن ست شد

//        userScore = (questions_ofListCorrect.size()) * 10;

        for (int i = 0; i < questions_ofListCorrect.size(); i++) {
            userScore += Integer.parseInt(questions_ofListCorrect.get(i).getScore());
            QuestionsActivity.score_end.setText(" امتیاز شما: " + userScore);
            score += 10;
        }

        setSectionFrameLayout(newPermission);

    }

    private void funGetCoin() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText("با موفقیت یک سکه گرفتید");
        sweetAlertDialog.show();

        prefManager.addOneCoin();
        QuestionsActivity.seke.setText(prefManager.getAllCoin());
    }

    int userScoreLesson = 0, totalScoreLesson = 0;

    @SuppressLint("ResourceAsColor")
    private void setSectionFrameLayout(final int nextLesson) {

        sec = Integer.parseInt(section);

        if ((stepSubLessonOfList.get(position).getStep1().equals("-1")) || sec == 1) {
            QuestionsActivity.txt2.setTextColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle2.setBorderColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle2.setColorFilter(Color.parseColor("#FF99D0FD"));

            QuestionsActivity.txt1.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle1.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle1.setColorFilter(Color.parseColor("#fcd8c3"));

            QuestionsActivity.tik1.setVisibility(View.VISIBLE);
            title = "بخش اول به پایان رسید";
            nextpart = "بخش دوم برای شما فعال شد";

        } else {
            QuestionsActivity.sec_layou1.setVisibility(View.VISIBLE);
        }

        if ((stepSubLessonOfList.get(position).getStep2().equals("-1")) || sec == 2) {

            QuestionsActivity.tik2.setVisibility(View.VISIBLE);
            QuestionsActivity.txt3.setTextColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle3.setBorderColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle3.setColorFilter(Color.parseColor("#FF99D0FD"));

            QuestionsActivity.txt2.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle2.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle2.setColorFilter(Color.parseColor("#fcd8c3"));
            title = "بخش دوم به پایان رسید";
            nextpart = "بخش سوم برای شما فعال شد";
        } else {

//            QuestionsActivity.sec_layou2.setVisibility(View.VISIBLE);
        }

        if ((stepSubLessonOfList.get(position).getStep3().equals("-1")) || sec == 3) {

            QuestionsActivity.tik3.setVisibility(View.VISIBLE);
            QuestionsActivity.txt4.setTextColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle4.setBorderColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle4.setColorFilter(Color.parseColor("#FF99D0FD"));

            QuestionsActivity.txt3.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle3.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle3.setColorFilter(Color.parseColor("#fcd8c3"));
            nextpart = "بخش چهارم برای شما فعال شد";
            title = "بخش سوم به پایان رسید";
        } else {
//            QuestionsActivity.sec_layou3.setVisibility(View.VISIBLE);
        }

        if ((stepSubLessonOfList.get(position).getStep4().equals("-1")) || sec == 4) {

            QuestionsActivity.tik4.setVisibility(View.VISIBLE);
            QuestionsActivity.txt5.setTextColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle5.setBorderColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle5.setColorFilter(Color.parseColor("#FF99D0FD"));

            QuestionsActivity.txt4.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle4.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle4.setColorFilter(Color.parseColor("#fcd8c3"));
            nextpart = "بخش پنجم برای شما فعال شد";
            title = "بخش چهارم به پایان رسید";
        } else {
//            QuestionsActivity.sec_layou4.setVisibility(View.VISIBLE);
        }

        if ((stepSubLessonOfList.get(position).getStep5().equals("-1")) || sec == 5) {

            QuestionsActivity.tik5.setVisibility(View.VISIBLE);
            QuestionsActivity.txt6.setTextColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle6.setBorderColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle6.setColorFilter(Color.parseColor("#FF99D0FD"));

            QuestionsActivity.txt5.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle5.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle5.setColorFilter(Color.parseColor("#fcd8c3"));
            nextpart = "بخش ششم برای شما فعال شد";
            title = "بخش پنجم به پایان رسید";
        } else {
//            QuestionsActivity.sec_layou5.setVisibility(View.VISIBLE);
        }

        if ((stepSubLessonOfList.get(position).getStep6().equals("-1")) || sec == 6) {

            QuestionsActivity.tik6.setVisibility(View.VISIBLE);
            QuestionsActivity.txt7.setTextColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle7.setBorderColor(Color.parseColor("#18769e"));
            QuestionsActivity.circle7.setColorFilter(Color.parseColor("#FF99D0FD"));

            QuestionsActivity.txt6.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle6.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle6.setColorFilter(Color.parseColor("#fcd8c3"));
            nextpart = "بخش هفتم برای شما فعال شد";
            title = "بخش ششم به پایان رسید";

        } else {
//            QuestionsActivity.sec_layou6.setVisibility(View.VISIBLE);
        }

        if ((stepSubLessonOfList.get(position).getStep7().equals("-1")) || sec == 7) {

            QuestionsActivity.tik7.setVisibility(View.VISIBLE);
            QuestionsActivity.txt7.setTextColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle7.setBorderColor(Color.parseColor("#ca7d00"));
            QuestionsActivity.circle7.setColorFilter(Color.parseColor("#fcd8c3"));
            title = "بخش هفتم به پایان رسید";
            nextpart = " برای شما فعال شد" + nextLesson + "درس ";

            // کنترل کردن این که ایا به اندازه کافی امتیاز کرفته ؟
            questions_ofList = SQLite.select().from(Questions_Of.class)
                    .where(Questions_Of_Table.lesson_id.eq(lessonId))
                    .queryList();

            for (int i = 0; i < questions_ofList.size(); i++) {
                userScoreLesson += Integer.parseInt(questions_ofList.get(i).getScore());
                totalScoreLesson += 10;
            }
            if ((userScoreLesson / totalScoreLesson) < 0.75) {
                nextpart = "شما موفق به گذراندان این درس نشدید لطفا دوباره به همه سوالات این درس پاسخ دهید";
            } else {
                nextpart = " برای شما فعال شد" + nextLesson + "درس ";
            }

        } else {
//            QuestionsActivity.sec_layou7.setVisibility(View.VISIBLE);
        }


        QuestionsActivity.title_end.setText(title);
        QuestionsActivity.nextpart.setText(nextpart);

        Log.e("=====", "=============================");
        Log.e("userScoreeee = ", userScore + "");
        Log.e("Scorerrrrr = ", score + "");
        Log.e("title = ", title);
        Log.e("nextPart = ", nextpart);
        Log.e("questions_ofList = ", questions_ofList.size() + "");

        // پروگرس
        if (score != 0)
            QuestionsActivity.progress_end.setText((userScore * 100) / (score) + "");
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(QuestionsActivity.progress_end, "progress"
                , 0f, (userScore * 100) / (score));
        objectAnimator.setDuration(2000);
        objectAnimator.start();

        // تکست وسط پروگرس
        int count = ((userScore * 100) / (score));
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, count);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                QuestionsActivity.txt_progress.setText(String.valueOf(animation.getAnimatedValue() + "%"));
            }
        });
        animator.setDuration(2000); // here you set the duration of the anim
        animator.start();

    }

}