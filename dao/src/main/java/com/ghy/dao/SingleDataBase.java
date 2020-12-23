package com.ghy.dao;

import android.content.Context;
import android.database.DatabaseUtils;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RxRoom;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

public class SingleDataBase {
    private  static  AppDataBase roomDatabase;

    public SingleDataBase() {

    }

    public static AppDataBase getInstance(Context mContext)
    {
        if(roomDatabase==null)
        {
            synchronized (SingleDataBase.class)
            {
                if(roomDatabase==null)
                {
                    roomDatabase= Room.databaseBuilder(mContext.getApplicationContext(),AppDataBase.class,"mdb").allowMainThreadQueries().addMigrations().build();
                }
            }
        }
        return roomDatabase;
    }
}
