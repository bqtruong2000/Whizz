package com.quizchic.whizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent i = getIntent();
        String name = i.getStringExtra("username");


        TextView nameOfUser = (TextView) findViewById(R.id.usersname);
        Button homeButton = (Button) findViewById(R.id.home);



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeActivity = new Intent(UserActivity.this, HomeActivity.class);
                startActivity(toHomeActivity);
            }
        });
        displayUserName(name,nameOfUser);
    }

    public void displayUserName(String name, TextView nameOfUser){
        String nullName = "User's Name";
        if(name.equalsIgnoreCase("") == false ){
            nameOfUser.append(name);
        }
        else if(name.equalsIgnoreCase("") == true ) {
            nameOfUser.append(nullName);
        }
    }
}