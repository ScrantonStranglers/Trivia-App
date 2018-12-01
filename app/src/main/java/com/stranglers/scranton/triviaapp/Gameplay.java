package com.stranglers.scranton.triviaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Gameplay extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Game g = (Game) getIntent().getSerializableExtra("game");
        Question currQuest = g.getRandomQuestion();
        String questText = currQuest.getQuestion();
        TextView text = (TextView) findViewById(R.id.textView3);
        text.setText(questText);

        Button ans1 = (Button) findViewById(R.id.a1);
        Button ans2 = (Button) findViewById(R.id.a2);
        Button ans3= (Button) findViewById(R.id.a3);
        Button ans4 = (Button) findViewById(R.id.a4);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);

        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton2);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }

    public void onBackPressed(){

    }
    private void home(){
        final Intent intent = new Intent(this,MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit? you will lose your progress in this game.");
        builder.setPositiveButton("Yes, I am sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               startActivity(intent);
            }
        });
        builder.setNegativeButton("No, I don't want to leave", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    @Override
    public void onClick(View v) {

    }
}
