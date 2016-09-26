package com.example.zorahu.hitchick;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class WelcomeActivity extends AppCompatActivity {
    private ImageButton btnBanner1,btnBanner2,btnEquip,btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        btnBanner1 = (ImageButton) findViewById(R.id.btnBanner1);
        btnBanner1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnBanner1.setBackgroundResource(R.drawable.banner1act);

                gotoMainPlay();
            }
        });

        btnBanner2 = (ImageButton) findViewById(R.id.btnBanner2);
        btnBanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBanner2.setBackgroundResource(R.drawable.banner2act);
                gotoMainPlay();
            }
        });

        btnEquip = (ImageButton) findViewById(R.id.btnEquip);
        btnEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEquip.setBackgroundResource(R.drawable.btnact);
                gotoEquip();
            }
        });

        btnSet = (ImageButton) findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSet.setBackgroundResource(R.drawable.btn2act);
                gotoEquip();
            }
        });

    }

    private void gotoMainPlay(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoEquip(){
        Intent intent = new Intent(this,EquipActivity.class);
        startActivity(intent);
        //finish();

    }
}
