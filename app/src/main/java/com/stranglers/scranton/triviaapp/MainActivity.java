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
        String jsonString = loadJSON(R.raw.api);
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray questionList = jsonObject.getJSONArray("results");
            for (int i = 0; i<questionList.length();i++){
                JSONObject quest = questionList.getJSONObject(i);

            }
        } catch (JSONException e){
            Log.e("JSON Creation Error",e.getStackTrace().toString());

        }

    }
    /* This method will be used to load in the JSON files into Strings.
      We will call this method every time we create a game, in order to give the user category options
    */
    private String loadJSON(int jsonID){
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
}
