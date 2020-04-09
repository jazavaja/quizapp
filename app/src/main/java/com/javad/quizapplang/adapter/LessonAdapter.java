package com.javad.quizapplang.adapter;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.cachapa.expandablelayout.ExpandableLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.javad.quizapplang.App;
import com.javad.quizapplang.QuestionsActivity;
import com.javad.quizapplang.R;
import com.javad.quizapplang.model.Progress;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LessonOf_Table;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.model.dbFlow.ProgressSec;
import com.javad.quizapplang.model.dbFlow.StepSubLessonOf;
import com.javad.quizapplang.model.dbFlow.StepSubLessonOf_Table;
import com.javad.quizapplang.model.dbFlow.SubLessonOf;
import com.javad.quizapplang.model.dbFlow.SubLessonOf_Table;
import com.javad.quizapplang.model.dbFlow.questions.QAnswers;
import com.javad.quizapplang.model.dbFlow.questions.QBodies;
import com.javad.quizapplang.model.dbFlow.questions.QFullText;
import com.javad.quizapplang.model.dbFlow.questions.QOptions;
import com.javad.quizapplang.model.dbFlow.questions.QPathPhoto;
import com.javad.quizapplang.model.dbFlow.questions.Questions_Of;
import com.javad.quizapplang.model.dbFlow.questions.Questions_Of_Table;
import com.javad.quizapplang.model.questionsOnline.Question;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.General;
import com.javad.quizapplang.utils.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

