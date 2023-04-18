package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
<<<<<<< HEAD
import android.media.MediaPlayer;
=======
>>>>>>> f1cdcce4538356ae9f300e631251314749ea3b93
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText placeHolder = findViewById(R.id.placeHolder);
        Button startButton = findViewById(R.id.startButton);
<<<<<<< HEAD
        final MediaPlayer soundButton = MediaPlayer.create(this, R.raw.ring);
=======

>>>>>>> f1cdcce4538356ae9f300e631251314749ea3b93

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String s = placeHolder.getText().toString();
                intent.putExtra("username",s);
                startActivity(intent);
<<<<<<< HEAD
                soundButton.start();
=======
>>>>>>> f1cdcce4538356ae9f300e631251314749ea3b93
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