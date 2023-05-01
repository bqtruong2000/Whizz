package com.quizchic.whizz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    int questionIndex = 0;
    int score = 0;
    private int totalQuestions;

    TextView questionTextView,questionScore;
    Button ansA_Btn, ansB_Btn, ansC_Btn, ansD_Btn, submitBtn;
    String selectedAnswer, rightAnswer;
    String ans;

    ArrayList questions = new ArrayList<Question>();
    ArrayList<String> selectedAns = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        String json = getJson("question.json");
        convertJsonToQuestions(json);
        totalQuestions = questions.size();

        init();
        loadQuestions(questionIndex);
    }

    public String getJson(String fileName){
        String json = "";
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception ex){
            Log.e("TAG", "Load json error: " + ex.getMessage());
        }
        return json;
    }
    public void convertJsonToQuestions(String json){

        try {
            JSONArray jsonArr = new JSONArray(json);
            int size = jsonArr.length();
            for (int i = 0; i < size; i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                Question question = new Question();
                question.setQuestion(jsonObject.getString("question"));
                question.setAnswer(jsonObject.getString("answer"));
                question.setOption1(jsonObject.getString("option1"));
                question.setOption2(jsonObject.getString("option2"));
                question.setOption3(jsonObject.getString("option3"));
                questions.add(question);
            }
        } catch (Exception ex){
            Log.e("TAG", "Load json error: " + ex.getMessage());
        }
    }

    public void init(){
        questionScore = findViewById(R.id.question_score);
        questionTextView= findViewById(R.id.question_displayPlace);
        ansA_Btn= findViewById(R.id.question_answer1);
        ansB_Btn= findViewById(R.id.question_answer2);
        ansC_Btn= findViewById(R.id.question_answer3);
        ansD_Btn= findViewById(R.id.question_answer4);
        submitBtn= findViewById(R.id.question_submit);

        ansA_Btn.setOnClickListener(this);
        ansB_Btn.setOnClickListener(this);
        ansC_Btn.setOnClickListener(this);
        ansD_Btn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    public void loadQuestions(int index){
        questionScore.setText("Score:"+score+"/"+totalQuestions );
        Question question = (Question) questions.get(index);
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
            selectedAns.add(ans);
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
        } else{
            clickedButton.setBackgroundColor(Color.WHITE);
            selectedAnswer = clickedButton.getText().toString().trim();
            ans = selectedAnswer;
        }
    }
    public void finishQuiz(){
        new AlertDialog.Builder(this)
                .setTitle("FinishQuiz")
                .setMessage(String.format("Score: %d (Correct Answers: %d/%d)", score,score,totalQuestions))
                .setPositiveButton("Restar", ((dialogInterface, i) -> restartQuiz()))
                .setNegativeButton("Review", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent toReviewActivity = new Intent(QuestionActivity.this,ReviewActivity.class);
                        toReviewActivity.putStringArrayListExtra("selectedAns",selectedAns);
                        toReviewActivity.putExtra("Score",questionScore.getText().toString().trim());
                        startActivity(toReviewActivity);
                    }
                })
                .show();
    }

    public void restartQuiz(){
        score = 0;
        questionIndex = 0;
        loadQuestions(questionIndex);
    }
}


//public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{ // Alt+Enter
//    int totalQuestions = questions.size();
//    TextView questionTextView,questionScore;
//    Button ansA_Btn, ansB_Btn, ansC_Btn, ansD_Btn;
//    Button submitBtn;
//    String selectedAnswer, rightAnswer;
//    String ans;
//    //    Global variables
//    public static int questionIndex = 0;
//    public static int score = 0;
//    public static QuestionList questions = new QuestionList();
//
//    ArrayList<String> selectedAns = new ArrayList<String>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_question);
//
//        init();
//        loadQuestions(questionIndex);
//    }
//
//    public void init(){
//        questionScore = findViewById(R.id.question_score);
//        questionTextView= findViewById(R.id.question_displayPlace);
//        ansA_Btn= findViewById(R.id.question_answer1);
//        ansB_Btn= findViewById(R.id.question_answer2);
//        ansC_Btn= findViewById(R.id.question_answer3);
//        ansD_Btn= findViewById(R.id.question_answer4);
//        submitBtn= findViewById(R.id.question_submit);
//
//        ansA_Btn.setOnClickListener(this);
//        ansB_Btn.setOnClickListener(this);
//        ansC_Btn.setOnClickListener(this);
//        ansD_Btn.setOnClickListener(this);
//        submitBtn.setOnClickListener(this);
//    }
//
//    public void loadQuestions(int index){
//        questionScore.setText("Score:"+score+"/"+totalQuestions );
//        Question question = questions.get(index);
//        rightAnswer = question.getAnswer();
//        questionTextView.setText(question.getQuestion());
//        ansA_Btn.setText(question.getAnswer());
//        ansB_Btn.setText(question.getOption1());
//        ansC_Btn.setText(question.getOption2());
//        ansD_Btn.setText(question.getOption3());
//    }
//
//    @Override
//    public void onClick(View view) {
//        Button clickedButton = (Button) view;
//        ansA_Btn.setBackgroundColor(733757);
//        ansB_Btn.setBackgroundColor(733757);
//        ansC_Btn.setBackgroundColor(733757);
//        ansD_Btn.setBackgroundColor(733757);
//
//        if (clickedButton.getId() == R.id.question_submit){
//            selectedAns.add(ans);
//            if (selectedAnswer == rightAnswer){
//                score++;
//                questionScore.setText("Score:"+score+"/"+totalQuestions );
//            }
//            questionIndex++;
//            if (questionIndex >= totalQuestions){
//                finishQuiz();
//            } else {
//                loadQuestions(questionIndex);
//            }
//        } else{
//            clickedButton.setBackgroundColor(Color.WHITE);
//            selectedAnswer = clickedButton.getText().toString().trim();
//            ans = selectedAnswer;
//        }
//    }
//    public void finishQuiz(){
//        new AlertDialog.Builder(this)
//                .setTitle("FinishQuiz")
//                .setMessage(String.format("Score: %d (Correct Answers: %d/%d)", score,score,totalQuestions))
//                .setPositiveButton("Restar", ((dialogInterface, i) -> restartQuiz()))
//                .setNegativeButton("Review", new DialogInterface.OnClickListener(){
//
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent toReviewActivity = new Intent(QuestionActivity.this,ReviewActivity.class);
//                        toReviewActivity.putStringArrayListExtra("selectedAns",selectedAns);
//                        toReviewActivity.putExtra("Score",questionScore.getText().toString().trim());
//                        startActivity(toReviewActivity);
//                    }
//                })
//                .show();
//    }
//
//    public void restartQuiz(){
//        score = 0;
//        questionIndex = 0;
//        loadQuestions(questionIndex);
//    }
//    Yes/No questions
//    shuffle questions
//    shuffle options
//    sound (effect and background)
//    json file (local and firebase)
//
//}