/**
 * Created by AMIR on 9/28/2018.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    List<LessonOf> lessonModelsList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    private int lastPosition = -1;
    private SubLessonOf subLessonOf;
    List<SubLessonOf> subLessonOfs = new ArrayList<>();
    List<StepSubLessonOf> stepSubLessonOfList = new ArrayList<>();

    public LessonAdapter(List<LessonOf> lessonModelsList, Context context) {
        this.lessonModelsList = lessonModelsList;
        this.context = context;
        this.inflater = LayoutInflater.from(App.context);
        subLessonOf = new SubLessonOf();
    }

    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lesson, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LessonAdapter.ViewHolder holder, final int position) {

        final LessonOf lessonModel = lessonModelsList.get(position);
        holder.name_les.setText(lessonModel.getDescription());
//        lessonModel.getTitle();
        holder.title.setText("درس "+lessonModel.getTitle());
        holder.name.setText(lessonModel.getName());
        final int total_num = lessonModel.getTotal_num();
        final int permission = Integer.parseInt(lessonModel.getPermission());
        Log.e("free space = ", Utils.getFreeRamSize() + "");
        stepSubLessonOfList = SQLite.select().from(StepSubLessonOf.class).queryList();

        subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

        getSectionStep(lessonModel,position, holder);

// چک کردن آیا درس پولش پرداخت شده یا ن  اگر 0 بود ینی پرداخت شده واگر 1 بود پرداخت نشده
        if (lessonModel.getStatus().equals("0")) {

            // permission 1 -> actvice
            // permission 0 -> diactvice
            if (lessonModel.getPermission().equals("1")) {

                // اگه هیچکدومشون 0 نبود ینی دانلود شدن
                if (subLessonOfs.get(position).getSec1() != 0 && subLessonOfs.get(position).getSec2() != 0
                        && subLessonOfs.get(position).getSec3() != 0 && subLessonOfs.get(position).getSec4() != 0
                        && subLessonOfs.get(position).getSec5() != 0 && subLessonOfs.get(position).getSec6() != 0
                        && subLessonOfs.get(position).getSec7() != 0) {

                    Log.e("total dl = ", "total downloaded");
                    holder.total_dl.setVisibility(View.INVISIBLE);

                }

            }else {

                holder.total_dl.setImageResource(R.drawable.ic_lock);
                holder.total_dl.setColorFilter(R.color.colorBlack);

            }

        }else {

        }

            holder.total_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lessonModel.getPermission().equals("1")) {

                    if (lessonModel.getStatus().equals("0")) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        if (subLessonOfs.get(position).getSec1() == 0 || subLessonOfs.get(position).getSec2() == 0
                                || subLessonOfs.get(position).getSec3() == 0 || subLessonOfs.get(position).getSec4() == 0
                                || subLessonOfs.get(position).getSec5() == 0 || subLessonOfs.get(position).getSec6() == 0
                                || subLessonOfs.get(position).getSec7() == 0) {

                            totalGetQuestionList(position, subLessonOfs, lessonModel,
                                    lessonModelsList.get(position).getId() + "", "", holder, holder.total_dl);

                        } else {

                            Toasty.info(context, "همه قسمت ها دانلود شده است").show();

                        }
                    } else {

                        Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                    }

                }else {
                    Toasty.info(context, "پس از اتمام درس قبلی و گرفتن امتیاز کافی این قسمت برای شما باز میشود").show();
                }
            }
        });


        try {

            Picasso.with(App.context).load(new File(lessonModel.getImgPath()))
                    .placeholder(R.drawable.ic_person)
                    .into(holder.img);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // set item
        animation(holder, position);

        Log.e("lessons bind", "title = "+ holder.title.getText().toString()+
                "// name = "+ holder.name.getText().toString()+"// img = "+ lessonModel.getImgPath());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.ex_item.isExpanded()){

                    holder.fl_progress.setVisibility(View.GONE);
                    holder.ex_item.collapse();

                }else {

                    List<Questions_Of> questions_ofListCorrect = SQLite.select().from(Questions_Of.class)
                            .where(Questions_Of_Table.lesson_id.eq(String.valueOf(lessonModel.getId())))
                            .and(Questions_Of_Table.state.eq(1))
                            .queryList();

                    int correct = questions_ofListCorrect.size();

                    Log.e("correct = ", String.valueOf(correct));
                    Log.e("total = ", String.valueOf(total_num));
                    int cal = 0;
                    try {
                        cal = ((correct * 100) / (total_num));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e("`sum = ", String.valueOf(cal));

                    holder.fl_progress.setVisibility(View.VISIBLE);
                    holder.circleProgressBar.setProgress(cal);
                    holder.circleProgressBar.setText(""+cal);
                    holder.ex_item.expand();

                }

            }
        });

        //نشون میده چیا دانلود شده اگه دانلود شده باشه 1 وگرنه 0

        final int sec1 = subLessonOfs.get(position).getSec1();
        int sec2 = subLessonOfs.get(position).getSec2();
        int sec3 = subLessonOfs.get(position).getSec3();
        int sec4 = subLessonOfs.get(position).getSec4();
        int sec5 = subLessonOfs.get(position).getSec5();
        int sec6 = subLessonOfs.get(position).getSec6();
        int sec7 = subLessonOfs.get(position).getSec7();

        Log.e("sec1", sec1+"");
        Log.e("sec2", sec2+"");
        Log.e("sec3", sec3+"");
        Log.e("sec4", sec4+"");
        Log.e("sec5", sec5+"");
        Log.e("sec6", sec6+"");
        Log.e("sec7", sec7+"");
        Log.e("ssub lesson id", subLessonOfs.get(position).getId()+"");

        setDownloadImage(sec1, holder.img_sec1);
        setDownloadImage(sec2, holder.img_sec2);
        setDownloadImage(sec3, holder.img_sec3);
        setDownloadImage(sec4, holder.img_sec4);
        setDownloadImage(sec5, holder.img_sec5);
        setDownloadImage(sec6, holder.img_sec6);
        setDownloadImage(sec7, holder.img_sec7);

        /**  progress logs  ------------------------------------------------------ */


        List<ProgressSec> subSecList = SQLite.select().from(ProgressSec.class).queryList();
        ProgressSec subSec1 = subSecList.get(position);

        Log.e("size sub sec list", subSecList.size()+"");
        Log.e("lesson_id", subSec1.getLesson_id()+"");
        Log.e("sub lesson id", subSec1.getSub_lesson_id()+"");
        Log.e("progress 1", subSec1.getProgress1()+"");
        Log.e("progress 2", subSec1.getProgress2()+"");
        Log.e("progress 3", subSec1.getProgress3()+"");
        Log.e("progress 4", subSec1.getProgress4()+"");
        Log.e("progress 5", subSec1.getProgress5()+"");
        Log.e("progress 6", subSec1.getProgress6()+"");
        Log.e("progress 7", subSec1.getProgress7()+"");

        /**  ------------------------------------------------------------------  */

        // level
        final LevelOf levelOf = SQLite.select().from(LevelOf.class).querySingle();

        holder.sec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        Log.e("-----------------------", "=========-----------------=========-----------------");
                        Log.e("permission11", permission + "");
                        Log.e("position11", position + "");

                        Log.e("sec1", subLessonOf.getSec1() + "");
                        Log.e("sec1", subLessonOf.getLesson_id() + " == " + lessonModel.getId());

                        try {

                            if (subLessonOfs.get(position).getSec1() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 1,
                                        lessonModelsList.get(position).getId() + "", "1", holder.img_sec1);

                            } else {

                                Log.e("position", position + "");
                                Log.e("permission", lessonModelsList.get(position).getPermission());
                                if (lessonModelsList.get(position).getPermission().equals("1") || position == 0) {

//                                Toast.makeText(context, "permission = ok", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "1");
                                    intent.putExtra("position", position);
                                    intent.putExtra("questionssize", total_num);
                                    intent.putExtra("lessonsize", lessonModelsList.size());
                                    intent.putExtra("sublessonid", subLessonOfs.get(position).getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    context.startActivity(intent);

                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();

                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toasty.info(App.context, "خظایی پیش آمده").show();
                        }

                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {
                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();
                }
            }
        });

        holder.sec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        try {

                            Log.e("sec2", subLessonOfs.get(position).getSec2() + "");
//                Log.e("sec2", subLessonOf.getLesson_id() +" == "+ lessonModel.getId());

                            if (subLessonOfs.get(position).getSec2() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 2,
                                        lessonModelsList.get(position).getId() + "", "2", holder.img_sec2);

                            } else {

                                if (position <= permission) {

//                                if (lessonModelsList.get(position).getPermission().equals("1")) {

//                    Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "2");
                                    intent.putExtra("sublessonid=", subLessonOf.getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    intent.putExtra("lessonsize", total_num);
                                    context.startActivity(intent);

//                                } else {
//
//                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
//                                }
                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();

                                }
                            }
                        } catch (Exception e) {

                            Toasty.info(App.context, "این قسمت فعلا غیر فعال است فقط sec2 از درس اول فعال است").show();
                        }
                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {

                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                }
            }
        });

        holder.sec3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        try {

                            Log.e("sec3", subLessonOfs.get(position).getSec3() + "");
//                Log.e("sec3", subLessonOf.getLesson_id() +" == "+ lessonModel.getId());

                            if (subLessonOfs.get(position).getSec3() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 3,
                                        lessonModelsList.get(position).getId() + "", "3", holder.img_sec3);

                            } else {

//                                if (lessonModelsList.get(position).getPermission().equals("1")) {
                                if (position <= permission) {

//                    Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "3");
                                    intent.putExtra("sublessonid=", subLessonOf.getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    intent.putExtra("lessonsize", total_num);
                                    context.startActivity(intent);

                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {

//                    Toasty.info(App.context, "این قسمت فعلا غیر فعال است فقط sec2 از درس اول فعال است").show();
                        }

                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {

                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                }
            }
        });

        holder.sec4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        try {

                            Log.e("sec4", subLessonOf.getSec4() + "");
                            Log.e("sec4", subLessonOf.getLesson_id() + " == " + lessonModel.getId());

                            if (subLessonOfs.get(position).getSec4() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 4,
                                        lessonModelsList.get(position).getId() + "", "4", holder.img_sec4);

//                        subLessonOfs.get(position).setSec4(1);
//                        subLessonOf.setLesson_id(lessonModel.getId());
//                        Log.e("sec4", subLessonOf.getSec4() + "");
//                        subLessonOfs.get(position).save();

                            } else {

//                            if (lessonModelsList.get(position).getPermission().equals("1")) {
                                if (position <= permission) {

                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "4");
                                    intent.putExtra("sublessonid=", subLessonOf.getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    intent.putExtra("lessonsize", total_num);
                                    context.startActivity(intent);
//                    Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (Exception e) {
                            Toasty.info(App.context, "این قسمت فعلا غیر فعال است فقط sec2 از درس اول فعال است").show();

                        }

                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {

                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                }
            }
        });

        holder.sec5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        try {

                            Log.e("sec5", subLessonOf.getSec5() + "");
                            Log.e("sec5", subLessonOf.getLesson_id() + " == " + lessonModel.getId());

                            if (subLessonOfs.get(position).getSec5() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 5,
                                        lessonModelsList.get(position).getId() + "", "5", holder.img_sec5);

                            } else {

//                            if (lessonModelsList.get(position).getPermission().equals("1")) {
                                if (position <= permission) {

                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "5");
                                    intent.putExtra("sublessonid=", subLessonOf.getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    intent.putExtra("lessonsize", total_num);
                                    context.startActivity(intent);
//                    Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            Toasty.info(App.context, "این قسمت فعلا غیر فعال است فقط sec2 از درس اول فعال است").show();

                        }

                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {

                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                }
            }
        });

        holder.sec6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        try {

                            Log.e("sec6", subLessonOf.getSec6() + "");
                            Log.e("sec6", subLessonOf.getLesson_id() + " == " + lessonModel.getId());

                            if (subLessonOfs.get(position).getSec6() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 6,
                                        lessonModelsList.get(position).getId() + "", "6", holder.img_sec6);

                            } else {

//                            if (lessonModelsList.get(position).getPermission().equals("1")) {
                                if (position <= permission) {

                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "6");
                                    intent.putExtra("sublessonid=", subLessonOf.getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    intent.putExtra("lessonsize", total_num);
                                    context.startActivity(intent);
//                    Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            Toasty.info(App.context, "این قسمت فعلا غیر فعال است فقط sec2 از درس اول فعال است").show();
                        }
                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {

                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                }
            }
        });

        holder.sec7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (lessonModel.getStatus().equals("0")) {

                    //کنترل خالی بودن حافظه
                    if (Utils.getFreeRamSize() >= 100) {

                        List<SubLessonOf> subLessonOfs = SQLite.select().from(SubLessonOf.class).queryList();

                        try {

                            Log.e("sec7", subLessonOf.getSec7() + "");
                            Log.e("sec7", subLessonOf.getLesson_id() + " == " + lessonModel.getId());

                            if (subLessonOfs.get(position).getSec7() == 0) {

                                getQuestionList(position, subLessonOfs, lessonModel, 7,
                                        lessonModelsList.get(position).getId() + "", "7", holder.img_sec7);

                            } else {

//                            if (lessonModelsList.get(position).getPermission().equals("1")) {
                                if (position <= permission) {

                                    Intent intent = new Intent(context, QuestionsActivity.class);
                                    intent.putExtra("lesson_id", lessonModel.getId() + "");
                                    intent.putExtra("section", "7");
                                    intent.putExtra("sublessonid=", subLessonOf.getId());
                                    assert levelOf != null;
                                    intent.putExtra("activeid", levelOf.getPermission());
                                    intent.putExtra("lessonsize", total_num);
                                    context.startActivity(intent);

                                } else {

                                    Toast.makeText(context, "اجازه ورود به این بخش را ندارید", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            Toasty.info(App.context, "این قسمت فعلا غیر فعال است فقط sec2 از درس اول فعال است").show();
                        }

                    } else {
                        Toasty.error(context, "حافظه گوشی کمتر از حد نیاز است لطفا حافظه گوشی را خالی کنید.").show();
                    }
                }else {

                    Toasty.error(context, "ابتدا باید هزینه فعالسازی آن را بپردازید").show();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return lessonModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, title, name_les;
        RelativeLayout root;
        ExpandableLayout ex_item;
        CircleImageView img;
        ImageView img_sec1,img_sec2, img_sec3, img_sec4, img_sec5, img_sec6, img_sec7, total_dl,
                icon_sec1,icon_sec2,icon_sec3,icon_sec4,icon_sec5,icon_sec6,icon_sec7;
        LinearLayout sec1,sec2,sec3,sec4,sec5,sec6,sec7;
        FrameLayout fl_progress;
        CircleProgressBar circleProgressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            circleProgressBar = itemView.findViewById(R.id.progressBarr);
            fl_progress = itemView.findViewById(R.id.fl_progress);
            root = itemView.findViewById(R.id.root);
            total_dl = itemView.findViewById(R.id.total_dl);
            img_sec1 = itemView.findViewById(R.id.img_sec1);
            img_sec2 = itemView.findViewById(R.id.img_sec2);
            img_sec3 = itemView.findViewById(R.id.img_sec3);
             img_sec4= itemView.findViewById(R.id.img_sec4);
            img_sec5 = itemView.findViewById(R.id.img_sec5);
            img_sec6 = itemView.findViewById(R.id.img_sec6);
            img_sec7 = itemView.findViewById(R.id.img_sec7);

            name_les = itemView.findViewById(R.id.name_les);

            ex_item = itemView.findViewById(R.id.ex_item);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.civ);

            sec1 = itemView.findViewById(R.id.sec1);
            sec2 = itemView.findViewById(R.id.sec2);
            sec3 = itemView.findViewById(R.id.sec3);
            sec4 = itemView.findViewById(R.id.sec4);
            sec5 = itemView.findViewById(R.id.sec5);
            sec6 = itemView.findViewById(R.id.sec6);
            sec7 = itemView.findViewById(R.id.sec7);

            icon_sec1 = itemView.findViewById(R.id.icon_sec1);
            icon_sec2 = itemView.findViewById(R.id.icon_sec2);
            icon_sec3 = itemView.findViewById(R.id.icon_sec3);
            icon_sec4 = itemView.findViewById(R.id.icon_sec4);
            icon_sec5 = itemView.findViewById(R.id.icon_sec5);
            icon_sec6 = itemView.findViewById(R.id.icon_sec6);
            icon_sec7 = itemView.findViewById(R.id.icon_sec7);

        }

        }

    private void getQuestionList(final int position , final List<SubLessonOf> subLessonOfs, final LessonOf lessonOf, final int sec,
                                 final String lessonId, final String section, final ImageView imageView){

            new Request<Data>(context).getQuestionList(lessonId, section, new CallBack<Data>() {
                @Override
                public void onRequestSuccessful(Data data) {

                    ArrayList<Question> questionArrayList = (ArrayList<Question>) data.getQuestions();
                    Progress progressList = data.getProgress();

                    try {

                        for (int i = 0; i < questionArrayList.size(); i++) {

                            String section = questionArrayList.get(i).getSectionquestion();
                            String type = questionArrayList.get(i).getTypequestion();
                            String score = questionArrayList.get(i).getScoresquestion();
                            String head = questionArrayList.get(i).getFullQuestionquestion().getHeadquestion();
                            List<String> body = questionArrayList.get(i).getFullQuestionquestion().getBodyquestion();
                            List<String> opts = (List<String>) questionArrayList.get(i).getOptquestion();
                            List<String> answers = questionArrayList.get(i).getAnswersquestion();
                            List<String> pathPhotos = questionArrayList.get(i).getPathPhotosquestion();
                            List<String> fullText = questionArrayList.get(i).getFullText();
                            String subtitle = questionArrayList.get(i).getSubtitlequestion();
                            String sex = questionArrayList.get(i).getSex();
                            String titleConvertsation = questionArrayList.get(i).getFullQuestionquestion().getTitleConvquestion();

                            saveInDatabase(head, type, score, section, lessonId, body, opts, answers,
                                    pathPhotos,subtitle, titleConvertsation, fullText,sex);

                        }


                        setProgress(position, Integer.parseInt(lessonId), Integer.parseInt(section),progressList);
                        setAfterDownload(position,subLessonOfs,lessonOf, sec);

                    }catch (Exception e){

                        e.printStackTrace();
                        Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();

                    }

                    imageView.setVisibility(View.INVISIBLE);
                }
            });
    }

    @SuppressLint("SdCardPath")
    public void downloadimage(String uRl, String filename, QPathPhoto qPathPhoto) {

        File path = new File("/sdcard/darsbazi/nomedia/");
        if(!path.exists()) {
            path.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(context.getResources().getString(R.string.app_name))
                .setDescription("در حال دانلود:" + filename + "")
                .setDestinationInExternalPublicDir("", "/darsbazi/nomedia/"+ filename + "");
        if (mgr != null) {
            mgr.enqueue(request);
        }

        qPathPhoto.setPath("/sdcard/darsbazi/nomedia/"+ filename + "");
        qPathPhoto.save();
    }

    @SuppressLint("SdCardPath")
    public void downloadimageType6(String uRl, String filename, QPathPhoto qPathPhoto, String type) {

        File path = new File("/sdcard/darsbazi/nomedia/");
        if(!path.exists()) {
            path.mkdirs();
        }

        String[] strings = uRl.split("&");
        String cap = "";

        Log.e("photo inside = ", strings[0]);
        if (strings.length == 2) {
            Log.e("photo p= ", strings[1]);
            cap = strings[1];
        }else {
            cap = "";
        }

//        String ss = uRl.replace("&","");

        DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(strings[0]);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(context.getResources().getString(R.string.app_name))
                .setDescription("در حال دانلود:" + filename + "")
                .setDestinationInExternalPublicDir("", "/darsbazi/nomedia/"+ filename + "");
        if (mgr != null) {
            mgr.enqueue(request);
        }

        qPathPhoto.setPath("/sdcard/darsbazi/nomedia/"+ filename + "&" + cap);
//        qPathPhoto.setPath("/sdcard/darsbazi/nomedia/"+ filename + "");
        qPathPhoto.save();
    }

    public void saveInDatabase(String head, String type, String score, String sections, String lesson_id,
                                    List<String> body, List<String> opts, List<String> answers, List<String> pathPhoto,
                               String subtitle, String titleConversation, List<String> fullText,String sex){

        try {

            Questions_Of questions_of = new Questions_Of();

//            Log.e("head = ", head);
//            Log.e("type = ", type);
//            Log.e("score = ", score);

            questions_of.setHead(head);
            questions_of.setType(type);
            questions_of.setScore(score);
            questions_of.setSections(sections);
            questions_of.setLesson_id(lesson_id);
            questions_of.setSubtitle(subtitle);
            questions_of.setSex(sex);
            questions_of.setTitleConvquestion(titleConversation);
            questions_of.save();

            for (int i = 0; i < body.size(); i++) {
                getBody(i, body, questions_of);
            }

            for (int i = 0; i < opts.size(); i++) {
                getOptions(i, opts, questions_of);
            }

            for (int i = 0; i < answers.size(); i++) {
                getAnswers(i, answers, questions_of);
            }

            for (int i = 0; i < pathPhoto.size(); i++) {
                getPhotoPath(i, pathPhoto, questions_of);
            }

            for (int i = 0; i < fullText.size(); i++) {
                getFullText(i, fullText, questions_of);
            }
        }catch (Exception e){
            e.printStackTrace();
//            Toast.makeText(context, "error = " + e, Toast.LENGTH_SHORT).show();
        }
        }

    private String imgName(String url){

        String[] strings = url.split("/");
        String name = strings[strings.length-1];
        name = name.replace(".jpg","").replace(".png","").replace("&","");
        Log.e("name = ", name);

        return name;
    }

    private void getOptions(int i, List<String> opts, Questions_Of questions_of){
        QOptions qOpts = new QOptions();
        qOpts.setOptions(opts.get(i));
        qOpts.setQuestions_id(questions_of.getId());
        Log.e("opts = ", opts.get(i));
        Log.e("opts q id = ", qOpts.getQuestions_id()+"");
        qOpts.save();

    }

    private void getBody(int i, List<String> body, Questions_Of questions_of){
        QBodies qBody = new QBodies();
        qBody.setBodies(body.get(i));
        qBody.setQuestions_id(questions_of.getId());
        Log.e("body = ", body.get(i));
        Log.e("body q id = ", qBody.getQuestions_id()+"");
        qBody.save();


    }

    private void getAnswers(int i, List<String> answers, Questions_Of questions_of){

        QAnswers qAnswers = new QAnswers();
        qAnswers.setAnswers(answers.get(i));
        qAnswers.setQuestions_id(questions_of.getId());
        Log.e("answers = ", answers.get(i));
        Log.e("answers q id = ", qAnswers.getQuestions_id()+"");
        qAnswers.save();

    }

    private void getFullText(int i, List<String> fullText, Questions_Of questions_of){

        QFullText qFullText = new QFullText();
        qFullText.setFulltext(fullText.get(i));
        qFullText.setQuestions_id(questions_of.getId());
        Log.e("answers = ", fullText.get(i));
        Log.e("answers q id = ", qFullText.getQuestions_id()+"");
        qFullText.save();

    }

    private void getPhotoPath(int i, List<String> pathPhoto, Questions_Of questions_of) {

        QPathPhoto qPathPhoto = new QPathPhoto();
        qPathPhoto.setQuestion_id(questions_of.getId());
        String path = pathPhoto.get(i);
        if (!path.startsWith("http")) {

            path = App.BASE_URL + path;

        }

        Log.e("pathPhotos = ", path);
        Log.e("type = ", questions_of.getType());

        Log.e("pathphoto q id = ", qPathPhoto.getQuestion_id() + "");

//        if (!path.contains("\\.")){
//            path = path + ".jpg";
//        }
//        Log.e("pathhhhh = ", path);

        if (questions_of.getType().equals("6")) {
//            path = path.replace("&","");
            Log.e("pathPhotos6666 = ", path);
            downloadimageType6(path, imgName(path), qPathPhoto, questions_of.getType());
        } else {
            downloadimage(path, imgName(path), qPathPhoto);
        }
    }

    private void setDownloadImage(int sec, ImageView secImg){

        if (sec == 0){

//            secImg.setImageResource(R.drawable.dl_sec);
            secImg.setVisibility(View.INVISIBLE);

        }else {

            secImg.setVisibility(View.INVISIBLE);

        }

    }

    private void animation(RecyclerView.ViewHolder holder, int position){

        // animation
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.bottom_from_up);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

    }

    /**  for visible or invisible icon of download */
    private void setAfterDownload(int position, List<SubLessonOf> subLessonOfs, LessonOf lessonModel, int sec){

        int setSec = 1;

        switch (sec) {

            case 1:
                Log.e("sec1", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec1(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec1", subLessonOf.getSec1() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec1.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

            case 2:
                Log.e("sec2", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec2(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec2", subLessonOf.getSec2() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec2.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

            case 3:
                Log.e("sec3", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec3(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec3", subLessonOf.getSec3() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec3.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

            case 4:
                Log.e("sec4", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec4(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec4", subLessonOf.getSec4() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec4.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

            case 5:
                Log.e("sec5", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec5(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec5", subLessonOf.getSec5() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec5.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

            case 6:
                Log.e("sec6", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec6(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec6", subLessonOf.getSec6() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec6.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

            case 7:
                Log.e("sec7", "position = " + position + "////" + lessonModelsList.get(position).getId());

                subLessonOfs.get(position).setSec7(setSec);
                subLessonOf.setLesson_id(lessonModel.getId());
                Log.e("sec7", subLessonOf.getSec7() + "");
                subLessonOfs.get(position).save();

                SQLite.update(SubLessonOf.class).set(SubLessonOf_Table.sec7.is(1))
                        .where(SubLessonOf_Table.lesson_id.eq(lessonModelsList.get(position).getId()))
                        .execute();

                break;

        }
    }

    /** set progress */
    private void setProgress(int positon, int lessonId, int section, Progress progress){

        ProgressSec progressSec = new ProgressSec();
        progressSec.setLesson_id(lessonId);
        switch (section){
            case 1:
                progressSec.setProgress1(Integer.parseInt(progress.getSection1()));
                break;
            case 2:
                progressSec.setProgress2(Integer.parseInt(progress.getSection2()));
                break;
            case 3:
                progressSec.setProgress3(Integer.parseInt(progress.getSection3()));
                break;
            case 4:
                progressSec.setProgress4(Integer.parseInt(progress.getSection4()));
                break;
            case 5:
                progressSec.setProgress5(Integer.parseInt(progress.getSection5()));
                break;
            case 6:
                progressSec.setProgress6(Integer.parseInt(progress.getSection6()));
                break;
            case 7:
                progressSec.setProgress7(Integer.parseInt(progress.getSection7()));
                break;
        }

        progressSec.save();

        List<ProgressSec> subSecList = SQLite.select().from(ProgressSec.class).queryList();
        ProgressSec subSec1 = subSecList.get(positon);

        Log.e("size sub sec list", subSecList.size()+"");
        Log.e("lesson_id", subSec1.getLesson_id()+"");
        Log.e("sub lesson id", subSec1.getSub_lesson_id()+"");
        Log.e("progress 1", subSec1.getProgress1()+"");
        Log.e("progress 2", subSec1.getProgress2()+"");
        Log.e("progress 3", subSec1.getProgress3()+"");
        Log.e("progress 4", subSec1.getProgress4()+"");
        Log.e("progress 5", subSec1.getProgress5()+"");
        Log.e("progress 6", subSec1.getProgress6()+"");
        Log.e("progress 7", subSec1.getProgress7()+"");

    }

    private void dlQuestion(int i, Data data, String section, String lessonId){
        ArrayList<Question> questionArrayList = (ArrayList<Question>) data.getQuestions();

        String type = questionArrayList.get(i).getTypequestion();
        String score = questionArrayList.get(i).getScoresquestion();
        String head = questionArrayList.get(i).getFullQuestionquestion().getHeadquestion();
        List<String> body = questionArrayList.get(i).getFullQuestionquestion().getBodyquestion();
        List<String> opts = (List<String>) questionArrayList.get(i).getOptquestion();
        List<String> answers = questionArrayList.get(i).getAnswersquestion();
        List<String> pathPhotos = questionArrayList.get(i).getPathPhotosquestion();
        List<String> fullText = questionArrayList.get(i).getFullText();
        String subtitle = questionArrayList.get(i).getSubtitlequestion();
        String sex = questionArrayList.get(i).getSex();
        String titleConvertsation = questionArrayList.get(i).getFullQuestionquestion().getTitleConvquestion();

        saveInDatabase(head, type, score, section, lessonId, body, opts, answers,
                pathPhotos,subtitle, titleConvertsation, fullText,sex);

    }

    private void totalGetQuestionList(final int position , final List<SubLessonOf> subLessonOfs, final LessonOf lessonOf,
                                      final String lessonId, final String sec, final ViewHolder holder,
                                      final ImageView imageView){

        new Request<Data>(context).getQuestionList(lessonId, sec, new CallBack<Data>() {
            @Override
            public void onRequestSuccessful(Data data) {

                ArrayList<Question> questionArrayList = (ArrayList<Question>) data.getQuestions();
                Progress progressList = data.getProgress();

                try {
                    for (int i = 0; i < questionArrayList.size(); i++) {
                        String section = questionArrayList.get(i).getSectionquestion();
                        switch (section){
                            case "1":
                                if (subLessonOfs.get(position).getSec1() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                            case "2":
                                if (subLessonOfs.get(position).getSec2() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                            case "3":
                                if (subLessonOfs.get(position).getSec3() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                            case "4":
                                if (subLessonOfs.get(position).getSec4() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                            case "5":
                                if (subLessonOfs.get(position).getSec5() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                            case "6":
                                if (subLessonOfs.get(position).getSec6() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                            case "7":
                                if (subLessonOfs.get(position).getSec1() != 0) {
                                    dlQuestion(i, data, section, lessonId);
                                }
                                break;
                        }

                    }

                    for (int i = 0; i <7; i++) {
                        setProgress(position, Integer.parseInt(lessonId), i+1,progressList);
                        setAfterDownload(position,subLessonOfs,lessonOf, i+1);
                    }

                    imageView.setVisibility(View.INVISIBLE);
                    holder.img_sec1.setVisibility(View.INVISIBLE);
                    holder.img_sec2.setVisibility(View.INVISIBLE);
                    holder.img_sec3.setVisibility(View.INVISIBLE);
                    holder.img_sec4.setVisibility(View.INVISIBLE);
                    holder.img_sec5.setVisibility(View.INVISIBLE);
                    holder.img_sec6.setVisibility(View.INVISIBLE);
                    holder.img_sec7.setVisibility(View.INVISIBLE);


                    SQLite.update(LessonOf.class).set(LessonOf_Table.total_dl.is("1"))
                            .where(LessonOf_Table.id.eq(lessonModelsList.get(position).getId()))
                            .execute();

                    notifyDataSetChanged();

                }catch (Exception e){

                    e.printStackTrace();
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    private void getSectionStep(LessonOf lessonModel, int position, ViewHolder holder){

        String step1 = "", step2 = "", step3 = "", step4 = "", step5 = "", step6 = "", step7 = "";
        Log.e("permissiion = ", lessonModel.getPermission());

            step1 = stepSubLessonOfList.get(position).getStep1();
            // permission = 1 -> active
            // permission = 0 -> diactive
            if (lessonModel.getPermission().equals("0")){
                holder.icon_sec1.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec1.setImageResource(General.iconSeda);
            }

            step2 = stepSubLessonOfList.get(position).getStep2();
            if (!(step1.equals("-1"))){
                holder.icon_sec2.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec2.setImageResource(General.iconSeda);
            }

            step3 = stepSubLessonOfList.get(position).getStep3();
            if (!(step2.equals("-1"))){
                holder.icon_sec3.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec3.setImageResource(General.iconSeda);
            }

            step4 = stepSubLessonOfList.get(position).getStep4();
            if (!(step3.equals("-1"))){
                holder.icon_sec4.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec4.setImageResource(General.iconSeda);
            }

            step5 = stepSubLessonOfList.get(position).getStep5();
            if (!(step4.equals("-1"))){
                holder.icon_sec5.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec5.setImageResource(General.iconSeda);
            }

            step6 = stepSubLessonOfList.get(position).getStep6();
            if (!(step5.equals("-1"))){
                holder.icon_sec6.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec6.setImageResource(General.iconSeda);
            }

            step7 = stepSubLessonOfList.get(position).getStep7();
            if (!(step6.equals("-1"))){
                holder.icon_sec7.setImageResource(R.drawable.ic_lock);
            }else {
                holder.icon_sec7.setImageResource(General.iconSeda);
            }

            Log.e("stepsssss = ", step1 +"//" + step2 +"//" + step3 +"//"+ step4 +"//"+ step5 +"//"+ step6 +"//"+ step7 +"//");

    }

}

