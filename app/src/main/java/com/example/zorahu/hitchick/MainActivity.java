package com.example.zorahu.hitchick;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView[] imageViewList;
    private TextView scoreBar,timerBar,scoreTotal;
    private int[] manychick;
    private boolean play;
    private Handler handler;
    private int score,touchId;
    private ChickSprite[] chickSprites;
    private SoundPool soundPool;
    private ImageButton btnBack;
    private ImageView winPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        scoreBar = (TextView) findViewById(R.id.scoreBar);
        scoreTotal = (TextView)findViewById(R.id.scoreTotal);
        //建立hole陣列
        imageViewList = new ImageView[]{
                (ImageView) findViewById(R.id.hole),
                (ImageView) findViewById(R.id.hole2),
                (ImageView) findViewById(R.id.hole3),
                (ImageView) findViewById(R.id.hole4),
                (ImageView) findViewById(R.id.hole5),
                (ImageView) findViewById(R.id.hole6),
                (ImageView) findViewById(R.id.hole7),
                (ImageView) findViewById(R.id.hole8),
                (ImageView) findViewById(R.id.hole9),
        };

        //建立小雞陣列
        manychick = new int[]{
            R.drawable.hole,R.drawable.j2,R.drawable.j3,R.drawable.j4,
                R.drawable.j5,R.drawable.j6,R.drawable.j7,R.drawable.j8
        };

        handler = new Handler();
        play = true;
        chickSprites = new ChickSprite[9];
        for (int i= 0; i < chickSprites.length ; i++){
            chickSprites[i] = new ChickSprite(imageViewList[i]);

            imageViewList[i].setOnTouchListener(new ChickOnTouchLister(chickSprites[i]));
            Thread t = new Thread(chickSprites[i]);
            t.start();

        }
        //戰報
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        winPage = (ImageView)findViewById(R.id.winPage);

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnBack.setBackgroundResource(R.drawable.k2a);
                goWelcome();
            }
        });

        //時間倒數
        timerBar =(TextView) findViewById(R.id.timeBar);
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerBar.setText(" 剩餘時間：" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                play = false;
                timerBar.setText("遊戲結束");
                btnBack.setVisibility(View.VISIBLE);
                winPage.setVisibility(View.VISIBLE);
                scoreTotal.setVisibility(View.VISIBLE);
                scoreTotal.setText("總得分:"+String.valueOf(score));
            }
        }.start();

        //音效
        buildSoundPool();



    }

    //接收 EquipActivity 的資料區
//    Bundle bundle = this.getIntent().getExtras();
//    String test1 = bundle.getString("btnArms1");

//    Intent intent = this.getIntent();
//    String test1 = intent.getStringExtra("btnArms1");



    private class ChickSprite implements Runnable{
        ImageView imageView;
        int idx;
        boolean hit;
        boolean isFirst = true;

        ChickSprite(ImageView imageView){
            this.imageView = imageView;
        }
        @Override
        public void run() {
            if (isFirst) {
                isFirst = false;
                int n = (int)(Math.random()*1000)%10+1;
                System.out.println("n = " + n);
                handler.postDelayed(this, n * 500);
                return;
            }


            draw();

        }
        private void draw(){
            if (!play){
                return;
            }
            //點擊發生
            if (hit){
                imageView.setImageResource(R.drawable.dg);//變更成傷害圖片
                hit = false;//還原點擊屬性
                idx = 0;//調整陣列維度
                handler.postDelayed(this,100);//每次遞迴延遲秒數

            }else {

                idx = idx % manychick.length;
                imageView.setImageResource(manychick[idx]);
                int n = (int)(Math.random()*1000)%5+1;
                handler.postDelayed(this,(n*100));//遞迴呼叫秒數
                idx = ++idx % manychick.length;
            }
        }

    }

    private class ChickOnTouchLister implements View.OnTouchListener{
        ChickSprite bobogi;
        ChickOnTouchLister(ChickSprite bobogi){
            this.bobogi = bobogi;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (play && event.getAction() == MotionEvent.ACTION_DOWN){
                if (manychick[bobogi.idx] == R.drawable.j4 ||
                        manychick[bobogi.idx] == R.drawable.j5 ||
                        manychick[bobogi.idx] == R.drawable.j6 ||
                        manychick[bobogi.idx] == R.drawable.j7){
                    bobogi.hit = true;
                    soundPool.play(touchId, 1, 1, 0, 0, 1);
//                    if ( test1 == "ok"){
//                        scoreBar.setText(String.valueOf(10+score));
//                    }
                    scoreBar.setText(String.valueOf(++score));


                }else {
                    if (score != 0){
                        scoreBar.setText(String.valueOf(--score));

                    }
                }
            }

            return false;
        }
    }

    //建立音效
    private void buildSoundPool(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(attr)
                    .build();

        }else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        }

        touchId = soundPool.load(this,R.raw.middle_punch1,1);
    }





    //遊戲結束返回首頁
    private void goWelcome(){
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }







}
