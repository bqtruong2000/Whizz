package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageButton home = (ImageButton)findViewById(R.id.home);
        ImageButton user = (ImageButton) findViewById(R.id.user);
        Switch sbg_switch = (Switch) findViewById(R.id.switch_sound1);
        Switch se_switch = (Switch) findViewById(R.id.switch_sound2);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isEnabled;
        boolean isMusicAnswerEnabled;

        MediaPlayer soundBackGround = MediaPlayer.create(SettingActivity.this, R.raw.soundbackground);
        soundBackGround.setLooping(true);
        isEnabled = sharedPreferences.getBoolean("isEnabled", false);
        sbg_switch.setChecked(isEnabled);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toUserActivity = new Intent(SettingActivity.this,UserActivity.class);
                startActivity(toUserActivity);
            }
        });

        sbg_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("isEnabled", isChecked).apply();
                if (isChecked) {
                    if (!soundBackGround.isPlaying()) {
                        soundBackGround.start();
                    }
                } else {
                    if (soundBackGround.isPlaying()) {
                        soundBackGround.pause();
                    }
                }
            }
        });


        isMusicAnswerEnabled = sharedPreferences.getBoolean("isMusicAnswerEnabled", false);
        se_switch.setChecked(isMusicAnswerEnabled);
        se_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("isMusicAnswerEnabled", isChecked).apply();
            }
        });


    }
}