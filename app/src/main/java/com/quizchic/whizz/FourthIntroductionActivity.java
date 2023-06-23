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
                Intent toQuestionActivity = new Intent(FourthIntroductionActivity.this, FourthQuestionActivity.class);
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