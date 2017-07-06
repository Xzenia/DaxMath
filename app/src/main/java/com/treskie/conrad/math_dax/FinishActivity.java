package com.treskie.conrad.math_dax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class FinishActivity extends AppCompatActivity {

    TextView score;
    TextView greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        score = (TextView) findViewById(R.id.scoreTextView);
        greeting = (TextView) findViewById(R.id.greeting);
        getGreeting();
        getScore();
    }

    private void getScore(){
        String scoreString = getIntent().getExtras().getString("scoreData");
        score.setText(scoreString);
    }
    private void getGreeting(){
        // TODO: Add different compliments and statements
        String greetingString = "Great Job!\n Your score is";
        greeting.setText(greetingString);
    }

    public void returnToStartMenu(View view){
        Intent startMenu = new Intent(this, MenuActivity.class);
        startActivity(startMenu);
        finish();

    }

}
