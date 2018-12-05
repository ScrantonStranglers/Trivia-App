package com.stranglers.scranton.triviaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Gameplay extends AppCompatActivity implements View.OnClickListener {
int questNum = 0;
int correctNum = 0;
Question currentQuestion;
Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        g = (Game) getIntent().getSerializableExtra("game");
        nextQuestion(g);


        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton2);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }

    public void onBackPressed(){
        home();

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
        final Button selected = (Button) v;
        final int x = selected.getCurrentTextColor();

        if (selected.getText().equals(currentQuestion.getCorrect_answer())){
            correctNum++;
            selected.setTextColor(Color.GREEN);
            selected.setText("Correct!");


        }else{
            selected.setTextColor(Color.RED);
            selected.setText("Incorrect");

        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                selected.setTextColor(x);
                if(questNum<9){
                    nextQuestion(g);
                }else{
                    goToResults();
                }
            }
        }, 800);

    }

    private void goToResults() {
        Intent intent = new Intent(this,Results.class);
        intent.putExtra("numRight",correctNum);
        intent.putExtra("game",g);
        startActivity(intent);
    }

    private void nextQuestion(Game g){
        Question currQuest = g.getRandomQuestion(questNum);
        currentQuestion = currQuest;
        String questText = currQuest.getQuestion();
        TextView text = (TextView) findViewById(R.id.textView3);
        text.setText(questText);
        ArrayList<String> ansList = currQuest.getScrambledAnswers();

        Button ans1 = (Button) findViewById(R.id.a1);
        Button ans2 = (Button) findViewById(R.id.a2);
        Button ans3= (Button) findViewById(R.id.a3);
        Button ans4 = (Button) findViewById(R.id.a4);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);

        ans1.setText(ansList.get(0));
        ans2.setText(ansList.get(1));
        ans3.setText(ansList.get(2));
        ans4.setText(ansList.get(3));
        questNum++;
    }
}
