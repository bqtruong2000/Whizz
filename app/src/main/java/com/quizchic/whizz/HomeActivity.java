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

        Intent i = getIntent();
        String name = i.getStringExtra("username");

        ImageButton user = (ImageButton) findViewById(R.id.user);
        Button question = (Button) findViewById(R.id.home_question1);
        MediaPlayer soundBackGround = MediaPlayer.create(this, R.raw.soundbackground);
        soundBackGround.setLooping(true);
        soundBackGround.start();
        TextView nameOfUser = (TextView) findViewById(R.id.home_userName);
        displayUserName(name,nameOfUser);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toUserActivity = new Intent(HomeActivity.this,UserActivity.class);
                toUserActivity.putExtra("username",name);
                startActivity(toUserActivity);
                soundBackGround.pause();

            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQuestionActivity = new Intent(HomeActivity.this, QuestionActivity.class);
                startActivity(toQuestionActivity);
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