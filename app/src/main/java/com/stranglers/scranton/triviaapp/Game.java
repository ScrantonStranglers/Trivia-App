package com.stranglers.scranton.triviaapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/* The Game object will have an array list of question objects, as well as information about the current game
*/
@SuppressWarnings("serial")
public class Game implements Serializable {
    private ArrayList<Question> questionList;
    public Game(){
        questionList = new ArrayList<>();
    }
    public void addQeustion(Question q){
        questionList.add(q);
    }
    public Question getRandomQuestion(int limit){
        Random r = new Random();
        int randNum = r.nextInt(50-limit);
        Question randQuest = questionList.get(randNum);
        questionList.remove(randQuest);
        return randQuest;
    }
    private int numCorrect;
    @Override
    public String toString() {
        return "Game{" +
                "questionList=" + questionList +
                '}';
    }
    public ArrayList<Question> getQuestions(){
        return questionList;
    }
    public int getNumCorrect(){
        return numCorrect;
    }
    public void increment(){
        numCorrect++;
    }
}
