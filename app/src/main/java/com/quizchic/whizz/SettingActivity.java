package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import services.BackgroundMusicService;

public class SettingActivity extends AppCompatActivity {
    public static Boolean isPlaying = false;
    public static Boolean isPlaySE =false;
    public static Boolean isStart =false;

    public static int Setting_numberOfQuestion = 5;

    public static int milliSecond = 0;

    public static boolean redFlagTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageButton home = (ImageButton)findViewById(R.id.home);
        ImageButton user = (ImageButton) findViewById(R.id.user);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        EditText hoursInput = findViewById(R.id.hoursInput);
        EditText minsInput = findViewById(R.id.minutesInput);
        EditText secondInput = findViewById(R.id.secondsInput);
        EditText numOfQuesInput = findViewById(R.id.numOfQuestion);
        if(Setting_numberOfQuestion != 5)
            numOfQuesInput.setText(String.valueOf(Setting_numberOfQuestion));
        if(milliSecond != 0){
            hoursInput.setText(String.valueOf(milliSecond/3600000));
            milliSecond = milliSecond%3600000;
            minsInput.setText(String.valueOf(milliSecond/60000));
            milliSecond = milliSecond%60000;
            secondInput.setText(String.valueOf(milliSecond/1000));
            milliSecond = 0;
            redFlagTimer = true;
        }
        else{
            redFlagTimer = false;
        }


        Switch sbg_switch = (Switch) findViewById(R.id.switch_sound1);
        Switch se_switch = (Switch) findViewById(R.id.switch_sound2);
        Switch timer_switch = (Switch) findViewById(R.id.switch_timer);

        ColorStateList colorThumb = new ColorStateList(
                new int[][] {
                        new int[] {android.R.attr.state_checked}, //trạng thái checked
                        new int[] {} //trạng thái bình thường
                },
                new int[] {
                        Color.BLACK,
                        Color.WHITE
                }
        );
        ColorStateList colorTrack = new ColorStateList(
                new int[][] {
                        new int[] {android.R.attr.state_checked}, //trạng thái checked
                        new int[] {} //trạng thái bình thường
                },
                new int[] {
                        Color.BLACK,
                        Color.WHITE
                }
        );

        sbg_switch.setThumbTintList(colorThumb);
        sbg_switch.setTrackTintList(colorTrack);
        se_switch.setThumbTintList(colorThumb);
        se_switch.setTrackTintList(colorTrack);
        timer_switch.setThumbTintList(colorThumb);
        timer_switch.setTrackTintList(colorTrack);

        sbg_switch.setChecked(isPlaying);
        se_switch.setChecked(isPlaySE);
        timer_switch.setChecked(isStart);
        if(!isStart){
            hoursInput.setText("0");
            minsInput.setText("0");
            secondInput.setText("0");
            hoursInput.setEnabled(false);
            minsInput.setEnabled(false);
            secondInput.setEnabled(false);
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(SettingActivity.this, HomeActivity.class);
                Setting_numberOfQuestion = numOfQuesInput.getText().equals("")?5:Integer.parseInt(numOfQuesInput.getText().toString());
                milliSecond = Integer.parseInt(hoursInput.getText().toString())*3600000
                        + Integer.parseInt(minsInput.getText().toString())*60000
                        +Integer.parseInt(secondInput.getText().toString())*1000;
                startActivity(toHomeActivity);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddActivity = new Intent(SettingActivity.this, AddQuestionActivity.class);
                Setting_numberOfQuestion = numOfQuesInput.getText().equals("")?5:Integer.parseInt(numOfQuesInput.getText().toString());
                milliSecond = Integer.parseInt(hoursInput.getText().toString())*3600000
                        + Integer.parseInt(minsInput.getText().toString())*60000
                        +Integer.parseInt(secondInput.getText().toString())*1000;
                startActivity(toAddActivity);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toUserActivity = new Intent(SettingActivity.this,UserActivity.class);
                Setting_numberOfQuestion = numOfQuesInput.getText().equals("")?5:Integer.parseInt(numOfQuesInput.getText().toString());
                milliSecond = Integer.parseInt(hoursInput.getText().toString())*3600000
                        + Integer.parseInt(minsInput.getText().toString())*60000
                        +Integer.parseInt(secondInput.getText().toString())*1000;
                startActivity(toUserActivity);
            }
        });

        sbg_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   if (!isPlaying) {
                       startService(new Intent(SettingActivity.this, BackgroundMusicService.class));
                       isPlaying= true;
                    }
                } else {
                    if (isPlaying) {
                        stopService(new Intent(SettingActivity.this, BackgroundMusicService.class));
                        isPlaying = false;
                    }
                }
            }
        });
        se_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!isPlaySE) {
                        isPlaySE= true;
                    }
                } else {
                    if (isPlaySE) {
                        isPlaySE = false;
                    }
                }
            }
        });
        timer_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!isStart) {
                        isStart= true;
                        hoursInput.setEnabled(true);
                        minsInput.setEnabled(true);
                        secondInput.setEnabled(true);
                    }
                } else {
                    if (isStart) {
                        isStart = false;
                        hoursInput.setText("0");
                        minsInput.setText("0");
                        secondInput.setText("0");
                        hoursInput.setEnabled(false);
                        minsInput.setEnabled(false);
                        secondInput.setEnabled(false);
                    }
                }
            }
        });
    }
}