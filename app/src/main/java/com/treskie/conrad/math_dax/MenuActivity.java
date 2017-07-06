package com.treskie.conrad.math_dax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void QuizActivity(View view){
        Intent goToNextActivity= new Intent(this, MainQuiz.class);
        startActivity(goToNextActivity);
        finish();
    }


}
