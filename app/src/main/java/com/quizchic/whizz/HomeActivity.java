package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton setting = (ImageButton) findViewById(R.id.settings);
        ImageButton user = (ImageButton) findViewById(R.id.user);
        Button question = (Button) findViewById(R.id.home_question1);
        Button question2 = (Button) findViewById(R.id.home_question2);

        TextView nameOfUser = (TextView) findViewById(R.id.home_userName);
        displayUserName(MainActivity.userName,nameOfUser);
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
    }

    public void displayUserName(String name, TextView nameOfUser){
        String nullName = "Wellcome User";
        if(name.equalsIgnoreCase("") == false ){
            nameOfUser.append("Wellcome " + name);
        }
        else if(name.equalsIgnoreCase("") == true ) {
            nameOfUser.append(nullName);
        }
    }
}