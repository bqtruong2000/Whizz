package com.quizchic.whizz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener { // Alt+Enter
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA_Btn, ansB_Btn, ansC_Btn, ansD_Btn;
    Button submitBtn;
    String selectedAnswer, rightAnswer;
    int questionIndex = 0;


    //    Global variables
    public static int score = 0;
    public static int numOfQuestion = 3;
    public static QuestionList questions = new QuestionList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();
        loadQuestions(questionIndex);
    }

    public void init(){
//        totalQuestionsTextView = findViewById(R.id.textViewTotalQues);
        questionTextView= findViewById(R.id.question_displayPlace);
        ansA_Btn= findViewById(R.id.question_answer1);
        ansB_Btn= findViewById(R.id.question_answer2);
        ansC_Btn= findViewById(R.id.question_answer3);
        ansD_Btn= findViewById(R.id.question_answer4);
        submitBtn= findViewById(R.id.button);

        ansA_Btn.setOnClickListener(this);
        ansB_Btn.setOnClickListener(this);
        ansC_Btn.setOnClickListener(this);
        ansD_Btn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    public void loadQuestions(int index){
        Question question = questions.get(index);
        rightAnswer = question.getAnswer();
        questionTextView.setText(question.getQuestion());
        ansA_Btn.setText(question.getAnswer());
        ansB_Btn.setText(question.getOption1());
        ansC_Btn.setText(question.getOption2());
        ansD_Btn.setText(question.getOption3());
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        ansA_Btn.setBackgroundColor(Color.WHITE);
        ansB_Btn.setBackgroundColor(Color.WHITE);
        ansC_Btn.setBackgroundColor(Color.WHITE);
        ansD_Btn.setBackgroundColor(Color.WHITE);

        if (clickedButton.getId() == R.id.button){
            if (selectedAnswer == rightAnswer){
                score++;
            }
            questionIndex++;
            if (questionIndex >= questions.size()){
                finishQuiz();
            } else {
                loadQuestions(questionIndex);
            }
        } else {
            clickedButton.setBackgroundColor(Color.GREEN);
            selectedAnswer = clickedButton.getText().toString().trim();
        }
    }
    public void finishQuiz(){
        new AlertDialog.Builder(this)
                .setTitle("FinishQuiz")
                .setMessage(String.format("Score: %d (Correct Answers: %d/%d)", score,score,numOfQuestion))
                .setPositiveButton("Restar", ((dialogInterface, i) -> restartQuiz()))
                .setNegativeButton("Review", ((dialogInterface, i) -> reviewQuiz()))
                .show();
    }

    public void restartQuiz(){
        score = 0;
        questionIndex = 0;
        loadQuestions(questionIndex);
    }

    public void reviewQuiz(){
//    Next and Previous buttons
    }
//    Yes/No questions
//    shuffle questions
//    shuffle options
//    sound (effect and background)
//    json file (local and firebase)
//
}