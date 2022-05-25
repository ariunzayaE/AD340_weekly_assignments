package com.example.helloworld;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseSingleton {
    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context,
                            AppDatabase.class, "sample-database")
                    .build();
        }
        return db;
    }
}