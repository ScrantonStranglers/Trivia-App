package com.stranglers.scranton.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Categories extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
        Button comp = (Button) findViewById(R.id.computers);
        Button general = (Button) findViewById(R.id.general);
        Button science = (Button) findViewById(R.id.science);
        Button sport = (Button) findViewById(R.id.sports);

        comp.setOnClickListener(this);
        general.setOnClickListener(this);
        science.setOnClickListener(this);
        sport.setOnClickListener(this);
    }

    private void home() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /* This method will be used to load in the JSON files into Strings.
      We will call this method every time we create a game, in order to give the user category options
    */
    private String loadFileToString(int jsonID){
        String line;
        String json = "";
        InputStream is = getResources().openRawResource(jsonID);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                json = json + line;
            }
        }catch (Exception e){
            Log.e("JSON Loading Error",e.getStackTrace().toString());
        }
        return json;
    }
    /*
    This Method takes the string from loadFileToString and makes a game out of it
    It can be expanded to support making specific kinds of games, i.e. timed/ not timed
    * */
    private Game jsonToGame(String jsonString){
        Game game = new Game();
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray questionList = jsonObject.getJSONArray("results");
            for (int i = 0; i<questionList.length();i++){
                JSONObject quest = questionList.getJSONObject(i);
                String difficulty = quest.getString("difficulty");
                String category = quest.getString("category");
                String type = quest.getString("type");
                String question = quest.getString("question");
                String correct_answer = quest.getString("correct_answer");
                JSONArray ans_array = quest.getJSONArray("incorrect_answers");
                String[] incorrect_answers = new String[ans_array.length()];
                for (int j = 0; j<ans_array.length();j++){
                    incorrect_answers[j] = ans_array.getString(j);
                }
                Question finalQuestion = new Question(category,type,difficulty,question,correct_answer,incorrect_answers);
                game.addQeustion(finalQuestion);
            }
        } catch (JSONException e){
            Log.e("Game Creation Error",e.getStackTrace().toString());

        }
        return  game;
    }
    @Override
    public void onClick(View v){
       String cat = ((Button) v).getText().toString();
       Game g;

        switch (cat){
           case "General":
               g = jsonToGame(loadFileToString(R.raw.general));
                break;
           case "Science and Nature":
               g = jsonToGame(loadFileToString(R.raw.science));
               break;
           case "Sports":
               g = jsonToGame(loadFileToString(R.raw.sports));
               break;
           case "Computers":
               g = jsonToGame(loadFileToString(R.raw.computers));
               break;
           default:
               Log.e("Hey Guys", "Default");
               g = jsonToGame(loadFileToString(R.raw.general));
       }
        Intent intent = new Intent(this,Confirmation.class);
        intent.putExtra("game",g);
        startActivity(intent);
   }
}
