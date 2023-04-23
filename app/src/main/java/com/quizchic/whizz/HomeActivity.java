package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
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
        TextView question = (TextView) findViewById(R.id.home_question1);
        MediaPlayer soundBackGround = MediaPlayer.create(this, R.raw.soundbackground);
        soundBackGround.setLooping(true);
        soundBackGround.start();

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
}