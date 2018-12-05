package com.stranglers.scranton.triviaapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LeaderboardDao {
    @Insert
    void insertEntry(LeaderboardEntry entry);

    @Query("SELECT * FROM leaderboard ORDER BY score DESC")
    List<LeaderboardEntry> getAllEntries();
}
