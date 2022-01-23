package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){
            public void onTick(long l){
                Toast.makeText(MainActivity.this,String.valueOf(l/1000),Toast.LENGTH_SHORT).show();
            }
            public void onFinish(){
                Toast.makeText(MainActivity.this,"Countdown Done!",Toast.LENGTH_SHORT).show();
            }
        }.start();









    }
}