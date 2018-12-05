package com.stranglers.scranton.triviaapp;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

public class LeaderboardRepository {

    private String DATABASE_NAME = "dwight_db";

    private LeaderboardDatabase leaderboardDatabase;

    public LeaderboardRepository(Context context) {
        leaderboardDatabase = Room.databaseBuilder(context, LeaderboardDatabase.class, DATABASE_NAME).build();
    }

    public void insertEntry(String username, String category, int score) {
        LeaderboardEntry entry = new LeaderboardEntry();
        entry.setUsername(username);
        entry.setCategory(category);
        entry.setScore(score);
        leaderboardDatabase.leaderboardDao().insertEntry(entry);
    }

    public List<LeaderboardEntry> getAllEntries() {
        return leaderboardDatabase.leaderboardDao().getAllEntries();
    }
}
