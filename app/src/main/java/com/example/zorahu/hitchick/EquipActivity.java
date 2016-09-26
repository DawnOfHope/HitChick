package com.example.zorahu.hitchick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class EquipActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageButton btnArms1;

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

//        btnArms1 = (ImageButton) findViewById(R.id.arms1);
//        btnArms1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                equipWithArms1();
//            }
//        });

    }
    //回首頁
    private void goWelcome(){
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    //裝備武器1
//    private void equipWithArms1(){
//        Intent intent = new Intent();
//        intent.setClass(EquipActivity.this,MainActivity.class);
////        Bundle bundle = new Bundle();
////        bundle.putString("btnArms1","ok");
////        intent.putExtras(bundle);
//        intent.putExtra("btnArms1","ok");
////        startActivity(intent);
//
//    }

}
