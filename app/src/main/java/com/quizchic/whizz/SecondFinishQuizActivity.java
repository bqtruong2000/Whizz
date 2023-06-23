package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondFinishQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_finish_quiz);
        Button back = (Button) findViewById(R.id.finish_previous);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(SecondFinishQuizActivity.this,HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
        Intent intent = getIntent();
        ArrayList<String> selectedAns = intent.getStringArrayListExtra("selectedAns");
        String yourScore = intent.getStringExtra("Score");
        int incorrectAnswers = intent.getIntExtra("incorrectAns",0);
        int correctAnswers = intent.getIntExtra("correctAns",0);
        int totalQuestions = intent.getIntExtra("totalQues",0);
        int outOfTimeQuestions = intent.getIntExtra("outOfTimeAns",0);
        TextView score = findViewById(R.id.finishQuiz_score);
        TextView correctAns = findViewById(R.id.finishQuiz_correctAns);
        TextView incorrectAns = findViewById(R.id.finishQuiz_incorrectAns);
        TextView timeOutOfQues = findViewById(R.id.finishQuiz_timeOutOfQues);
        TextView skipQues = findViewById(R.id.finishQuiz_skipQues);

        score.setText(yourScore);
        correctAns.setText("- Correct Answers: "+ correctAnswers);
        incorrectAns.setText("- Incorrect Answers: "+ incorrectAnswers);
        timeOutOfQues.setText("- Time Out Question: "+ outOfTimeQuestions);
        skipQues.setText("- Skip Question: "+(totalQuestions-(incorrectAnswers+correctAnswers+outOfTimeQuestions)));

        Button restart = findViewById(R.id.finishQuiz_restart);
        Button review = findViewById(R.id.finishQuiz_review);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQuestionActivity = new Intent(SecondFinishQuizActivity.this,SecondQuestionActivity.class);
                startActivity(toQuestionActivity);
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toReviewActivity = new Intent(SecondFinishQuizActivity.this,SecondReviewQuestionActivity.class);
                toReviewActivity.putStringArrayListExtra("selectedAns",selectedAns);
                toReviewActivity.putExtra("Score",yourScore);
                startActivity(toReviewActivity);
            }
        });
    }
}