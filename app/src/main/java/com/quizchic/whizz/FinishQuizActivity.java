package com.quizchic.whizz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class FinishQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);
        Button back = (Button) findViewById(R.id.finish_previous);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(FinishQuizActivity.this,HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
        Intent intent = getIntent();
        ArrayList<String> selectedAns = intent.getStringArrayListExtra("selectedAns");
        String yourScore = intent.getStringExtra("Score");
        int incorrectAnswers = intent.getIntExtra("incorrectAns",0);
        int correctAnswers = intent.getIntExtra("correctAns",0);
        int totalQuestions = intent.getIntExtra("totalQues",0);
        new AlertDialog.Builder(this)
                .setTitle("FinishQuiz")
                .setMessage(String.format("%s\nCorrect Answers: %d\nIncorrect Answers: %d\nSkip Question: %d", yourScore,correctAnswers,incorrectAnswers,totalQuestions-(incorrectAnswers+correctAnswers)))
                .setPositiveButton("Restar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent toReviewActivity = new Intent(FinishQuizActivity.this,QuestionActivity.class);
                        startActivity(toReviewActivity);
                    }
                })
                .setNegativeButton("Review", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent toReviewActivity = new Intent(FinishQuizActivity.this,ReviewActivity.class);
                        toReviewActivity.putStringArrayListExtra("selectedAns",selectedAns);
                        toReviewActivity.putExtra("Score",yourScore);
                        startActivity(toReviewActivity);
                    }
                })
                .show();
    }
}