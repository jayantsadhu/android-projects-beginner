package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekbar;
    Button btn;
    CountDownTimer countDownTimer;
    MediaPlayer timerMusic;
    MediaPlayer timerCompleteMusic;
    boolean isTimerActive = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = findViewById(R.id.timerSeekBar);
        textView = findViewById(R.id.timerTextView);
        btn = findViewById(R.id.goButton);
        seekbar.setMax(900);
        seekbar.setProgress(30);

        //timerMusic = MediaPlayer.create(MainActivity.this,R.raw.ticktock);
        timerCompleteMusic = MediaPlayer.create(getApplicationContext(),R.raw.smokealarm);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void buttonClicked(View view){

        if(isTimerActive){
            resetTimer();
            return;
        }
        seekbar.setEnabled(false);
        isTimerActive = true;
        btn.setText("STOP");
        timerCompleteMusic.stop();
        timerMusic = MediaPlayer.create(MainActivity.this,R.raw.ticktock);
        timerCompleteMusic = MediaPlayer.create(getApplicationContext(),R.raw.smokealarm);
        countDownTimer = new CountDownTimer(seekbar.getProgress()*1000, 1000) {

            @Override
            public void onTick(long l) {
                int i = (int)(l/1000);
                updateTimer(i);
                timerMusic.start();
            }
            @Override
            public void onFinish() {
                resetTimer();
                timerCompleteMusic.start();
                Toast.makeText(MainActivity.this, "Timer Finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
    public void resetTimer(){
        countDownTimer.cancel();
        timerMusic.stop();
        seekbar.setEnabled(true);
        seekbar.setProgress(30);
        textView.setText("00:30");
        btn.setText("GO!");
        isTimerActive = false;
    }
    public void updateTimer(int i){
        int minutes = i/60;
        int secs = i - minutes*60;
        String strMinute = String.valueOf(minutes);
        if(minutes/10==0)
            strMinute = "0" + strMinute;
        String strSec = String.valueOf(secs);
        if(secs/10==0)
            strSec = "0" + strSec;

        textView.setText(strMinute+":"+strSec);
    }
}