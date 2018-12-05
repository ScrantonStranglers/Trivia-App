package com.stranglers.scranton.triviaapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LeaderboardEntry.class}, version = 1, exportSchema = false)
public abstract class LeaderboardDatabase extends RoomDatabase {
    public abstract LeaderboardDao leaderboardDao();
}
