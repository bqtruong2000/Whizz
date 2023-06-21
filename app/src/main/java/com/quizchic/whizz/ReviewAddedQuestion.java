package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class ReviewAddedQuestion extends AppCompatActivity {

    Button btnSave;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_added_question);

        tvContent = findViewById(R.id.addedQuestion);
        btnSave = findViewById(R.id.loadFileButton);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getExternalFilesDir(null), "question.txt");
                loadFromFile(file);
            }
        });
    }

    public void loadFromFile(File file){

        byte[] content = new byte[(int)file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(content);
            fis.close();
            tvContent.setText(new String(content));
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}