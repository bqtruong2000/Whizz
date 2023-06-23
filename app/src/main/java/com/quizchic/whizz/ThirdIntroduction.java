package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdIntroduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_introduction);

        Button question = (Button) findViewById(R.id.thirdIntroduction_startButton);
        Button back = (Button) findViewById(R.id.thirdIntroduction_back);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQuestionActivity = new Intent(ThirdIntroduction.this, QuestionActivity.class);
                QuestionActivity.choosenSubject = "question_third.json";
                QuestionActivity.occurActivityTimes = 0;
                startActivity(toQuestionActivity);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(ThirdIntroduction.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
    }
}