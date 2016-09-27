package com.example.zorahu.hitchick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class EquipActivity extends AppCompatActivity {
    private ImageButton btnBack,btnArms1,btnArms2;
    private ImageView equipStar1,equipStar2;
    private int whetherStar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_equipe);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnBack.setBackgroundResource(R.drawable.k2a);
                goWelcome();
            }
        });

        whetherStar = 0;
        equipStar1 = (ImageView) findViewById(R.id.equipStar1);
        equipStar2 = (ImageView) findViewById(R.id.equipStar2);

        btnArms1 = (ImageButton) findViewById(R.id.arms1);
        btnArms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whetherStar != 2){
                    equipStar1.setVisibility(View.VISIBLE);
                    whetherStar = 1;
                }
//                equipWeapon();

            }
        });

        btnArms2 = (ImageButton) findViewById(R.id.arms2);
        btnArms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whetherStar != 1){
                    equipStar2.setVisibility(View.VISIBLE);
                    whetherStar = 2;
                }

            }
        });


    }
    //回首頁
    private void goWelcome(){
        Intent intent = new Intent(this,WelcomeActivity.class);
        intent.putExtra("whetherEquip",whetherStar);
        startActivity(intent);
        finish();
    }

//    //裝備武器判斷
//    private void equipWeapon(){
//        Intent intent = new Intent(this,MainActivity.class);
//        intent.putExtra("whetherEquip",whetherStar);
//        startActivity(intent);
//
//    }












}
