package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondIntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_introduction);

        Button question = (Button) findViewById(R.id.secondIntroduction_startButton);
        Button back = (Button) findViewById(R.id.secondIntroduction_back);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSecondQuestionActivity = new Intent(SecondIntroductionActivity.this, SecondQuestionActivity.class);
                startActivity(toSecondQuestionActivity);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(SecondIntroductionActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
    }
}