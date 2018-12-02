package com.stranglers.scranton.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class results extends AppCompatActivity {
    Game g;
    int result;
    int adjustedScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView message = findViewById(R.id.textView4);
        TextView score = findViewById(R.id.scoreText);
        Intent currentIntent = getIntent();
         g = (Game) currentIntent.getSerializableExtra("game");
        Button submit =  findViewById(R.id.button);



        result = currentIntent.getIntExtra("numRight",0);
        adjustedScore = result*1234;
        score.setText("Results:\n You answered " + result + " /10\n Your score is: " + adjustedScore );
        if(result==10){
            message.setText("Wow, you really know your stuff! Dwight is proud of you.");
        }else if(result>4){
            message.setText("You preformed well, but Dwight still thinks you have much to learn");
        }else if(result!=0){
            message.setText("Dwight is disappointed, please do better next time");
        }else{
            message.setText("Unacceptable, Dwight will have you fired for this");
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToLeaderboard();
            }
        });

    }

    private void addToLeaderboard() {
        Intent intent = new Intent(this,leaderboard.class);
        intent.putExtra("category",g.getQuestions().get(0).getCategory());
        EditText nameEnter = findViewById(R.id.editText);
        String name = nameEnter.getText().toString();
        intent.putExtra("name",name);
        intent.putExtra("score",adjustedScore);
        intent.putExtra("fromMenu",false);
        startActivity(intent);
    }
}
