package com.stranglers.scranton.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//These packages help us parse JSON
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonString = loadFileToString(R.raw.api);
        Game game = jsonToGame(jsonString);


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
}
