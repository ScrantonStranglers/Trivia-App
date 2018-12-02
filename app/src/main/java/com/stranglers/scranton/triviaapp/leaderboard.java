package com.stranglers.scranton.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class leaderboard extends AppCompatActivity {
boolean fromMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Intent intent = getIntent();
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton5);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

    }

    private void home() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
