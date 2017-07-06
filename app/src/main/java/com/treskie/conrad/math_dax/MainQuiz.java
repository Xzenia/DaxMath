package com.treskie.conrad.math_dax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.content.Intent;

public class MainQuiz extends AppCompatActivity {

    private ProgressBar timerBar;
    private CountDownTimer myCountDownTimer;
    private EditText userEditText;
    private TextView scoreLabel;
    private int score;
    private int answer;
    private int maxNumber = 10;
    private int minNumber = 1;
    private TextView question;

    private static final String TAG = "MainQuiz";

    private QuizHelper quizHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        timerBar = (ProgressBar)findViewById(R.id.timerBar);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        userEditText = (EditText) findViewById(R.id.input);
        question = (TextView) findViewById(R.id.question);
        quizHelper = new QuizHelper();
        setupGame();
    }

    private void setupGame(){
        quizHelper.setProgressBar(timerBar,100);
        initializeTimer(10000);
        generateProblem();
    }

    public void generateProblem(){
        int a = quizHelper.generateRandomNumber(minNumber,maxNumber);
        int b = quizHelper.generateRandomNumber(minNumber,maxNumber);
        int operation = quizHelper.generateRandomNumber(1,3);
        switch (operation){
            case 1:
                question.setText(a+" + "+b+"? ");
                answer = a + b;
                break;
            case 2:
                //TODO: Find a way to prevent generating another number for b
                b = quizHelper.generateRandomNumber(minNumber,a);
                question.setText(a+" - "+b+"? ");
                answer = a - b;
                break;
            case 3:
                question.setText(a+" x "+b+"? ");
                answer = a * b;
                break;
            default:
                Log.d(TAG,"Something clearly went wrong with the switch case!");
                break;
        }
    }

    public void initializeTimer(int maxTime){
        myCountDownTimer = new CountDownTimer(maxTime,1000){
            public void onTick(long millisUntilFinished) {
                int progress = (int) (millisUntilFinished/100);
                quizHelper.setProgressBar(timerBar,progress);
            }
            public void onFinish() {
                quizHelper.setProgressBar(timerBar,0);
                Toast.makeText(getApplicationContext(),"Time's up!",Toast.LENGTH_SHORT).show();
                finishActivity(score);
            }
        }.start();
    }

    public void submitAnswer (View view){
        String userInputString = userEditText.getText().toString();
        if (TextUtils.isEmpty(userInputString)){
            userEditText.setError("You did not enter anything!");
            return;
        } else {
            int userInputInt = Integer.valueOf(userInputString);
            if (userInputInt == answer) {
                Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
                score = score + 1;
                quizHelper.setScore(scoreLabel,score);
                regenerateMethod();
            } else {
                userEditText.getText().clear();
                myCountDownTimer.cancel();
                finishActivity(score);
            }
        }
    }

    public void regenerateMethod(){
        myCountDownTimer.cancel();
        generateProblem();
        myCountDownTimer.start();
        userEditText.getText().clear();
    }

    public void finishActivity(int score){
        Intent finishScreen = new Intent(this, FinishActivity.class);
        String scoreString = String.valueOf(score);
        finishScreen.putExtra("scoreData",scoreString);
        startActivity(finishScreen);
        finish();
    }
}

