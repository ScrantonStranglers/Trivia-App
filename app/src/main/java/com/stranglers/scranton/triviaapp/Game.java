package com.stranglers.scranton.triviaapp;

import java.util.ArrayList;

/* The Game object will have an array list of question objects, as well as information about the current game
*/
public class Game {
    private ArrayList<Question> questionList;
    public Game(){
        questionList = new ArrayList<>();
    }
    public void addQeustion(Question q){
        questionList.add(q);
    }

    @Override
    public String toString() {
        return "Game{" +
                "questionList=" + questionList +
                '}';
    }
}
