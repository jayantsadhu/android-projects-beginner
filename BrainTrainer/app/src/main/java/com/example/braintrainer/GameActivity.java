package com.example.braintrainer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class GameActivity extends AppCompatActivity {
    char ch[] = {'+','-','*','/'};
    Random rand = new Random();
    CountDownTimer countDownTimer;
    TextView timerView;
    TextView mathTextView;
    TextView scoreView;
    TextView option1View;
    TextView option2View;
    TextView option3View;
    TextView option4View;
    TextView solnStatus;
    Button startButton;
    boolean isTimeOver;
    int totalQs;
    int score;
    int ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mathTextView = findViewById(R.id.mathView);
        option1View = findViewById(R.id.textViewOp1);
        option2View = findViewById(R.id.textViewOp2);
        option3View = findViewById(R.id.textViewOp3);
        option4View = findViewById(R.id.textViewOp4);
        scoreView = findViewById(R.id.scoreTextView);
        timerView = findViewById(R.id.timerTextView);
        solnStatus = findViewById(R.id.solnStatusTextView);
        startButton = findViewById(R.id.startButtonView);
        //timerView.setBackgroundColor(Color.parseColor());
        isTimeOver = true;
        scoreView.setText("0/0");
        solnStatus.setText("Status");
        timerView.setText("30s");
    }
    public void startTimer(View view){
        scoreView.setText("0/0");
        solnStatus.setText("Status");
        timerView.setText("30s");
        if(!isTimeOver){
            countDownTimer.cancel();
            isTimeOver = true;
            mathTextView.setText("Math");
            startButton.setText("Start");
            return;
        }
        isTimeOver = false;
        totalQs = 0;
        score = 0;
        createQuestion();
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startButton.setText("Stop");
                long secs = millisUntilFinished/1000;
                timerView.setText((secs+"s"));
            }

            @Override
            public void onFinish() {
                isTimeOver = true;
                solnStatus.setText(("Final score is "+scoreView.getText().toString()));
                startButton.setText("Start");
            }
        }.start();
    }
    public void clickedOption(View view){
        if(isTimeOver) return;
        TextView clickedView = (TextView) view;
        int option = Integer.parseInt(clickedView.getText().toString());
        if(option==ans) {
            score++;
            solnStatus.setText("Correct!");
        }
        else
            solnStatus.setText("Wrong :(");

        scoreView.setText((String.valueOf(score)+'/'+String.valueOf(totalQs)));
        createQuestion();
    }

    public void createQuestion(){
        totalQs++;
        int num1 = rand.nextInt(100);
        int num2 = rand.nextInt(100);
        int opIdx = rand.nextInt(4);
        String mathText = String.valueOf(num1)+ch[opIdx]+String.valueOf(num2);
        mathTextView.setText(mathText);
        ans = answer(num1, num2, opIdx);
        updateOptions(opIdx, num1, num2);
    }
    public void updateOptions(int opIdx, int num1, int num2){
        List<Integer> opList = new ArrayList<>();
        opList.add(ans);
        int min = (ans/10)*10;
        int max = (ans/10+1)*10;
        int op2 = min + rand.nextInt(max-min+1);
        opList.add(op2);

        List<Integer> opMaking = new ArrayList<>();
        for(int i=0 ; i<4 ; i++) opMaking.add(i);

        opMaking.remove(new Integer(opIdx));
        int op3Idx = rand.nextInt(2);
        int op3 = answer(num1, num2, opMaking.get(op3Idx));
        opList.add(op3);
        opMaking.remove(new Integer(op3Idx));
        int op4Idx = rand.nextInt(1);
        int op4 = answer(num1, num2, opMaking.get(op4Idx));
        opList.add(op4);

        Collections.shuffle(opList);
        option1View.setText(String.valueOf(opList.get(0)));
        option2View.setText(String.valueOf(opList.get(1)));
        option3View.setText(String.valueOf(opList.get(2)));
        option4View.setText(String.valueOf(opList.get(3)));
    }
    public int answer(int num1, int num2, int opIdx){
        switch(opIdx){
            case 0:
                return (num1+num2);
            case 1:
                return (num1-num2);
            case 2:
                return (num1*num2);
            case 3:
                return (num1/num2);
        }
        return 0;
    }
}
