package com.example.zorahu.hitchick;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewList;
    private TextView scoreBar;
    private int[] manychick;
    private boolean play;
    private Handler handler;
    private int score;
    private ChickSprite[] chickSprites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        imageViewList = (ImageView) findViewById(R.id.hole);

    }

    private class ChickSprite implements Runnable{
        @Override
        public void run() {

        }

    }

}
