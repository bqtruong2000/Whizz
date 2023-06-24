package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
                Intent toIntroductionActivity = new Intent(HomeActivity.this, FristIntroductionActivity.class);
                startActivity(toIntroductionActivity);
            }
        });
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toIntroductionActivity = new Intent(HomeActivity.this, SecondIntroductionActivity.class);
                startActivity(toIntroductionActivity);
            }
        });
        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toIntroductionActivity = new Intent(HomeActivity.this, ThirdIntroduction.class);
                startActivity(toIntroductionActivity);
            }
        });

        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toIntroductionActivity = new Intent(HomeActivity.this, FourthIntroductionActivity.class);
                startActivity(toIntroductionActivity);
            }
        });
    }

    public void displayUserName(String name, TextView nameOfUser){
        String nullName = "Hello User";
        if(name.equalsIgnoreCase("") == false ){
            nameOfUser.append("Hello " + name);
        }
        else if(name.equalsIgnoreCase("") == true ) {
            nameOfUser.append(nullName);
        }
    }
}