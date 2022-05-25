package com.example.helloworld;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SettingsDao {
    @Query("SELECT * FROM settings WHERE id = :settingsId LIMIT 1")
    LiveData<Settings> findBySettingsId(String settingsId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSettings(Settings settings);
}