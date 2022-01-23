package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.lang.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public void isCorrect(View view){
        int num = Integer.parseInt(view.getTag().toString());
        if(correctOption==num) {
            Toast.makeText(this, "Correct Ans", Toast.LENGTH_SHORT).show();
            nextQs();
        }
        else
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
    }

    List<String> celebNames = new ArrayList<>();
    List<String> celebUrls =new ArrayList<>();
    TextView views[] = new TextView[4];
    String wrongOp[] = new String[3];
    int correctOption;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views[0] = findViewById(R.id.textView1);
        views[1] = findViewById(R.id.textView2);
        views[2] = findViewById(R.id.textView3);
        views[3] = findViewById(R.id.textView4);
        imageView = findViewById(R.id.imageView);
        BackGroundTask btsk =  new BackGroundTask();
        String celebrity = "";
        try {
            celebrity = btsk.execute("https://www.forbesindia.com/lists/2019-celebrity-100/1819/1").get();
            if(celebrity==null) {
                Toast.makeText(getApplicationContext(), "Null Information", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        //<div class=\"celeb-grid \">(.*?)<h3>(.*?)</h3>(.*?)<img src=\"(.*?)\""

        String key = "<img src=\"(.*?)\"(.*?)alt=\"(.*?)\"";
        Pattern p = Pattern.compile(key);
        Matcher m = p.matcher(celebrity);
        while(m.find()){
            celebNames.add(m.group(3));
            celebUrls.add(m.group(1));
        }
        nextQs();
    }
    void nextQs(){
        Random rn = new Random();
        int celebId = rn.nextInt(celebNames.size());
        wrongAns(celebId);
        correctOption = rn.nextInt(4);
        views[correctOption].setText(celebNames.get(celebId));
        setImage(celebUrls.get(celebId));
        int j = 0;
        for(int i=0 ; i<4 ; i++){
            if(i!=correctOption){
                views[i].setText(wrongOp[j++]);
            }
        }
    }
    public void setImage(String url){
        ImageTask it = new ImageTask();
        try{
            Bitmap image = it.execute(url).get();
            imageView.setImageBitmap(image);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    void wrongAns(int correctCelebId){
        Random rn = new Random();
        int first = rn.nextInt(celebNames.size());
        while(first==correctCelebId) first = rn.nextInt(celebNames.size());
        wrongOp[0] = celebNames.get(first);
        int second = rn.nextInt(celebNames.size());
        while(second==correctCelebId || second==first) second = rn.nextInt(celebNames.size());
        wrongOp[1] = celebNames.get(second);
        int third = rn.nextInt(celebNames.size());
        while(third==correctCelebId || third==first || third==second) third = rn.nextInt(celebNames.size());
        wrongOp[2] = celebNames.get(third);
    }

    class BackGroundTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            URLConnection urlConnection;
            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                Scanner s = new Scanner(in).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
                //return celebrity;
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;

        }
    }
    class ImageTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            URLConnection urlConnection;
            Bitmap myImage = null;
            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                myImage = BitmapFactory.decodeStream(in);

            } catch(Exception e){
                e.printStackTrace();
            }
            return myImage;
        }
    }
}