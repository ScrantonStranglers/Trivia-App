package com.stranglers.scranton.triviaapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "leaderboard")
public class LeaderboardEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String category;

    @ColumnInfo
    private int score;

    public String getUsername() {
        return username;
    }

    public String getCategory() {
        return category;
    }

    public int getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
