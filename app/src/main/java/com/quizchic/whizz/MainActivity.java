package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public static String userName;
    boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText placeHolder = findViewById(R.id.placeHolder);
        Button startButton = findViewById(R.id.startButton);
        MediaPlayer soundButton = MediaPlayer.create(this, R.raw.ring);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String s = placeHolder.getText().toString();
                userName = s;
//                intent.putExtra("username",s);
                startActivity(intent);
                soundButton.start();
            }
        });
        placeHolder.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(isFirst){
                    placeHolder.setText("");
                    isFirst = false;
                }
            }
        });

    }
}