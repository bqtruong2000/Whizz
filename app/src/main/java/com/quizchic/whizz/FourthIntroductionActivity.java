package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FourthIntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_introduction);

        Button question = (Button) findViewById(R.id.fourthIntroduction_startButton);
        Button back = (Button) findViewById(R.id.fourthIntroduction_back);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQuestionActivity = new Intent(FourthIntroductionActivity.this, QuestionActivity.class);
                QuestionActivity.choosenSubject="question_fourth.json";
                QuestionActivity.occurActivityTimes = 0;
                QuestionActivity.questions.clear();
                startActivity(toQuestionActivity);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(FourthIntroductionActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
    }
}