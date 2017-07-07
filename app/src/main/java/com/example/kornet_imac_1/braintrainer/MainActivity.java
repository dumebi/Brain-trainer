package com.example.kornet_imac_1.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    TextView timerTextView;
    TextView sumTextview;
    TextView pointsTextview;
    RelativeLayout gameLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){
        numberOfQuestions = 0;
        score = 0;
        timerTextView.setText("30s");
        pointsTextview.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();


        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s") ;
                resultTextView.setText("Your score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuestion(){
        Random rand  = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        int answer = a+b;
        answers.clear();
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;

        for(int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(answer);
            }
            else{
                incorrectAnswer = (rand.nextInt(40));
                while(incorrectAnswer == answer){
                    incorrectAnswer = (rand.nextInt(40));
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        sumTextview.setText(Integer.toString(a)+" + "+Integer.toString(b));
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            resultTextView.setText("Correct");
            score++;
        }
        else{
            resultTextView.setText("Wrong");
        }
        generateQuestion();
        numberOfQuestions++;
        pointsTextview.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
    }
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.startButton);
        sumTextview = (TextView)findViewById(R.id.questionView);
        pointsTextview = (TextView)findViewById(R.id.scoreView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        gameLayout = (RelativeLayout)findViewById(R.id.gameLayout);
        button0 = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);


        playAgain(playAgainButton);
    }
}
