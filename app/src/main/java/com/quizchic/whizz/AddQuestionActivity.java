package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class AddQuestionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ImageButton home = (ImageButton) findViewById(R.id.home);
        ImageButton setting = (ImageButton) findViewById(R.id.settings);
        ImageButton user = (ImageButton) findViewById(R.id.user);
        Button back = (Button) findViewById(R.id.previous);
        Spinner courseName = (Spinner) findViewById(R.id.spinner);
        EditText questionInput = (EditText) findViewById(R.id.editQuestion);
        EditText answerInput = (EditText) findViewById(R.id.editAnswer);
        EditText option1Input = (EditText) findViewById(R.id.editOption1);
        EditText option2Input = (EditText) findViewById(R.id.editOption2);
        EditText option3Input = (EditText) findViewById(R.id.editOption3);
        Button submitButton = (Button) findViewById(R.id.startButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getQuestion = questionInput.getText().toString(); //question
                String getAnswer = answerInput.getText().toString(); //answer
                String getOption1 = option1Input.getText().toString(); //opt1
                String getOption2 = option2Input.getText().toString(); //
                String getOption3 = option3Input.getText().toString();


                File file = new File(getExternalFilesDir(null), "question.txt");
                JSONObject js = new JSONObject();

                try {

                    js.put("question",getQuestion);
                    js.put("answer",getAnswer);
                    js.put("option1",getOption1);
                    js.put("option2",getOption2);
                    js.put("option3",getOption3);
                    writeToFile(js, file); //jsObj
                    Intent toHomeActivity = new Intent(AddQuestionActivity.this, HomeActivity.class);
                    Context context = getApplicationContext();
                    CharSequence text = "SUCCESSFULLY INSERT!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP,0,250);
                    toast.show();
                    startActivity(toHomeActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(AddQuestionActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toUserActivity = new Intent(AddQuestionActivity.this,UserActivity.class);
                startActivity(toUserActivity);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSettingActivity = new Intent(AddQuestionActivity.this, SettingActivity.class);
                startActivity(toSettingActivity);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(AddQuestionActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
    }

    public void writeToFile(JSONObject js, File file) {
        // add-write text into file
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write(js.toString() + "\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}