package com.example.zorahu.hitchick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnbanner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnbanner1 = (Button) findViewById(R.id.btnBanner1);
        btnbanner1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gotoMainPlay();
            }
        });
    }

    private void gotoMainPlay(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
