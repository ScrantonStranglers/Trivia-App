package com.stranglers.scranton.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//These packages help us parse JSON


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newgame = (Button) findViewById(R.id.newgame);
        final Button leaderboard = (Button) findViewById(R.id.leader);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderboard();
            }
        });
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategories();
            }
        });


    }

    private void openCategories(){
        Intent intent = new Intent(this,Categories.class);
        startActivity(intent);
    }

    private void openLeaderboard(){
        Intent intent = new Intent(this,Leaderboard.class);
        intent.putExtra("fromMenu",true);
        startActivity(intent);
    }
}
