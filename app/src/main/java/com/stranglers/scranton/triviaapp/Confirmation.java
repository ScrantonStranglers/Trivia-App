package com.stranglers.scranton.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent i = getIntent();
        final Game g = (Game) i.getSerializableExtra("game");
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText("Are you ready to answer 10 tough questions in the " +g.getQuestions().get(0).getCategory()+" category?" );
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton3);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

        Button proceed = (Button) findViewById(R.id.proceed);
        final Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(g);
            }
        });


    }


    private void home() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void cancel(){
        Intent intent = new Intent(this,Categories.class);
        startActivity(intent);
    }
    private void startGame(Game g){
        Intent intent = new Intent(this,Gameplay.class);
        intent.putExtra("game",g);
        startActivity(intent);
    }


}
