package com.example.practice.Activity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = DbTable.class, version = 1, exportSchema = false)
public abstract class DbDatabase extends RoomDatabase {
    private static DbDatabase dbDatabaseInstance;

    public static synchronized DbDatabase getInstance(Context context)
    {
        if(dbDatabaseInstance ==null)
        {
            dbDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), DbDatabase.class, "cybraryShopDb").build();
        }

        return dbDatabaseInstance;
    }

    public abstract DbDAO dbDAO();
}
