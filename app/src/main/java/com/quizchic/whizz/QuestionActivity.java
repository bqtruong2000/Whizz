package com.quizchic.whizz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{ // Alt+Enter
    int totalQuestions = questions.size();
    TextView questionTextView,questionScore;
    Button ansA_Btn, ansB_Btn, ansC_Btn, ansD_Btn;
    Button submitBtn,previousBtn,nextBtn;
    Button selectedBtn;
    String selectedAnswer, rightAnswer;
    int questionIndex = 0;
    List<Integer> idSaved = new ArrayList<Integer>();
    int id;
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
        questionScore = findViewById(R.id.question_score);
        questionTextView= findViewById(R.id.question_displayPlace);
        ansA_Btn= findViewById(R.id.question_answer1);
        ansB_Btn= findViewById(R.id.question_answer2);
        ansC_Btn= findViewById(R.id.question_answer3);
        ansD_Btn= findViewById(R.id.question_answer4);
        submitBtn= findViewById(R.id.question_submit);
        nextBtn = findViewById(R.id.question_next);
        previousBtn = findViewById(R.id.question_previous);
        nextBtn.setVisibility(View.INVISIBLE);
        previousBtn.setVisibility(View.INVISIBLE);
        nextBtn.setEnabled(false);
        previousBtn.setEnabled(false);


        ansA_Btn.setOnClickListener(this);
        ansB_Btn.setOnClickListener(this);
        ansC_Btn.setOnClickListener(this);
        ansD_Btn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    public void loadQuestions(int index){
        questionScore.setText("Score:"+score+"/"+totalQuestions );
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
        ansA_Btn.setBackgroundColor(733757);
        ansB_Btn.setBackgroundColor(733757);
        ansC_Btn.setBackgroundColor(733757);
        ansD_Btn.setBackgroundColor(733757);

        if (clickedButton.getId() == R.id.question_submit){
            idSaved.add(id);
            if (selectedAnswer == rightAnswer){
                score++;
                questionScore.setText("Score:"+score+"/"+totalQuestions );
            }
            questionIndex++;
            if (questionIndex >= totalQuestions){
                finishQuiz();
            } else {
                loadQuestions(questionIndex);
            }
        } else if(clickedButton.getId() == R.id.question_next){
            if(questionIndex < totalQuestions-1){
                questionIndex++;
                loadQuestions(questionIndex);
                loadCheck(questionIndex);
            }else{
                warningReviewLimit();
            }
        } else if (clickedButton.getId() == R.id.question_previous) {
            if(questionIndex>0){
                questionIndex --;
                loadQuestions(questionIndex);
                loadCheck(questionIndex);
            }else{
                warningReviewLimit();
            }
        } else{
            id = clickedButton.getId();
            clickedButton.setBackgroundColor(Color.WHITE);
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

    public void warningReviewLimit(){
        new AlertDialog.Builder(this)
                .setMessage("can't go anymore")
                .setPositiveButton("Restar", ((dialogInterface, i) -> restartQuiz()))
                .setNegativeButton("continue", ((dialogInterface, i) -> reviewQuiz()))
                .show();
    }

    public void loadCheck(int questionIndex){
        if(idSaved.get(questionIndex)!= null){
            selectedBtn = findViewById(idSaved.get(questionIndex));
            if(selectedBtn.getText() == rightAnswer){
                selectedBtn.setBackgroundColor(Color.GREEN);
            } else {
                selectedBtn.setBackgroundColor(Color.RED);
                checkRightAnswer();
            }
        }else{
            checkRightAnswer();
        }
    }
    public void checkRightAnswer(){
            if (ansA_Btn.getText() == rightAnswer)
                ansA_Btn.setBackgroundColor(Color.GREEN);
            if (ansB_Btn.getText() == rightAnswer)
                ansB_Btn.setBackgroundColor(Color.GREEN);
            if (ansC_Btn.getText() == rightAnswer)
                ansC_Btn.setBackgroundColor(Color.GREEN);
            if (ansD_Btn.getText() == rightAnswer)
                ansD_Btn.setBackgroundColor(Color.GREEN);
    }

    public void restartQuiz(){
        score = 0;
        questionIndex = 0;
        nextBtn.setVisibility(View.INVISIBLE);
        previousBtn.setVisibility(View.INVISIBLE);
        nextBtn.setEnabled(false);
        previousBtn.setEnabled(false);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setEnabled(true);
        ansA_Btn.setEnabled(true);
        ansB_Btn.setEnabled(true);
        ansC_Btn.setEnabled(true);
        ansD_Btn.setEnabled(true);
        loadQuestions(questionIndex);
    }

    public void reviewQuiz() {
//        loadQuestions(questionIndex);
        nextBtn.setVisibility(View.VISIBLE);
        previousBtn.setVisibility(View.VISIBLE);
        nextBtn.setEnabled(true);
        previousBtn.setEnabled(true);
        submitBtn.setVisibility(View.INVISIBLE);
        submitBtn.setEnabled(false);
        ansA_Btn.setEnabled(false);
        ansB_Btn.setEnabled(false);
        ansC_Btn.setEnabled(false);
        ansD_Btn.setEnabled(false);
//        loadCheck(questionIndex);
    }
//    Yes/No questions
//    shuffle questions
//    shuffle options
//    sound (effect and background)
//    json file (local and firebase)
//
}