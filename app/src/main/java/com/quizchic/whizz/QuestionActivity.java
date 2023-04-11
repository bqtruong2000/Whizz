package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity {

    public String listOfQuestion[] = {"1 + 1 = ?", "2 + 10 = ?", "4 * 10 = ?", "Question 4", "Question 5", "Question 6", "Question 7", "Question 8", "Question 9", "Question 10", "Question 11", "Question 12", "Question 13", "Question 14", "Question 15", "Question 16", "Question 17", "Question 18", "Question 19", "Question 20"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView displaceQuestion = (TextView) findViewById(R.id.question_displayPlace);
        Intent intent = getIntent();


        displaceQuestion.append(listOfQuestion[randomQuestion(listOfQuestion.length)]);
    }

    public int randomQuestion(int numberOfQuestions){
        return (int)(Math.random()*(numberOfQuestions - 0 + 1) + 0);
    }

}