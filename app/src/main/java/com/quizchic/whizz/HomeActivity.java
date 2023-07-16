package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton setting = (ImageButton) findViewById(R.id.settings);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        ImageButton user = (ImageButton) findViewById(R.id.user);

        Button question = (Button) findViewById(R.id.home_question1);
        Button question2 = (Button) findViewById(R.id.home_question2);
        Button question3 = (Button) findViewById(R.id.home_question3);
        Button question4 = (Button) findViewById(R.id.home_question4);
        Button question5 = (Button) findViewById(R.id.home_question5);

        TextView nameOfUser = (TextView) findViewById(R.id.home_userName);
        displayUserName(MainActivity.userName,nameOfUser);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddActivity = new Intent(HomeActivity.this, AddQuestionActivity.class);
                startActivity(toAddActivity);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toUserActivity = new Intent(HomeActivity.this,UserActivity.class);
                startActivity(toUserActivity);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSettingActivity = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(toSettingActivity);
            }
        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SettingActivity.redFlagTimer == false && SettingActivity.isStart == true) || SettingActivity.Setting_numberOfQuestion <= 0){
                    checkTimer();
                }
                else{
                    Intent toIntroductionActivity = new Intent(HomeActivity.this, IntroductionActivity.class);
                    QuestionActivity.chosenSubject = "question.json";
                    QuestionActivity.questions.clear();
                    startActivity(toIntroductionActivity);
                }
            }
        });
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SettingActivity.redFlagTimer == false && SettingActivity.isStart == true) || SettingActivity.Setting_numberOfQuestion <= 0){
                    checkTimer();
                }
                else {
                    Intent toIntroductionActivity = new Intent(HomeActivity.this, IntroductionActivity.class);
                    QuestionActivity.chosenSubject = "question_second.json";
                    QuestionActivity.questions.clear();
                    startActivity(toIntroductionActivity);
                }
            }
        });
        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SettingActivity.redFlagTimer == false && SettingActivity.isStart == true) || SettingActivity.Setting_numberOfQuestion <= 0){
                    checkTimer();
                }
                else {
                    Intent toIntroductionActivity = new Intent(HomeActivity.this, IntroductionActivity.class);
                    QuestionActivity.chosenSubject = "question_third.json";
                    QuestionActivity.questions.clear();
                    startActivity(toIntroductionActivity);
                }
            }
        });

        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SettingActivity.redFlagTimer == false && SettingActivity.isStart == true) || SettingActivity.Setting_numberOfQuestion <= 0){
                    checkTimer();
                }
                else {
                    Intent toIntroductionActivity = new Intent(HomeActivity.this, IntroductionActivity.class);
                    QuestionActivity.chosenSubject = "question_fourth.json";
                    QuestionActivity.questions.clear();
                    startActivity(toIntroductionActivity);
                }
            }
        });

        question5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SettingActivity.redFlagTimer == false && SettingActivity.isStart == true) || SettingActivity.Setting_numberOfQuestion <= 0){
                    checkTimer();
                }
                else {
                    Intent toIntroductionActivity = new Intent(HomeActivity.this, IntroductionActivity.class);
                    QuestionActivity.chosenSubject = "MachineLearning.json";
                    QuestionActivity.questions.clear();
                    startActivity(toIntroductionActivity);
                }
            }
        });
    }

    public void displayUserName(String name, TextView nameOfUser){
        String nullName = "Hello IT-Whizz";
        if(name.equalsIgnoreCase("") == false ){
            nameOfUser.append("Hello " + name);
        }
        else if(name.equalsIgnoreCase("") == true ) {
            nameOfUser.append(nullName);
        }
    }

    public void checkTimer(){
        if(SettingActivity.redFlagTimer == false && SettingActivity.isStart == true && SettingActivity.Setting_numberOfQuestion <= 0 ){
            Toast.makeText(getApplicationContext(), "Warning! The number of your questions have to be greater than 0. Please input time for timer", Toast.LENGTH_SHORT).show();
        }
        else if(SettingActivity.Setting_numberOfQuestion <= 0 ){
            Toast.makeText(getApplicationContext(), "Warning! The number of your questions have to be greater than 0", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Please input time for timer", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toSettingsActivity = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(toSettingsActivity);
                finish();
            }
        }, 3000);
    }
}