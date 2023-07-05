package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    public String fileName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ImageButton home = (ImageButton) findViewById(R.id.home);
        ImageButton setting = (ImageButton) findViewById(R.id.settings);
        ImageButton user = (ImageButton) findViewById(R.id.user);
        Button back = (Button) findViewById(R.id.previous);

        EditText questionInput = (EditText) findViewById(R.id.editQuestion);
        EditText answerInput = (EditText) findViewById(R.id.editAnswer);
        EditText option1Input = (EditText) findViewById(R.id.editOption1);
        EditText option2Input = (EditText) findViewById(R.id.editOption2);
        EditText option3Input = (EditText) findViewById(R.id.editOption3);
        Button submitButton = (Button) findViewById(R.id.startButton);

        String[] options = {"Choose a subject","Object-Oriented Programming", "Database Fundamental", "Computer Systems", "Operation Fundamental"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    return;
                }

                switch (position){
                    case 1: {
                        fileName = "oop";
                        break;
                    }
                    case 2:{
                        fileName = "databaseFundamental";
                        break;
                    }
                    case 3:{
                        fileName = "computerSystems";
                        break;
                    }
                    case 4: {
                        fileName = "operationFundamental";
                        break;
                    }
                    default:
                        fileName = null;
                        break;
                }

                Toast.makeText(AddQuestionActivity.this, "Selected value: " + fileName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String question = questionInput.getText().toString();
                String answer = answerInput.getText().toString();
                String option1 = option1Input.getText().toString();
                String option2 = option2Input.getText().toString();
                String option3 = option3Input.getText().toString();

                File file = new File(getExternalFilesDir(null), fileName+".json");
                JSONObject js = new JSONObject();

                try {
                    js.put("question",question);
                    js.put("answer",answer);
                    js.put("option1",option1);
                    js.put("option2",option2);
                    js.put("option3",option3);
                    writeToFile(js, file);

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

        ArrayList<String> processList;
        processList = readFromFile(file);

        if (!processList.isEmpty()) {
            int lastIndex = processList.size() - 1;
            String lastElement = processList.get(lastIndex);
            if (lastElement.endsWith("]")) {
                processList.set(lastIndex, lastElement.substring(0, lastElement.length() - 1));
            }
        }


        for (int i = 0; i < processList.size(); i++) {
            String element = processList.get(i);
            if (element.contains("[")) {
                element = element.replace("[", "");
                processList.set(i, element);
            }
        }

        processList.add(js.toString());
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(processList + "\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList readFromFile(File file){
        ArrayList<String> stringList = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }


}