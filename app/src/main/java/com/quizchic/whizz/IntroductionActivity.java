package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        TextView introduction = (TextView) findViewById(R.id.Introduction_introduction);
        Button question = (Button) findViewById(R.id.Introduction_startButton);
        Button back = (Button) findViewById(R.id.Introduction_back);

        if(QuestionActivity.chosenSubject.equals("question.json")){
            introduction.setText("Object-oriented programming (OOP) is a computer programming model that organizes software design around data, or objects, rather than functions and logic. An object can be defined as a data field that has unique attributes and behavior.");
        }
        if(QuestionActivity.chosenSubject.equals("question_second.json")){
            introduction.setText("The course introduces the basic concepts of databases, the concepts of the associative entity model, the relational model. Concept of relational algebra and data query language (SQL). Concepts of functional dependencies, keys, minimum coverage, normal form, and decomposition and aggregation algorithms in the design of a database.");
        }
        if(QuestionActivity.chosenSubject.equals("question_third.json")){
            introduction.setText("A computer system is an electronic machine that operates and performs tasks under the control of instructions stored in memory. In basic terms, a computer system is a device that can accept input, store, retrieve, process the data, and produces output information.");
        }
        if(QuestionActivity.chosenSubject.equals("question_fourth.json")){
            introduction.setText("Operation fundamentals are the fundamental principles and concepts that form the basis of effective and efficient operations management within an organization. It encompasses the essential knowledge and techniques required to design, plan, execute, and control various operational processes.");
        }
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQuestionActivity = new Intent(IntroductionActivity.this, QuestionActivity.class);
                startActivity(toQuestionActivity);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(IntroductionActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
    }
}