package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertINR(View view) {

        EditText editText = (EditText) findViewById(R.id.USDTValueEditText);
        String text = editText.getText().toString();
        if(editText==null)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        else
        {
            double usdt = Double.parseDouble(text);
            //double inrValue = (double)((int)(76.3*usdt*100))/100;
            double inrValue = (double)Math.round(76.3*usdt*100)/100;
            Toast.makeText(this, Double.toString(inrValue)+" Rupees!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}