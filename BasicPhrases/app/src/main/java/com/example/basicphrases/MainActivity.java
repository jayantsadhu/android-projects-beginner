package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;


    public void playPhrase(View view)
    {

        Button buttonView = (Button) view;
        int resId = getResources().getIdentifier(buttonView.getTag().toString(),"raw",getPackageName());
        mediaPlayer = MediaPlayer.create(this,resId);
        mediaPlayer.start();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}