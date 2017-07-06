package com.treskie.conrad.math_dax;

import android.widget.ProgressBar;
import android.widget.TextView;

public class QuizHelper {

    public void setProgressBar(ProgressBar progressBar,int progress){
        progressBar.setProgress(progress);
    }

    public void setScore(TextView textView, int score){
        textView.setText(String.valueOf(score));
    }

    public int generateRandomNumber(int min, int max){
        int number = min + (int) (Math.random() * max);
        return number;
    }

}
