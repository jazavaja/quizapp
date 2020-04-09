package com.javad.quizapplang;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.javad.quizapplang.CustomView.MyButton;
import com.javad.quizapplang.model.Level;
import com.javad.quizapplang.model.Profile;
import com.javad.quizapplang.model.data.Data;
import com.javad.quizapplang.model.dbFlow.LessonOf;
import com.javad.quizapplang.model.dbFlow.LevelOf;
import com.javad.quizapplang.model.dbFlow.LevelOf_Table;
import com.javad.quizapplang.model.dbFlow.SubLessonOf;
import com.javad.quizapplang.service.CallBack;
import com.javad.quizapplang.service.Request;
import com.javad.quizapplang.utils.General;
import com.javad.quizapplang.utils.PrefManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ProfileActivity extends AppCompatActivity {

//    Spinner spinner;
    List<String> listSp = new ArrayList<>();
    EditText name, education, username, mobile, email;
    TextView score, level;
    CircleImageView pic_profile;
    int PICK_IMAGE_REQUEST = 1;
    String strProfile;
    PrefManager prefManager;
    SwipeRefreshLayout refreshLayout;
    List<LevelOf> levelOfs;

    MyButton agianTayinSath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        education = findViewById(R.id.education);
        score = findViewById(R.id.score);
        agianTayinSath=findViewById(R.id.agianTayinSath);
        level = findViewById(R.id.level);
        username = findViewById(R.id.username);
        mobile = findViewById(R.id.mobile);
        refreshLayout=findViewById(R.id.ref);
        email = findViewById(R.id.email);
        pic_profile = findViewById(R.id.pic_profile);
        prefManager = new PrefManager(this);
        requestGetProfile();

        agianTayinSath.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (prefManager.getTdadTayinSathAnjmShd()<=3)
                {
                    Intent intent=new Intent(ProfileActivity.this,PlacementActivity.class);
                    intent.putExtra("agian","yes");
                    startActivity(new Intent(intent));
                    finish();
                }
            }
        });

        if (prefManager.getFirstSeeProfile().equals("No")){
            setTapTarget();
            prefManager.setFirstSeeProfile();
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestGetProfile();
            }
        });


        pic_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);

                /**
                 * Always show the chooser (if there are multiple option available)
                 */

                startActivityForResult(Intent.createChooser(intent, "choose a pic"), PICK_IMAGE_REQUEST);

            }
        });


        try {

            pic_profile.setImageBitmap(stringToImage(prefManager.getPhotoprofile()));
            Log.e("pic", prefManager.getPhotoprofile());

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (prefManager.getFirsttimeprofle().equals("0")){

            requestGetProfile();

        }else {

            name.setText(prefManager.getNameprofile());
            education.setText(prefManager.getEducationprofile());
            username.setText(prefManager.getUsernameprofile());
            mobile.setText(prefManager.getMobileprofile());

        }

        List<LevelOf> levelOf = SQLite.select().from(LevelOf.class).queryList();

        for (int i = 0; i < levelOf.size(); i++) {
            if (levelOf.get(i).getNum_level().equals(prefManager.getNameapp())){
                score.setText("امتیاز کل: " + levelOf.get(i).getTotal_score());
            }else {
                Log.e("score id = ", levelOf.get(i).getId()+"");
                Log.e("score active = ", levelOf.get(i).getActiveLevel()+"");
                Log.e("score active = ", levelOf.get(i).getTotal_score()+"");
                Log.e("score permission = ", levelOf.get(i).getPermission()+"");
            }
        }

        level.setText("سطح شما: " + prefManager.getNameapp());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK
                && data != null && data.getData() != null){

            Uri uri = data.getData();

            try {
                final Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                Log.e("pic", String.valueOf(bitmap));

                pic_profile.setImageBitmap(bitmap);

                pic_profile.setScaleType(ImageView.ScaleType.CENTER_CROP);
                strProfile = imageToString(bitmap);


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileOutputStream out = null;

                        try {
                            out = new FileOutputStream(getFilesDir()+ File.separator+"p.png");
                            /**
                             * png is a lossless format, the compression factor(100) is ignore
                             */
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }finally {

                            try {

                                if (out != null){
                                    out.close();

                                    Log.e("asdasd", "end");

                                }
                            }catch (Exception e){
                                e.printStackTrace();

                                Log.e("asdasd", "end but cant close");
                            }
                        }
                    }
                });
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public TapTarget tapTarget(int view, String s){
        return TapTarget.forView(findViewById(view),s)
                .outerCircleColor(R.color.color_blue)
                .dimColor(android.R.color.background_light)
                .textColor(R.color.colorBlack);
    }

    public void setTapTarget(){
        new TapTargetSequence(this)
                .targets(
                        tapTarget(R.id.pic_profile,"عکس پروفایل خود را میتوانید اپلود کنید"),
                        tapTarget(R.id.name,"نام خود را در اپ میتوانید ویرایش کنید"),
                        tapTarget(R.id.education,"تحصیلات و رشته خود را میتوانید ویرایش کنید"),
                        tapTarget(R.id.mobile,"شماره موبایل خود را نیز میتوانید ویرایش کنید"),
                        tapTarget(R.id.email,"ایمیل خود را ویرایش کنید")
                )

                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        // Yay
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();
    }

    private void requestGetProfile(){
        levelOfs = SQLite.select().from(LevelOf.class).queryList();

            new Request<Data>(this).getInfoProfile(new CallBack<Data>() {
                @Override
                public void onRequestSuccessful(Data data) {
                    prefManager.setFirsttimeprofle("1");
                    Profile profile = data.getProfile();

                    setItems(profile.getFullname(), profile.getEducation(), profile.getUsername(), profile.getPhoneNumber()
                            , profile.getPathPhoto());
                    refreshLayout.setRefreshing(false);
                    Log.e("path photo = ", profile.getPathPhoto());

                    if (levelOfs.size() == 0) {

                        new Request<Data>(ProfileActivity.this).getLevelList(new CallBack<Data>() {
                            @Override
                            public void onRequestSuccessful(Data data) {

                                List<Level> list = data.getLevels();

                                for (int i = 0; i < list.size(); i++) {
                                    Level level = list.get(i);
                                    listSp.add(level.getName());

                                    saveList(level.getId(), level.getName());

                                    setLevelList(level.getName());

                                }

                            }
                        });

                    }
                }
            });
    }

    private void setLevelList(String name){
        LevelOf levelOf = new LevelOf();
        levelOf.setNum_level(name);
//        levelOf.setTotal_score(totalScore);
//        levelOf.setPermission(permission);
        levelOf.save();
    }

    private void saveList(int id, String name){
        LevelOf levelOf = new LevelOf();
        levelOf.setId(id);
        levelOf.setNum_level(name);
        levelOf.setPermission(-1);
        levelOf.save();
    }

    private void requestSetProfile(){

        new Request<Data>(this).editInfoProfile(convertToString(name),
                convertToString(username), convertToString(education),
                strProfile, new CallBack<Data>() {
                    @Override
                    public void onRequestSuccessful(Data data) {

                        Toasty.success(ProfileActivity.this,"ویرایش با موفقیت انجام شد").show();
                        finish();
                        prefManager.setFirsttimeprofle("0");
                        prefManager.setPhotoprofile(strProfile);

                    }
                });
    }

    private String convertToString(EditText editText){
        return editText.getText().toString();
    }

    public void btn_save(View view) {
        requestSetProfile();
    }

    private void setItems(String strname, String streducation, String strusername, String strmobile, String strPhoto){

        prefManager.setNameprofile(strname);
        prefManager.setEducationprofile(streducation);
        prefManager.setUsernameprofile(strusername);
        prefManager.setMobileprofile(strmobile);
        downloadimage(strPhoto,"p.png");
        name.setText(strname);
        education.setText(streducation);
        username.setText(strusername);
        mobile.setText(strmobile);

    }

    public void writeToFile(String data) {
        // Get the directory for the user's public pictures directory.
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DCIM + "/Camera_Demo/"
                        );

        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "config.txt");

        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String imageToString(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int maxWidth = 200;
        int maxHeight = 200;

        Log.v("Pictures", "Width and height are " + width + "--" + height);

        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int)(height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int)(width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }
        Log.e("width", String.valueOf(width));
        Log.e("height", String.valueOf(height));

        Bitmap resize = Bitmap.createScaledBitmap(bitmap, width, height, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        resize.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
        byte[] image = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(image,Base64.DEFAULT);
    }

    private Bitmap stringToImage(String base64){
        byte[] decod = Base64.decode(base64,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decod,0,decod.length);

        return bitmap;
    }

    public void downloadimage(String uRl,String filename) {

        File path = new File(Environment.getExternalStorageDirectory() + "/darsbazi/nomedia/");
        if(!path.exists()) {
            path.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(App.BASE_URL + uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(getResources().getString(R.string.app_name))
                .setDescription("در حال دانلود:" + filename + "")
                .setDestinationInExternalPublicDir("", "/darsbazi/nomedia/"+ "p.png");
        if (mgr != null) {
            mgr.enqueue(request);
        }

        Toast.makeText(this, "download image", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    Picasso.with(ProfileActivity.this).load(new File(Environment.getExternalStorageDirectory() + "/darsbazi/nomedia/"+ "p.png"))
                            .placeholder(R.drawable.ic_person)
                            .into(pic_profile);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },1000);

    }
}
