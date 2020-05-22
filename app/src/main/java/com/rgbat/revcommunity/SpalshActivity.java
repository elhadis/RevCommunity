package com.rgbat.revcommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpalshActivity extends AppCompatActivity {
    private TextView appName;
    private ImageView imageView;
    private Button btnClick;
    public static  List<String> catList =new ArrayList<>();
//    public static List<CategoryModel> catList = new ArrayList<>();
//    public static int selected_cat_index = 0;
//    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);
        appName = findViewById(R.id.appName);
        imageView = findViewById(R.id.imageView);
        btnClick = findViewById(R.id.click);
        //firestore = FirebaseFirestore.getInstance();


        /////  font to textView
        // Typeface typeface = ResourcesCompat.getFont(this,R.we need the font file in resource)
        // appName.setTypeface(typeface);
        ((AnimationDrawable)imageView.getBackground()).start();

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },1000);

            }
        });
        ///// animation
//        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_anim);
//        appName.setAnimation(anim);

//        new Thread() {
//            public void run() {
////DATA FROM SERVER
                //loadData();
//                try {
//                    sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//
//                }
//                Intent intent = new Intent(getBaseContext(),MainActivity.class);
//                startActivity(intent);
//
//            }
//
//
//        }.start();
    }
}
